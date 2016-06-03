package com.baicai.core.database;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baicai.annotation.Column;
import com.baicai.annotation.Table;
import com.baicai.domain.system.Pagination;
import com.baicai.util.PropertiesTool;

/**
 * DAO辅助类
 * @author 95
 *
 */
public class DaoUtil {
	public static final String tableFix=PropertiesTool.get("system", "tableFix");
	private static final Logger logger = LoggerFactory.getLogger(DaoUtil.class);
	public static final String INSERT="INSERT INTO ";
	public static final String COMMA=",";
	public static final String COLON=":";
	public static final String BRACKET_LEFT=" ( ";
	public static final String BRACKET_RIGHT=" ) ";
	public static final String VALUES=" ) VALUES ( ";
	public static final String APOSTROPHE="`" ;
	public static final String APOSTROPHECOMMA="`," ;
	public static final String ASK="?" ;
	
	
	public static String format(String sql) {
		String result = sql.replace("{",  tableFix);
		result = result.replace("}", " ");
		return result;
	}
	
	public static String  limit(Pagination page){
		return " limit "+page.getOffset()+","+page.getPageSize()+" ";
	}
	
	public static SqlBound insert(Object t){
	    Field[] at = t.getClass().getDeclaredFields();
        String tableName = "";
        try {
            Table table = (Table) t.getClass().getAnnotation(Table.class);
            tableName = table.name();// 通过注解获取表名
        } catch (Exception e) {
            logger.warn("获取表名注解失败，将使用类名作为表名，建议加上表名注解,异常信息："+e.getMessage());
            tableName = t.getClass().getSimpleName().toLowerCase();
        }
        tableName=DaoUtil.tableFix+tableName;
        StringBuilder sb = new StringBuilder(40);
        sb.append(INSERT).append(tableName).append(BRACKET_LEFT);
        StringBuilder after = new StringBuilder(48);// SQL后半部分
        List<Object> params = new ArrayList<Object>();
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
                    sb.append(APOSTROPHE).append(Tcolumn).append(APOSTROPHECOMMA);
                    params.add(field.get(t));
                    after.append(ASK).append(COMMA);
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                logger.error("数据入库时注解解析失败" + e.getMessage());
            }

        }
        sb.deleteCharAt(sb.length()-1);
        after.deleteCharAt(after.length()-1);
        sb.append(VALUES).append(after) .append(BRACKET_RIGHT);
        return new SqlBound(sb.toString(),params.toArray());
	}
}
