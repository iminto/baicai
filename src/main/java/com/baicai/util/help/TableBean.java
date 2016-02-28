package com.baicai.util.help;

import java.util.ArrayList;
import java.util.List;

public class TableBean {
    private String tableName;

    private String tableComment;

    private String tableNameNoDash;

    private String tableNameCapitalized;

    private List<ColumnBean> columnBeanList = new ArrayList<ColumnBean>();

    private boolean hasDateColumn;

    private boolean hasBigDecimal;

    public void addColumn(ColumnBean columnBean) {
        columnBeanList.add(columnBean);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public List<ColumnBean> getColumnBeanList() {
        return columnBeanList;
    }

    public void setColumnBeanList(List<ColumnBean> columnBeanList) {
        this.columnBeanList = columnBeanList;
    }

    public String getTableNameNoDash() {
        return tableNameNoDash;
    }

    public void setTableNameNoDash(String tableNameNoDash) {
        this.tableNameNoDash = tableNameNoDash;
    }

    public String getTableNameCapitalized() {
        return tableNameCapitalized;
    }

    public void setTableNameCapitalized(String tableNameCapitalized) {
        this.tableNameCapitalized = tableNameCapitalized;
    }

    public boolean isHasDateColumn() {
        return hasDateColumn;
    }

    public void setHasDateColumn(boolean hasDateColumn) {
        this.hasDateColumn = hasDateColumn;
    }

    public boolean isHasBigDecimal() {
        return hasBigDecimal;
    }

    public void setHasBigDecimal(boolean hasBigDecimal) {
        this.hasBigDecimal = hasBigDecimal;
    }

	@Override
	public String toString() {
		return "TableBean [tableName=" + tableName + ", tableComment=" + tableComment + ", tableNameNoDash="
				+ tableNameNoDash + ", tableNameCapitalized=" + tableNameCapitalized + ", columnBeanList="
				+ columnBeanList + ", hasDateColumn=" + hasDateColumn + ", hasBigDecimal=" + hasBigDecimal + "]";
	}
    
    
}
