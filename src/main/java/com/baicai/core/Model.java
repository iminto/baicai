package com.baicai.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baicai.annotation.Column;
import com.baicai.annotation.Key;
import com.baicai.annotation.NotNull;
import com.baicai.annotation.Size;
import com.baicai.annotation.Table;
import com.baicai.annotation.ValidType;
import com.baicai.util.ArrayHelper;
import com.baicai.util.FILTER;
import com.baicai.util.PropertiesTool;
import com.baicai.util.ValidatorHelper;

/**
 * 基于jdbcTemplate的简单ORM类
 * 注意：由于使用了new，导致模型里面的操作可能不在事务控制下，不要大量使用这里面的方法。
 * 另外，反射的使用也会降低效率
 * @TODO: 可能存在的安全隐患
 * @author waitfox@qq.com
 * @created 2015-05-03 01:12:01
 * @version 0.15 默认表名转为小写
 */
@Component
public class Model {

	@SuppressWarnings("rawtypes")
	static BaseDAO<?> baseDao = (BaseDAO) SpringUtils.getApplicationContext()
			.getBean("baseDAO");
	private ThreadLocal<String> whereCon = new ThreadLocal<String>();// where条件，线程安全
	private String tableName = PropertiesTool.get("system", "tableFix")
			+ this.getClass().getSimpleName().toLowerCase();
	private boolean keySet = false;// 是否设置了主键并且有值，防止主键条件和where条件混用
	private static final Logger logger = LoggerFactory.getLogger(Model.class);
	private Map<String, String> errorMap = new HashMap<String, String>();

	public Model() {
		// 由于model操作之间复用性不高且反射会影响性能，故不在构造函数里做太多初始化操作
		if (this.getClass().isAnnotationPresent(Table.class)) {
			Table table = (Table) this.getClass().getAnnotation(Table.class);
			tableName = table.name();// 获取表名
		}
	}
	
	/**
	 * 更新模型，要求必须有主键，并且主键必须有值
	 * @return
	 */
	public int update(){
		Field[] at = this.getClass().getDeclaredFields();
		StringBuilder sb = new StringBuilder(40);
		sb.append("UPDATE `").append(tableName).append("` SET ");
		String fullSQL = "";
		String key=null;
		Object valueObject=null;
		boolean hasKey=false;
		for (Field field : at) {
			field.setAccessible(true);
			String Tcolumn = field.getName().toLowerCase();// 字段名转为小写，以符合MySQL里的习惯
			try {
				if(hasKey==false && field.isAnnotationPresent(Key.class) == true){
					hasKey=true;//找到了就不找了
					key=field.getName();
					valueObject=field.get(this);
				}
				if (field.get(this) != null
						|| (field.isAnnotationPresent(Column.class) == true && field
								.getAnnotation(Column.class).insertZero() == true)) {
					if (field.getModifiers()==25) continue;//如果是规则字段，跳出
					Column dColumn = field.getAnnotation(Column.class);
					Tcolumn = dColumn != null ? dColumn.column() : field
							.getName();
					sb.append("`").append(Tcolumn).append("`=:").append(field.getName()).append(",");
				}
			} catch (Exception e) {

			}

		}
		if(hasKey==false || valueObject==null){
			logger.error("更新数据时主键不存在，退出。SQL语句："+fullSQL);
			return -1;
		}
		fullSQL = sb.substring(0, sb.length() - 1);
		fullSQL+=" WHERE "+key+"="+valueObject;
		logger.info(fullSQL + this);
		int i=baseDao.getNamedParameterJdbcTemplate().update(fullSQL,
				new BeanPropertySqlParameterSource(this));
		return i;
	}

	public Model where(String condition) {
		if (keySet != true) {
			if (whereCon.get() == null || whereCon.get().equals("")) {
				whereCon.set(" WHERE 1 ");
			}
			whereCon.set(whereCon.get() + " AND " + condition);
		}
		return this;
	}

