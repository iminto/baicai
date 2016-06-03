package com.baicai.core.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
* @Description: 数据库异常
* @author 猪肉有毒 waitfox@qq.com  
* @date 2016年6月3日 下午10:27:36 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
public class SqlException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private static final Logger loger = LoggerFactory.getLogger(SqlException.class);
	
	public SqlException(String message) {
		super(message);
	}

	public SqlException(Exception e, String sql) {
		super("数据库运行期异常");
		e.printStackTrace();
		if (loger.isErrorEnabled()) {
			loger.error("数据库运行期异常，相关sql语句为" + sql);
			loger.error(e.getMessage());
		}
	}

	public SqlException(String message, String sql) {
		super(message);
		if (loger.isErrorEnabled()) {
			loger.error(message + "，相关sql语句为" + sql);
		}
	}

}
