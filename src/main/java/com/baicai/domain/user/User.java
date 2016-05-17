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
	private Integer userid;// 用户id
	private String username;// 用户登录名
	@ValidType(valid="password",message="密码字段不符合规范")
	private String loginpass;// 登陆密码
	private String paypass;// 支付密码
	@NotNull
	private String email;// 用户邮箱
	private String phone;//用户手机
	private String userpic;//用户头像
	private String realname;//真实姓名
	private String useraddress;//用户联系地址
	private Integer inviteuserid;//父id/推荐人id
	private Integer usertype;//用户类型（0，普通注册用户；1，手机注册用户2，后台手动添加用户）
	private Integer emailcheck;//邮箱认证（0，未认证；1已认证）
	private Integer phonecheck;//手机认证（0，未认证；1已认证）
	private Integer realnamecheck;//实名认证（0，未认证；1已认证）
	private Integer safequestioncheck;//密保问题（0，未设置；1，已设置）
	private Integer vipstoptime;//VIP到期时间
	private Integer islock;//账户锁定（0，正常；1，锁定）
	private Integer registertime;//注册时间
	private Integer logintime;//登录时间
	private String registerip;//
	private Integer invitenum;//邀请人数
	
	public static final String loginRule="loginRules";
	public static final String[] loginRules={"username","loginpass"};//登录时需要验证这些字段
	public static final String regRule="regRules";
	public static final String[] regRules={"username","loginpass","email"};//注册时需要验证这些字段
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLoginpass() {
		return loginpass;
	}
	public void setLoginpass(String loginpass) {
		this.loginpass = loginpass;
	}
	public String getPaypass() {
		return paypass;
	}
	public void setPaypass(String paypass) {
		this.paypass = paypass;
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
	public String getUserpic() {
		return userpic;
	}
	public void setUserpic(String userpic) {
		this.userpic = userpic;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getUseraddress() {
		return useraddress;
	}
	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}
	public Integer getInviteuserid() {
		return inviteuserid;
	}
	public void setInviteuserid(Integer inviteuserid) {
		this.inviteuserid = inviteuserid;
	}
	public Integer getUsertype() {
		return usertype;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	public Integer getEmailcheck() {
		return emailcheck;
	}
	public void setEmailcheck(Integer emailcheck) {
		this.emailcheck = emailcheck;
	}
	public Integer getPhonecheck() {
		return phonecheck;
	}
	public void setPhonecheck(Integer phonecheck) {
		this.phonecheck = phonecheck;
	}
	public Integer getRealnamecheck() {
		return realnamecheck;
	}
	public void setRealnamecheck(Integer realnamecheck) {
		this.realnamecheck = realnamecheck;
	}
	public Integer getSafequestioncheck() {
		return safequestioncheck;
	}
	public void setSafequestioncheck(Integer safequestioncheck) {
		this.safequestioncheck = safequestioncheck;
	}
	public Integer getVipstoptime() {
		return vipstoptime;
	}
	public void setVipstoptime(Integer vipstoptime) {
		this.vipstoptime = vipstoptime;
	}
	public Integer getIslock() {
		return islock;
	}
	public void setIslock(Integer islock) {
		this.islock = islock;
	}
	public Integer getRegistertime() {
		return registertime;
	}
	public void setRegistertime(Integer registertime) {
		this.registertime = registertime;
	}
	public Integer getLogintime() {
		return logintime;
	}
	public void setLogintime(Integer logintime) {
		this.logintime = logintime;
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