	/**
	 * 删除一条纪录，支持where条件或者根据主键删除，二者不能混用
	 * 
	 * @return
	 */
	public int del() {
		buildPrimaryWhere();
		if (whereCon.get() == null || whereCon.get().equals("")) {
			return 0;
		} else {
			String sql = " DELETE FROM `" + tableName + "`  " + whereCon.get();
			int result = baseDao.getJdbcTemplate().update(sql);
			whereCon = null;// SQL执行成功后，清空Where条件
			return result;
		}

	}

	public <T> T find() {
		buildPrimaryWhere();
		System.out.println("whereCon.get()"+whereCon.get());
		if (whereCon.get() == null || whereCon.get().equals("")) {
			return null;
		} else {
			String sql = " SELECT * FROM `" + tableName + "`  "
					+ whereCon.get();
			System.out.println(sql);
			Object obj;
			Object[] args = {};
			try {
				obj = baseDao.getJdbcTemplate().queryForObject(sql,
						new BeanPropertyRowMapper(this.getClass()), args);
			} catch (EmptyResultDataAccessException e) {
				obj = null;

			}
			return (T) obj;
		}

	}

	/**
	 * 判断字段是否为空，目前只针对int和String,Long做判断
	 * 
	 * @param field
	 * @return
	 */
	public boolean hasValue(Field field) {
		try {
			if (field.getType().getSimpleName().equals("int")
					|| field.getType().getSimpleName().equals("Integer")
					|| field.getType().getSimpleName().equals("long")
					|| field.getType().getSimpleName().equals("Long")) {
				if (field.get(this) == null) {
					return false;
				}
			}
			if (field.getType().getSimpleName().equals("String")) {
				if (field.get(this) == null || field.get(this).equals("")) {
					return false;
				}
			}
		} catch (Exception e) {

		}
		return true;
	}

	public void buildPrimaryWhere() {
		Field[] at = this.getClass().getDeclaredFields();
		String column = "";// 默认主键名

		for (Field field : at) {
			field.setAccessible(true);
			try {
				if (field.isAnnotationPresent(Key.class) == true
						&& hasValue(field)) {
					if (field.isAnnotationPresent(Column.class) == true) {
						column = field.getAnnotation(Column.class).column();
					} else {
						column = field.getName();
					}
					whereCon.set(" WHERE " + column + "=" + field.get(this));
					keySet = true;
					break;
				}
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}
		}
	}

	public HashMap<String, String> createDO() {
		Field[] at = this.getClass().getDeclaredFields();
		HashMap<String, String> map = new HashMap<String, String>();
		for (Field field : at) {
			// 找出有column注解的字段,在数据库字段和POJO之间建立关联
			if (field.isAnnotationPresent(Column.class) == true) {
				map.put(field.getAnnotation(Column.class).column(),
						field.getName());
			}
		}
		return map;
	}

