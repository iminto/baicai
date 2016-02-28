package com.baicai.util.help;

public class ColumnBean {
    private String columnName;

    private String columnNameNoDash;

    private String columnNameCapitalized;

    private String columnType;

    private String columnTypeRsGetter;

    private String columnComment;

    private int length;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnNameNoDash() {
        return columnNameNoDash;
    }

    public void setColumnNameNoDash(String columnNameNoDash) {
        this.columnNameNoDash = columnNameNoDash;
    }

    public String getColumnNameCapitalized() {
        return columnNameCapitalized;
    }

    public void setColumnNameCapitalized(String columnNameCapitalized) {
        this.columnNameCapitalized = columnNameCapitalized;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnTypeRsGetter() {
        return columnTypeRsGetter;
    }

    public void setColumnTypeRsGetter(String columnTypeRsGetter) {
        this.columnTypeRsGetter = columnTypeRsGetter;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

	@Override
	public String toString() {
		return "ColumnBean [columnName=" + columnName + ", columnNameNoDash=" + columnNameNoDash
				+ ", columnNameCapitalized=" + columnNameCapitalized + ", columnType=" + columnType
				+ ", columnTypeRsGetter=" + columnTypeRsGetter + ", columnComment=" + columnComment + ", length="
				+ length + "]";
	}
    
    
}
