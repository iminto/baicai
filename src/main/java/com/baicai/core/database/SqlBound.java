package com.baicai.core.database;
/**
 * 
* @Description: SQL组装的容器
* @author 猪肉有毒 waitfox@qq.com  
* @date 2016年6月3日 下午9:17:32 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
public class SqlBound {
	private String sql;
	private Object[] param;

	public SqlBound() {
	}

	public SqlBound(String sql, Object[] param) {
		super();
		this.sql = sql;
		this.param = param;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Object[] getParam() {
		return param;
	}

	public void setParam(Object[] param) {
		this.param = param;
	}

}