	/**
	 * 对Model进行验证，目前支持三种注解，其中validType注解是可扩展的，可以根据需要进行扩展
	 * 用法如下：user.validate(User.regRule);Map<String, String>
	 * errorMap=user.getErrorMap(); 判断errorMap是否非空即可。
	 * 
	 * @param rules
	 *            验证的类型，需要去domain里配置，可针对业务有不同的认证规则
	 * @return
	 */
	public boolean validate(String rules) {
		Field[] at = this.getClass().getDeclaredFields();
		Field rulesf = null;
		String[] rulesArr = {};// 需要验证的字段数组
		try {
			rulesf = this.getClass().getDeclaredField(rules);
			rulesArr = (String[]) rulesf.get(this);
		} catch (NoSuchFieldException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		for (Field field : at) {
			field.setAccessible(true);
			if (!ArrayHelper.inArray(rulesArr, field.getName())) {
				continue;// 不需要验证的字段跳过
			} else {
				try {
					doValidType(field);
					doSize(field);
					doNotNull(field);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		return this.errorMap.isEmpty();//如果这个数组为空，那么就算验证通过

	}

	public Map<String, String> getErrorMap() {
		return this.errorMap;
	}

	/**
	 * 对validType注解进行验证
	 */
	private boolean doValidType(Field field) throws IllegalArgumentException,
			IllegalAccessException {
		boolean passed = true;
		if (field.isAnnotationPresent(ValidType.class) == true) {

			if (field.getAnnotation(ValidType.class).valid().equals("password")) {
				if (ValidatorHelper.filter_var(String.valueOf(field.get(this)),
						FILTER.PASSWORD)) {
				} else {
					passed = false;
					errorMap.put(field.getName(),
							field.getAnnotation(ValidType.class).message());
				}
			}
			if (field.getAnnotation(ValidType.class).valid().equals("email")) {
				if (ValidatorHelper.filter_var(String.valueOf(field.get(this)),
						FILTER.EMAIL)) {
				} else {
					passed = false;
					errorMap.put(field.getName(),
							field.getAnnotation(ValidType.class).message());
				}
			}
			if (field.getAnnotation(ValidType.class).valid().equals("mobile")) {
				if (ValidatorHelper.filter_var(String.valueOf(field.get(this)),
						FILTER.MOBILEPHONE)) {
				} else {
					passed = false;
					errorMap.put(field.getName(),
							field.getAnnotation(ValidType.class).message());
				}
			}

		}
		return passed;
	}

	/**
	 * 对size注解进行验证
	 * 
	 * @param field
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private boolean doSize(Field field) throws IllegalArgumentException,
			IllegalAccessException {
		boolean passed = true;
		if (field.isAnnotationPresent(Size.class) == true) {
			if (field.getAnnotation(Size.class).min() > ((String) field
					.get(this)).length()) {
				passed = false;
				errorMap.put(field.getName(), field.getAnnotation(Size.class)
						.message());
			}
			if (field.getAnnotation(Size.class).max() < ((String) field
					.get(this)).length()) {
				passed = false;
				errorMap.put(field.getName(), field.getAnnotation(Size.class)
						.message());
			}
		}
		return passed;
	}

	/**
	 * 处理notNull注解
	 * 
	 * @param field
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private boolean doNotNull(Field field) throws IllegalArgumentException,
			IllegalAccessException {
		boolean passed = true;
		if (field.isAnnotationPresent(NotNull.class) == true) {
			if (field.getType().getSimpleName().equals("int")
					|| field.getType().getSimpleName().equals("Integer")
					|| field.getType().getSimpleName().equals("long")
					|| field.getType().getSimpleName().equals("Long")) {
				if (field.get(this) == null) {
					errorMap.put(field.getName(),
							field.getAnnotation(NotNull.class).message());
					return false;
				}
			}
			if (field.getType().getSimpleName().equals("String")) {
				if (field.get(this) == null || field.get(this).equals("")) {
					errorMap.put(field.getName(),
							field.getAnnotation(NotNull.class).message());
					return false;
				}
			} else {
				if (field.get(this) == null) {
					errorMap.put(field.getName(),
							field.getAnnotation(NotNull.class).message());
					return false;
				}
			}

		}
		return passed;
	}
	
	/**
	 * 判断对象是否真的为空，不建议大量使用，反射效率不高
	 * 可能有考虑不完善的地方
	 * 因此加上不推荐的标注
	 * @return
	 */
	@Deprecated
	public boolean isNullObject(){
		boolean isNull=true;//默认为空
		Field[] at = this.getClass().getDeclaredFields();
		for (Field field : at) {
			field.setAccessible(true);
			try {
				if (field.getModifiers()!=25 && field.get(this) != null){
					isNull=false;//只要有一个属性非空，那就认为非空了
					break;
				}
			} catch (Exception e) {
				
			} 
		}
		return isNull;
	}

}
