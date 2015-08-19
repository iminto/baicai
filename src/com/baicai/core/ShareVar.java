package com.baicai.core;

import com.baicai.util.PropertiesTool;

/**
* @Description: 设置共享变量
* @author 猪肉有毒 waitfox@qq.com  
* @date 2015年8月9日 上午12:01:27 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
public class ShareVar {
	private  String version;
	private String salt;
	public String getVersion() {
		return  "1.0";
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSalt() {
		return PropertiesTool.get("system", "USER_SALT");
	}
	public void setSalt(String salt) {
		this.salt =salt;
	}
	
}
