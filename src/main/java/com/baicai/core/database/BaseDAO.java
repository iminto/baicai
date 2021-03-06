package com.baicai.core.database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.baicai.corewith.util.StringUtil;
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
		int cnt = queryForInt(getDoneSQL(sql), object);
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
		final String sqlx = getDoneSQL(sql);
		final long begin = System.currentTimeMillis();
		return getJdbcTemplate().query(sqlx, o, new ResultSetExtractor<Integer>() {
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				long end = System.currentTimeMillis();
				printSQL(sqlx, o, end - begin);
				return rs.next() ? rs.getInt(1) : 0;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public int queryForInt(String sql, Map m) {
		final String sqlx = getDoneSQL(sql);
		return getNamedParameterJdbcTemplate().query(sqlx, m, new ResultSetExtractor<Integer>() {
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				return rs.next() ? rs.getInt(1) : 0;
			}
		});
	}

	public long queryForLong(String sql, Object[] o) {
		final String sqlx = getDoneSQL(sql);
		return getJdbcTemplate().query(sqlx, o, new ResultSetExtractor<Long>() {
			public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
				return rs.next() ? rs.getLong(1) : 0l;
			}
		});
	}

	public Double queryForDouble(String sql, Object[] o) {
		final String sqlx = getDoneSQL(sql);
		return getJdbcTemplate().query(sqlx, o, new ResultSetExtractor<Double>() {
			public Double extractData(ResultSet rs) throws SQLException, DataAccessException {
				return rs.next() ? rs.getDouble(1) : 0d;
			}
		});
	}

	public BigDecimal queryForBigdecimal(final String sql, final Object[] o) {
		final String sqlx = getDoneSQL(sql);
		return getJdbcTemplate().query(sqlx, o, new ResultSetExtractor<BigDecimal>() {
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
		final String sqlx = getDoneSQL(sql);
		return getJdbcTemplate().query(sqlx, o, new ResultSetExtractor<String>() {
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				return rs.next() ? rs.getString(1) : null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public T queryForBean(Class<T> beanClass, String sql, Object[] args) {
		Object obj;
		try {
			long begin = System.currentTimeMillis();
			String sqlx = getDoneSQL(sql);
			obj = jdbcTemplate.queryForObject(getDoneSQL(sql), new BeanPropertyRowMapper(beanClass), args);
			long end = System.currentTimeMillis();
			printSQL(sqlx, args, end - begin);
		} catch (EmptyResultDataAccessException e) {
			obj = null;
			return null;
		}
		return (T) obj;
	}

	public List<T> queryForList(Class<T> beanClass, String sql, Object[] args) {
		String sqlx = getDoneSQL(sql);
		BeanPropertyRowMapper<T> rowMapper = new BeanPropertyRowMapper<T>(beanClass);
		long begin = System.currentTimeMillis();
		List<T> list = jdbcTemplate.query(sqlx, rowMapper, args);
		long end = System.currentTimeMillis();
		printSQL(sqlx, args, end - begin);
		return list;
	}

	public Map<String, Object> queryForMap(String sql, Object[] args) {
		String sqlx = getDoneSQL(sql);
		long begin = System.currentTimeMillis();
		Map map;
		try {
			map = jdbcTemplate.queryForMap(sqlx, args);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			map = null;
		}
		long end = System.currentTimeMillis();
		printSQL(sqlx, args, end - begin);
		return map;
	}

	public List<Map<String, Object>> queryForList(String sql, Object[] args) {
		long begin = System.currentTimeMillis();
		String sqlx = getDoneSQL(sql);
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlx, args);
		long end = System.currentTimeMillis();
		printSQL(sqlx, args, end - begin);
		return list;
	}

	public List<Map<String, Object>> queryForList(String sql, Map args) {
		long begin = System.currentTimeMillis();
		String sqlx = getDoneSQL(sql);
		List<Map<String, Object>> list = namedParameterJdbcTemplate.queryForList(sqlx, args);
		long end = System.currentTimeMillis();
		printSQLMap(sqlx, args, end - begin);
		return list;
	}

	public Map<String, Object> queryForMap(String sql) {
		Object[] object = {};
		return queryForMap(getDoneSQL(sql), object);
	}

	public List<T> queryForList(Class<T> beanClass, String sql) {
		Object[] object = {};
		return queryForList(beanClass, getDoneSQL(sql), object);
	}

	public int update(String sql, Object[] args) {
		long begin = System.currentTimeMillis();
		String sqlx = getDoneSQL(sql);
		int i = jdbcTemplate.update(sqlx, args);
		long end = System.currentTimeMillis();
		printSQL(sqlx, args, end - begin);
		return i;
	}

	public int update(String sql, Map map) {
		long begin = System.currentTimeMillis();
		String sqlx = getDoneSQL(sql);
		int i = namedParameterJdbcTemplate.update(sqlx, map);
		long end = System.currentTimeMillis();
		printSQLMap(sqlx, map, end - begin);
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

	public long insert(Object t) {
		return insert(t, false);
	}
	
	/**
	 * 更新一个POJO，必需有主键且主键不能为空
	 * 支持多主键，以找到的第一个主键为准。如果更新语句没有主键，请使用通用方法
	 * @param bean POJO对象
	 * @return
	 */
	public int update(Object bean) {
		long begin = System.currentTimeMillis();
		SqlBound bound = DaoUtil.update(bean);
		int i=update(bound);
		long end = System.currentTimeMillis();
		printSQL(bound.getSql(), bound.getParam(), end - begin);
		return i;
	}
	
	/**
	 * 优化的插入方法，少使用了一次反射
	 * @param t POJO对象
	 * @param back 是否需要返回主键
	 * @return
	 */
	public long insert(Object t, boolean back) {
		final SqlBound bound = DaoUtil.insert(t);
		long id = 0l;
		long begin = System.currentTimeMillis();
		if (back) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(bound.getSql(), Statement.RETURN_GENERATED_KEYS);
					int index = 0;
					for (Object param : bound.getParam()) {
						index++;
						ps.setObject(index, param);
					}
					return ps;
				}
			}, keyHolder);
			id = keyHolder.getKey().longValue();
		} else {
			update(bound);
		}
		long end = System.currentTimeMillis();
		printSQL(bound.getSql(), bound.getParam(), end - begin);
		return id;
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
		String sqlx = getDoneSQL(sql);
		int j = jdbcTemplate.update(sqlx, args);
		long end = System.currentTimeMillis();
		printSQL(sqlx, args, end - begin);
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
		String sqlx = getDoneSQL(sql);
		namedParameterJdbcTemplate.update(sqlx, new BeanPropertySqlParameterSource(o), holder);
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
		String sqlx = getDoneSQL(sql);
		int i = jdbcTemplate.update(sqlx, map);
		long end = System.currentTimeMillis();
		printSQL(sqlx, map.entrySet().toArray(), end - begin);
		return i;
	}

	public int[] batchUpdate(String sql, List<Object[]> batchArgs) {
		long begin = System.currentTimeMillis();
		String sqlx = getDoneSQL(sql);
		int[] temp = jdbcTemplate.batchUpdate(sqlx, batchArgs);
		long end = System.currentTimeMillis();
		printSQL(sqlx, null, end - begin);
		return temp;
	}

	private int update(SqlBound sqlBound) {
		String sql = null;
		int i=0;
		if(StringUtil.isBlank(sqlBound.getSql())){
			logger.error("Bound构造器中SQL为空");
			throw new SqlException("Bound构造器中SQL为空");
		}
		try {
			sql = sqlBound.getSql();
			i=jdbcTemplate.update(sql, sqlBound.getParam());
		} catch (Exception e) {
			throw new SqlException(e,sql);
		}
		return i;
	}

	private void printSQL(String sql, Object[] params, long time) {
		logger.info("  SQL: " + sql + (params != null ? "\nParams: " + Arrays.deepToString(params) : "") + ",耗时：" + time + " 毫秒\n");
	}

	private void printSQL(String sql, long time) {
		logger.info("  SQL: " + sql + ",耗时：" + time + " 毫秒\n");
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

	/**
	 * 处理传入的SQL，主要是表前缀的拼接等，不排除其他特殊处理
	 * 
	 * @param sql
	 * @return
	 */
	public String getDoneSQL(String sql) {
		return DaoUtil.format(sql);
	}

}
