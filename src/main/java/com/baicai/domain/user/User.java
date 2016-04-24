package com.baicai.domain.user;

import com.baicai.annotation.Key;
import com.baicai.annotation.NotNull;
import com.baicai.annotation.ValidType;
import com.baicai.core.Model;

/**
 * @Description: 用户模型类
 * @author: waitfox@qq.com
 * @version: 0.01
 * @date: 2016/04/21 21:50:28
 */
public class User extends Model{
	@Key
	private Integer userId;// 用户id
	private String userName;// 用户登录名
	@ValidType(valid="password",message="密码字段不符合规范")
	private String loginPass;// 登陆密码
	private String payPass;// 支付密码
	@NotNull
	private String email;// 用户邮箱
	private String phone;// 用户手机
	private String userPic;// 用户头像
	private String realname;// 真实姓名
	private String userAddress;// 用户联系地址
	private Integer inviteUserId;// 父id/推荐人id
	private Integer userType;// 用户类型（0，普通注册用户；1，手机注册用户2，后台手动添加用户）
	private Integer isEmailCheck;// 邮箱认证（0，未认证；1已认证）
	private Integer isPhoneCheck;// 手机认证（0，未认证；1已认证）
	private Integer isRealnameCheck;// 实名认证（0，未认证；1已认证）
	private Integer isSafequestionCheck;// 密保问题（0，未设置；1，已设置）
	private Integer vipStopTime;// VIP到期时间
	private Integer isLock;// 账户锁定（0，正常；1，锁定）
	private Integer registerTime;// 注册时间
	private Integer loginTime;// 登录时间
	private String registerip;//
	private Integer invitenum;// 邀请人数
	
	public static final String loginRule="loginRules";
	public static final String[] loginRules={"userName","loginPass"};//登录时需要验证这些字段
	public static final String regRule="regRules";
	public static final String[] regRules={"userName","loginPass","email"};//注册时需要验证这些字段

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	public String getPayPass() {
		return payPass;
	}

	public void setPayPass(String payPass) {
		this.payPass = payPass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Integer getInviteUserId() {
		return inviteUserId;
	}

	public void setInviteUserId(Integer inviteUserId) {
		this.inviteUserId = inviteUserId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getIsEmailCheck() {
		return isEmailCheck;
	}

	public void setIsEmailCheck(Integer isEmailCheck) {
		this.isEmailCheck = isEmailCheck;
	}

	public Integer getIsPhoneCheck() {
		return isPhoneCheck;
	}

	public void setIsPhoneCheck(Integer isPhoneCheck) {
		this.isPhoneCheck = isPhoneCheck;
	}

	public Integer getIsRealnameCheck() {
		return isRealnameCheck;
	}

	public void setIsRealnameCheck(Integer isRealnameCheck) {
		this.isRealnameCheck = isRealnameCheck;
	}

	public Integer getIsSafequestionCheck() {
		return isSafequestionCheck;
	}

	public void setIsSafequestionCheck(Integer isSafequestionCheck) {
		this.isSafequestionCheck = isSafequestionCheck;
	}

	public Integer getVipStopTime() {
		return vipStopTime;
	}

	public void setVipStopTime(Integer vipStopTime) {
		this.vipStopTime = vipStopTime;
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public Integer getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Integer registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Integer loginTime) {
		this.loginTime = loginTime;
	}

	public String getRegisterip() {
		return registerip;
	}

	public void setRegisterip(String registerip) {
		this.registerip = registerip;
	}

	public Integer getInvitenum() {
		return invitenum;
	}

	public void setInvitenum(Integer invitenum) {
		this.invitenum = invitenum;
	}

}