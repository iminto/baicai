package com.baicai.util.help;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.baicai.util.StringUtil;


/**
* @Description: Model代码生成
* @author 猪肉有毒 waitfox@qq.com  
* @date 2015年8月22日 下午4:46:21 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
*/
public class Generator {
	private static JdbcTemplate jdbcTemplate = (JdbcTemplate) new FileSystemXmlApplicationContext("F:/data/eclipse/p2p/target/p2p/WEB-INF/classes/config/applicationContext-core.xml").getBean("jdbcTemplate");
//	private static JdbcTemplate jdbcTemplate = (JdbcTemplate)SpringUtils.getApplicationContext().getBean("jdbcTemplate");
	/**
     * 数据库名
     */
    public static final String SCHEMA_NAME = "test";

    /**
     * 输出路径绝对地址
     */
    public static final String OUTPUT_PATH = "G:/tmp/";

    /**
     * 生成代码的package前缀
     */
    public static final String PACKAGE_BASE = "com.baicai.domain";
    /**
     * 生成类的前缀，如xxxSimpleDAO中的Simple
     */
    public static final String CLASS_PREFIX = "Simple";
    
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
    
    public static TableBean getTable(String tableName) {
    	List<TableBean> tableBeanList = jdbcTemplate.query("select * from information_schema.tables where table_schema = ? AND TABLE_NAME=?", new Object[]{SCHEMA_NAME,tableName},
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
    	TableBean tb=tableBeanList.get(0);
    	tb.setColumnBeanList(getColumns(tb.getTableName(),tb));
    	return tb;
    	
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
                        } else if (columnType.startsWith("tinyint") || columnType.startsWith("smallint") || columnType.startsWith("mediumint")||columnType.startsWith("int") ) {
                            bean.setColumnType("Integer");
                            bean.setColumnTypeRsGetter("getInt");
                        } else if (columnType.startsWith("bigint")) {
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
    
}
