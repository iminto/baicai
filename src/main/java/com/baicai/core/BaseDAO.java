package com.baicai.core;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.baicai.annotation.Column;
import com.baicai.annotation.Table;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Basedao:对常用数据库操作的简单封装后
 * 
 * @author waitfox@qq.com
 * @version :1.03
 * @modify: 2015-06-09
 *          加入对redis的支持，此处没有使用spring-data-redis，spring-data-redis有“过度设计”的嫌疑，
 *          并且没有对sharding提供良好的封装。
 *          但是，配置文件中还是加入了对spring-data-redis，可根据个人喜好选用.这个问题可以用codis解决，
 *          但codis本身太复杂。 2015-06-16 优化时间获取函数
 * @param <T>
 */
@Component
public class BaseDAO<T> {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private ShardedJedisPool shardedJedisPool;

	private static final Logger logger = LoggerFactory.getLogger(BaseDAO.class);

	/***
	 * 简单的查询语句，返回一个int，比如 select count(*) jdbcTemplate.queryForInt已经是不推荐的写法故绕了个弯
	 * 
	 * @param sql
	 * @return
	 */
	public int queryForInt(String sql) {
		Object[] object = {};
		int cnt = queryForInt(sql, object);
		return cnt;
	}

	/**
	 * 带有参数的查询语句，如 select count(*) where nid=5 modify:加入对空的处理
	 * 
	 * @param sql
	 * @param o
	 * @return
	 */
	public int queryForInt(final String sql, final Object[] o) {
		final long begin = System.currentTimeMillis();
		return getJdbcTemplate().query(sql, o, new ResultSetExtractor<Integer>() {
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				long end = System.currentTimeMillis();
				printSQL(sql, o, end - begin);
				return rs.next() ? rs.getInt(1) : 0;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public int queryForInt(String sql, Map m) {
		return getNamedParameterJdbcTemplate().query(sql, m, new ResultSetExtractor<Integer>() {
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				return rs.next() ? rs.getInt(1) : 0;
			}
		});
	}

	public long queryForLong(String sql, Object[] o) {
		return getJdbcTemplate().query(sql, o, new ResultSetExtractor<Long>() {
			public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
				return rs.next() ? rs.getLong(1) : 0l;
			}
		});
	}

	public Double queryForDouble(String sql, Object[] o) {
		return getJdbcTemplate().query(sql, o, new ResultSetExtractor<Double>() {
			public Double extractData(ResultSet rs) throws SQLException, DataAccessException {
				return rs.next() ? rs.getDouble(1) : 0d;
			}
		});
	}

	public BigDecimal queryForBigdecimal(final String sql, final Object[] o) {
		return getJdbcTemplate().query(sql, o, new ResultSetExtractor<BigDecimal>() {
			public BigDecimal extractData(ResultSet rs) throws SQLException, DataAccessException {
				return rs.next() ? rs.getBigDecimal(1) : new BigDecimal(0);
			}
		});
	}

	/**
	 * 查询并获取一个字符串，解决Spring JdbcTemplate调用queryForObject()方法结果集为空时报异常
	 * 也可以用queryForList的方法变通
	 * 
	 * @param sql
	 * @param o
	 * @return
	 */
	public String queryForString(String sql, Object[] o) {
		return getJdbcTemplate().query(sql, o, new ResultSetExtractor<String>() {
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				return rs.next() ? rs.getString(1) : null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public T queryForBean(Class<T> beanClass, String sql, Object[] args) {
		Object obj;
		try {
			obj = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(beanClass), args);
		} catch (EmptyResultDataAccessException e) {
			obj = null;
		}
		return (T) obj;
	}

	@SuppressWarnings("unchecked")
	public List<T> queryForList(Class<T> beanClass, String sql, Object[] args) {
		long begin = System.currentTimeMillis();
		List<T> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(beanClass), args);
		long end = System.currentTimeMillis();
		printSQL(sql, args, end - begin);
		return list;
	}

	public Map<String, Object> queryForMap(String sql, Object[] args) {
		long begin = System.currentTimeMillis();
		Map map;
		try {
			map = jdbcTemplate.queryForMap(sql, args);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			map = null;
		}
		long end = System.currentTimeMillis();
		printSQL(sql, args, end - begin);
		return map;
	}

	public List<Map<String, Object>> queryForList(String sql, Object[] args) {
		long begin = System.currentTimeMillis();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
		long end = System.currentTimeMillis();
		printSQL(sql, args, end - begin);
		return list;
	}

	public List<Map<String, Object>> queryForList(String sql, Map args) {
		long begin = System.currentTimeMillis();
		List<Map<String, Object>> list = namedParameterJdbcTemplate.queryForList(sql, args);
		long end = System.currentTimeMillis();
		printSQLMap(sql, args, end - begin);
		return list;
	}

	public Map<String, Object> queryForMap(String sql) {
		Object[] object = {};
		return queryForMap(sql, object);
	}

	public List<T> queryForList(Class<T> beanClass, String sql) {
		Object[] object = {};
		return queryForList(beanClass, sql, object);
	}

	public int update(String sql, Object[] args) {
		long begin = System.currentTimeMillis();
		int i = jdbcTemplate.update(sql, args);
		long end = System.currentTimeMillis();
		printSQL(sql, args, end - begin);
		return i;
	}

	public int update(String sql, Map map) {
		long begin = System.currentTimeMillis();
		int i = namedParameterJdbcTemplate.update(sql, map);
		long end = System.currentTimeMillis();
		printSQLMap(sql, map, end - begin);
		return i;
	}

	public int update(String sql) {
		Object[] object = {};
		return update(sql, object);
	}

	public int save(String sql) {
		Object[] object = {};
		return save(sql, object);
	}

	public int insert(T t) {
		long begin = System.currentTimeMillis();
		Field[] at = t.getClass().getDeclaredFields();
		String tableName = "";
		try {
			Table table = (Table) t.getClass().getAnnotation(Table.class);
			tableName = table.name();// 通过注解获取表名
		} catch (Exception e) {
			logger.warn("获取表名注解失败，将使用类名作为表名，建议加上表名注解"+e.getMessage());
			tableName = t.getClass().getSimpleName().toLowerCase();
		}
		tableName=DaoUtil.tableFix+tableName;
		StringBuilder sb = new StringBuilder(40);
		sb.append("INSERT INTO `").append(tableName).append("` (");
		StringBuilder after = new StringBuilder(48);// SQL后半部分
		String fullSQL = "";
		for (Field field : at) {
			field.setAccessible(true);
			String Tcolumn = field.getName();
			try {
				if (field.get(t) != null || (field.isAnnotationPresent(Column.class) == true
						&& field.getAnnotation(Column.class).insertZero() == true)) {
					Column dColumn = field.getAnnotation(Column.class);
					Tcolumn = dColumn != null ? dColumn.column() : field.getName();
					if (field.getModifiers() == 25)
						continue;// 如果是规则字段，跳出
					sb.append("`").append(Tcolumn).append("`,");
					after.append(":").append(field.getName()).append(",");
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.error("数据入库时注解解析失败" + e.getMessage());
			}

		}
		fullSQL = sb.substring(0, sb.length() - 1);
		fullSQL += ") VALUES (" + after.substring(0, after.length() - 1) + (")");
		KeyHolder holder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(fullSQL, new BeanPropertySqlParameterSource(t),holder);
		long end = System.currentTimeMillis();
		printSQL(fullSQL,  end - begin);
		return holder.getKey().intValue();
	}

	/**
	 * 插入一条数据，同时返回主键。如果不需要返回主键，请用execute方法 考虑到表不存在主键的情况。
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public int save(String sql, Object[] args) {
		long begin = System.currentTimeMillis();
		int j = jdbcTemplate.update(sql, args);
		long end = System.currentTimeMillis();
		printSQL(sql, args, end - begin);
		int i = queryForInt("SELECT @@IDENTITY");
		return i > j ? i : j;
	}

	/**
	 * 同样是插入数据返回主键，只是这次的参数是一个和表对应的模型
	 * 
	 * @param sql
	 * @param o
	 * @return
	 */
	public int insert(String sql, Object o) {
		KeyHolder holder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(o), holder);
		return holder.getKey().intValue();

	}

	public int execute(String sql) {
		Object[] object = {};
		return execute(sql, object);
	}

	public int execute(String sql, Object[] args) {
		return update(sql, args);
	}

	public int execute(String sql, Map<String, ?> map) {
		long begin = System.currentTimeMillis();
		int i = jdbcTemplate.update(sql, map);
		long end = System.currentTimeMillis();
		printSQL(sql, map.entrySet().toArray(), end - begin);
		return i;
	}

	public int[] batchUpdate(String sql, List<Object[]> batchArgs) {
		long begin = System.currentTimeMillis();
		int[] temp = jdbcTemplate.batchUpdate(sql, batchArgs);
		long end = System.currentTimeMillis();
		printSQL(sql, null, end - begin);
		return temp;
	}

	private void printSQL(String sql, Object[] params, long time) {
		logger.info("  SQL: " + sql + (params != null ? "\nParams: " + Arrays.deepToString(params) : "") + ",耗时：" + time
				+ " 毫秒\n");
	}
	
	private void printSQL(String sql, long time) {
		logger.info("  SQL: " + sql + ",耗时：" + time+ " 毫秒\n");
	}

	private void printSQLMap(String sql, Map params, long time) {
		logger.info("  SQL: " + sql + (params != null ? "\nParams: " + params : "") + ",耗时：" + time + " 毫秒\n");
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	public ShardedJedis getRedisClient() {
		try {
			ShardedJedis shardJedis = shardedJedisPool.getResource();
			return shardJedis;
		} catch (Exception e) {
			logger.error("getRedisClent error");
		}
		return null;
	}

}
