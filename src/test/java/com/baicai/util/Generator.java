package com.baicai.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


/**
* @Description: Model代码生成
* @author 猪肉有毒 waitfox@qq.com  
* @date 2015年8月22日 下午4:46:21 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
*/
public class Generator {
	private static JdbcTemplate jdbcTemplate = (JdbcTemplate) new FileSystemXmlApplicationContext("F:/data/eclipse/datura/WebRoot/WEB-INF/config/applicationContext-core.xml").getBean("jdbcTemplate");
	 /**
     * 数据库名
     */
    public static final String SCHEMA_NAME = "test";

    /**
     * 输出路径绝对地址
     */
    public static final String OUTPUT_PATH = "f:/temp/";

    /**
     * 生成代码的package前缀
     */
    public static final String PACKAGE_BASE = "com.baicai.util";
    /**
     * 生成类的前缀，如xxxSimpleDAO中的Simple
     */
    public static final String CLASS_PREFIX = "Simple";

    public static final String JDBC_TEMPLATE_NAME = "jdbcTemplate";
    
    public static List<TableBean> getTables() {
        List<TableBean> tableBeanList = jdbcTemplate.query("select * from information_schema.tables where table_schema = ?", new Object[]{SCHEMA_NAME},
                new RowMapper<TableBean>() {
                    @Override
                    public TableBean mapRow(ResultSet rs, int rowNum) throws SQLException {
                        TableBean bean = new TableBean();
                        String tableName = rs.getString("table_name");
                        String tableNameTrimed = tableName;
                        if (tableName.startsWith("t_")) {
                            tableNameTrimed = tableName.substring(2);
                        }

                        bean.setTableName(tableName);
                        bean.setTableNameNoDash(delDash(tableNameTrimed));
                        bean.setTableNameCapitalized(StringUtil.capitalize(bean.getTableNameNoDash()));
                        bean.setTableComment(rs.getString("table_comment"));
                        return bean;
                    }
                });

        for (TableBean tableBean : tableBeanList) {
            tableBean.setColumnBeanList(getColumns(tableBean.getTableName(), tableBean));
        }

        return tableBeanList;
    }

    public static List<ColumnBean> getColumns(String tableName, final TableBean tableBean) {
        return jdbcTemplate.query("select * from information_schema.COLUMNS where table_schema = ? and table_name = ?",
                new Object[]{SCHEMA_NAME, tableName},
                new RowMapper<ColumnBean>() {
                    @Override
                    public ColumnBean mapRow(ResultSet rs, int rowNum) throws SQLException {
                        ColumnBean bean = new ColumnBean();
                        String columnName = rs.getString("column_name");
                        bean.setColumnName(columnName);
                        bean.setColumnNameNoDash(delDash(columnName));
                        bean.setColumnNameCapitalized(StringUtil.capitalize(bean.getColumnNameNoDash()));

                        String charLength = rs.getString("CHARACTER_MAXIMUM_LENGTH");
                        if (!StringUtil.isBlank(charLength)) {
                            bean.setLength(Integer.parseInt(charLength));
                        }

                        bean.setColumnComment(rs.getString("column_comment"));

                        String columnType = rs.getString("column_type").toLowerCase();
                        if (columnType.startsWith("varchar") || ("text").equals(columnType) || columnType.startsWith("enum")) {
                            bean.setColumnType("String");
                            bean.setColumnTypeRsGetter("getString");
                        } else if (columnType.startsWith("tinyint") || columnType.startsWith("smallint") || columnType.startsWith("mediumint")) {
                            bean.setColumnType("Integer");
                            bean.setColumnTypeRsGetter("getInt");
                        } else if (columnType.startsWith("int") || columnType.startsWith("bigint")) {
                            bean.setColumnType("Long");
                            bean.setColumnTypeRsGetter("getLong");
                        } else if (("timestamp").equals(columnType)) {
                            bean.setColumnType("Date");
                            bean.setColumnTypeRsGetter("getDate");
                            tableBean.setHasDateColumn(true);
                        } else if (columnType.startsWith("float")) {
                            bean.setColumnType("Float");
                            bean.setColumnTypeRsGetter("getFloat");
                        } else if (columnType.startsWith("double")) {
                            bean.setColumnType("Double");
                            bean.setColumnTypeRsGetter("getDouble");
                        } else if (columnType.startsWith("decimal")) {
                            bean.setColumnType("BigDecimal");
                            bean.setColumnTypeRsGetter("getBigDecimal");
                            tableBean.setHasBigDecimal(true);
                        } else {
                            throw new RuntimeException("Unsupported type: [" + columnType + "]!");
                        }

                        return bean;
                    }
                });
    }

    private static String delDash(String str) {
        String lowerCaseStr = str.toLowerCase();
        String[] noDashArray = StringUtil.splitc(lowerCaseStr, '_');
        StringBuilder noDash = new StringBuilder(noDashArray[0]);
        for (int i = 1; i < noDashArray.length; i++) {
            noDash.append(StringUtil.capitalize(noDashArray[i]));
        }
        return noDash.toString();
    }
	public static void main(String[] args) {
		String date=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
		List<TableBean> tableBeanList = getTables();
		for (TableBean tableBean : tableBeanList) {
			StringBuilder sb=new StringBuilder(100);
			StringBuilder importClass=new StringBuilder();
			Set<String> ipset=new HashSet<>();
			ipset.add("import com.baicai.core.Model\r\n");
			StringBuilder head=new StringBuilder();
			head.append("package ").append(PACKAGE_BASE).append("\r\n");
			sb.append("/**\r\n").append("* @Description:").append(tableBean.getTableComment())
			.append("模型类\r\n* @date ").append(date).append(" \r\n* @version V1.0\r\n");
			sb.append("*/\r\n");
			sb.append("public class ").append(tableBean.getTableNameCapitalized());
			sb.append(" extends Model{\r\n");
			for (ColumnBean column : tableBean.getColumnBeanList()) {
				sb.append("private ").append(column.getColumnType()).append(" ").append(column.getColumnName());
				sb.append(" ;");
				if(column.getColumnComment()!=null && column.getColumnComment().length()>0){
					sb.append("//").append(column.getColumnComment());
				}
				if(column.getColumnType().equals("BigDecimal")){
					ipset.add("import java.math.BigDecimal;\r\n");
				}
				sb.append("\r\n");
			}
			Iterator it = ipset.iterator();
			while(it.hasNext()){
				importClass.append(it.next());
			}
			sb.append("}\r\n");
			head.append(importClass).append(sb);
            Map<String, Object> varMap = new HashMap<String, Object>();
            varMap.put("tableBean", tableBean);
            varMap.put("schemaName", SCHEMA_NAME);
            varMap.put("packageBase", PACKAGE_BASE);
            varMap.put("classPrefix", CLASS_PREFIX);
            varMap.put("jdbcTemplateName", JDBC_TEMPLATE_NAME);
            varMap.put("jdbcTemplateNameCapitalized", StringUtil.capitalize(JDBC_TEMPLATE_NAME));
            System.out.println(head);
		}
		
	}
}
