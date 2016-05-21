package com.baicai.domain.system;
/**
 * 
* @Description: 记录用户操作，用于安全审核
* @author 猪肉有毒 waitfox@qq.com  
* @date 2016年5月22日 上午1:18:28 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
public enum UserAction {
	Login("login"),//登陆
	Logout("logout"),//退出登录
	ChangePwd("changepwd"),//修改密码
	Browser("browser");//正常浏览
	
	private String action;
	
	private UserAction(String action){
		this.setAction(action);
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
