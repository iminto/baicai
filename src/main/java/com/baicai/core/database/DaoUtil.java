package com.baicai.core.database;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baicai.corewith.annotation.Column;
import com.baicai.corewith.annotation.Key;
import com.baicai.corewith.annotation.Table;
import com.baicai.domain.system.Pagination;
import com.baicai.corewith.util.PropertiesTool;

/**
 * DAO辅助类
 * 
 * @author 白菜
 *
 */
public class DaoUtil {
	public static final String tableFix = PropertiesTool.get("system", "tableFix");
	private static final Logger logger = LoggerFactory.getLogger(DaoUtil.class);
	public static final String INSERT = "INSERT INTO ";
	public static final String COMMA = ",";
	public static final String COLON = ":";
	public static final String BRACKET_LEFT = " ( ";
	public static final String BRACKET_RIGHT = " ) ";
	public static final String VALUES = " ) VALUES ( ";
	public static final String APOSTROPHE = "`";
	public static final String APOSTROPHECOMMA = "`,";
	public static final String ASK = "?";
	public static final String UPDATE = "UPDATE ";
	public static final String WHERE = " WHERE ";
	public static final String EQUAL = "=";
	public static final String SET = " SET ";

	public static String format(String sql) {
		String result = sql.replace("{", tableFix);
		result = result.replace("}", " ");
		return result;
	}

	public static String limit(Pagination page) {
		return " limit " + page.getOffset() + "," + page.getPageSize() + " ";
	}

	public static String getTableName(Object bean) {
		String tableName = "";
		try {
			Table table = (Table) bean.getClass().getAnnotation(Table.class);
			tableName = table.name();// 通过注解获取表名
		} catch (Exception e) {
			logger.warn("获取表名注解失败，将使用类名作为表名，建议加上表名注解,异常信息：" + e.getMessage());
			tableName = bean.getClass().getSimpleName().toLowerCase();
		}
		tableName = DaoUtil.tableFix + tableName;
		return tableName;
	}

	public static SqlBound insert(Object t) {
		Field[] at = t.getClass().getDeclaredFields();
		String tableName = getTableName(t);
		StringBuilder sb = new StringBuilder(40);
		sb.append(INSERT).append(tableName).append(BRACKET_LEFT);
		StringBuilder after = new StringBuilder(48);// SQL后半部分
		List<Object> params = new ArrayList<Object>();
		for (Field field : at) {
			field.setAccessible(true);
			String Tcolumn = field.getName();
			try {
				if (field.get(t) != null || (field.isAnnotationPresent(Column.class) == true && field.getAnnotation(Column.class).insertZero() == true)) {
					Column dColumn = field.getAnnotation(Column.class);
					Tcolumn = dColumn != null ? dColumn.column() : field.getName();
					if (field.getModifiers() == 25)
						continue;// 如果是规则字段，跳出
					sb.append(APOSTROPHE).append(Tcolumn).append(APOSTROPHECOMMA);
					params.add(field.get(t));
					after.append(ASK).append(COMMA);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				logger.error("数据入库时注解解析失败" + e.getMessage());
			}

		}
		sb.deleteCharAt(sb.length() - 1);
		after.deleteCharAt(after.length() - 1);
		sb.append(VALUES).append(after).append(BRACKET_RIGHT);
		return new SqlBound(sb.toString(), params.toArray());
	}

	/**
	 * 更新一个POJO，必需有主键且主键不能为空
	 * 支持多主键，以找到的第一个主键为准。如果更新语句没有主键，请使用通用方法
	 * @param t
	 * @return
	 */
	public static SqlBound update(Object t) {
		SqlBound bound = new SqlBound();
		Field[] at = t.getClass().getDeclaredFields();
		StringBuilder sb = new StringBuilder(40);
		String tableName = getTableName(t);
		sb.append(UPDATE).append(tableName).append(SET);
		String key = null;// 主键的名字
		Object valueObject = null;// 主键的值
		boolean hasKey = false;// 是否存在主键
		List<Object> params = new ArrayList<Object>();
		for (Field field : at) {
			field.setAccessible(true);
			String Tcolumn = field.getName().toLowerCase();// 字段名转为小写，以符合MySQL里的习惯
			try {
				if (hasKey == false && field.isAnnotationPresent(Key.class) == true) {// 如果没找到主键，则逐个字段寻找主键
					hasKey = true;// 找到了就做个记号，不找了
					key = field.getName();
					valueObject = field.get(t);
					hasKey=(valueObject==null)?false:true;//解决多个主键问题
				}
				if (field.get(t) != null || (field.isAnnotationPresent(Column.class) == true && field.getAnnotation(Column.class).insertZero() == true)) {
					if (field.getModifiers() == 25)
						continue;// 如果是规则字段，跳出
					Column dColumn = field.getAnnotation(Column.class);
					Tcolumn = dColumn != null ? dColumn.column() : field.getName();
					sb.append(APOSTROPHE).append(Tcolumn).append("`=?").append(COMMA);
					params.add(field.get(t));
				}
			} catch (Exception e) {
				logger.error("update解析注解阶段出现异常");
				throw new SqlException("update解析注解阶段出现异常");
			}

		}
		if (hasKey == false || valueObject == null) {
			logger.error("更新数据时主键不存在，退出。SQL语句：" + sb);
			throw new SqlException("update解析注解阶段出现异常");
		} else {
			sb.deleteCharAt(sb.length() - 1);
			sb.append(WHERE).append(key).append(EQUAL).append(ASK);
			params.add(valueObject);
			bound.setSql(sb.toString());
			bound.setParam(params.toArray());
		}
		return bound;
	}
	
	public HashMap<String, String> createDO(Object bean) {
		Field[] at = bean.getClass().getDeclaredFields();
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
}
