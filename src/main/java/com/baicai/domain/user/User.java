package com.baicai.domain.user;
import com.baicai.annotation.Key;
import com.baicai.annotation.ValidType;
import com.baicai.core.Model;

public class User extends Model{
	
	@Key
	private Long user_id;
	private String user_name;
	@ValidType(valid="password")
	private String login_pass;
	private String pay_pass;
	@ValidType(valid = "email")
	private String user_email;
	@ValidType(valid="mobile")
	private String user_phone;
	private String home_tel;
	private String user_qq;
	private String user_pic;
	private String real_name;
	private String card_num;
	private Integer user_sex;
	private Integer user_age;
	private Integer user_edu;
	private String birth_place;
	private String live_place;
	private String user_address;
	private Long p_user_id;
	private Integer user_type;
	private Integer is_email_check;
	private Integer is_phone_check;
	private Integer is_realname_check;
	private Integer vip_stop_time;
	private Integer is_hook;
	private Integer register_time;
	private Integer login_time;
	private String register_ip;
	
	public static final String loginRule="loginRules";
	public static final String[] loginRules={"user_name","login_pass"};//登录时需要验证这些字段
	public static final String regRule="regRules";
	public static final String[] regRules={"user_name","login_pass"};//注册时需要验证这些字段
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getLogin_pass() {
		return login_pass;
	}
	public void setLogin_pass(String login_pass) {
		this.login_pass = login_pass;
	}
	public String getPay_pass() {
		return pay_pass;
	}
	public void setPay_pass(String pay_pass) {
		this.pay_pass = pay_pass;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getHome_tel() {
		return home_tel;
	}
	public void setHome_tel(String home_tel) {
		this.home_tel = home_tel;
	}
	public String getUser_qq() {
		return user_qq;
	}
	public void setUser_qq(String user_qq) {
		this.user_qq = user_qq;
	}
	public String getUser_pic() {
		return user_pic;
	}
	public void setUser_pic(String user_pic) {
		this.user_pic = user_pic;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getCard_num() {
		return card_num;
	}
	public void setCard_num(String card_num) {
		this.card_num = card_num;
	}
	public Integer getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(Integer user_sex) {
		this.user_sex = user_sex;
	}
	public Integer getUser_age() {
		return user_age;
	}
	public void setUser_age(Integer user_age) {
		this.user_age = user_age;
	}
	public Integer getUser_edu() {
		return user_edu;
	}
	public void setUser_edu(Integer user_edu) {
		this.user_edu = user_edu;
	}
	public String getBirth_place() {
		return birth_place;
	}
	public void setBirth_place(String birth_place) {
		this.birth_place = birth_place;
	}
	public String getLive_place() {
		return live_place;
	}
	public void setLive_place(String live_place) {
		this.live_place = live_place;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public Long getP_user_id() {
		return p_user_id;
	}
	public void setP_user_id(Long p_user_id) {
		this.p_user_id = p_user_id;
	}
	public Integer getUser_type() {
		return user_type;
	}
	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}
	public Integer getIs_email_check() {
		return is_email_check;
	}
	public void setIs_email_check(Integer is_email_check) {
		this.is_email_check = is_email_check;
	}
	public Integer getIs_phone_check() {
		return is_phone_check;
	}
	public void setIs_phone_check(Integer is_phone_check) {
		this.is_phone_check = is_phone_check;
	}
	public Integer getIs_realname_check() {
		return is_realname_check;
	}
	public void setIs_realname_check(Integer is_realname_check) {
		this.is_realname_check = is_realname_check;
	}
	public Integer getVip_stop_time() {
		return vip_stop_time;
	}
	public void setVip_stop_time(Integer vip_stop_time) {
		this.vip_stop_time = vip_stop_time;
	}
	public Integer getIs_hook() {
		return is_hook;
	}
	public void setIs_hook(Integer is_hook) {
		this.is_hook = is_hook;
	}
	public Integer getRegister_time() {
		return register_time;
	}
	public void setRegister_time(Integer register_time) {
		this.register_time = register_time;
	}
	public Integer getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Integer login_time) {
		this.login_time = login_time;
	}
	public String getRegister_ip() {
		return register_ip;
	}
	public void setRegister_ip(String register_ip) {
		this.register_ip = register_ip;
	}
	
}
