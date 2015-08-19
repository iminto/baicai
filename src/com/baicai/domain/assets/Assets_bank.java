package com.baicai.domain.assets;
import com.baicai.annotation.Key;
import com.baicai.core.Model;

public class Assets_bank extends Model{

	@Key
	private Long b_id;
	private Long b_user_id;
	private Integer b_status;
	private String b_cardNum;
	private Long b_bank;
	private String b_branch;
	private String b_city;
	private String b_province;
	private String sign;
	private Integer is_warning;
	private Integer b_addtime;
	private String b_addip;
	
	public static final String userRule = "userRules" ;
	public static final String[] userRules = {"b_bank","b_branch","b_cardNum"};
	
	public Long getB_id() {
		return b_id;
	}
	public void setB_id(Long b_id) {
		this.b_id = b_id;
	}
	public Long getB_user_id() {
		return b_user_id;
	}
	public void setB_user_id(Long b_user_id) {
		this.b_user_id = b_user_id;
	}
	public Integer getB_status() {
		return b_status;
	}
	public void setB_status(Integer b_status) {
		this.b_status = b_status;
	}
	public String getB_cardNum() {
		return b_cardNum;
	}
	public void setB_cardNum(String b_cardNum) {
		this.b_cardNum = b_cardNum;
	}
	public Long getB_bank() {
		return b_bank;
	}
	public void setB_bank(Long b_bank) {
		this.b_bank = b_bank;
	}
	public String getB_branch() {
		return b_branch;
	}
	public void setB_branch(String b_branch) {
		this.b_branch = b_branch;
	}
	public Integer getB_addtime() {
		return b_addtime;
	}
	public void setB_addtime(Integer b_addtime) {
		this.b_addtime = b_addtime;
	}
	public String getB_addip() {
		return b_addip;
	}
	public void setB_addip(String b_addip) {
		this.b_addip = b_addip;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public Integer getIs_warning() {
		return is_warning;
	}
	public void setIs_warning(Integer is_warning) {
		this.is_warning = is_warning;
	}
	public String getB_city() {
		return b_city;
	}
	public void setB_city(String b_city) {
		this.b_city = b_city;
	}
	public String getB_province() {
		return b_province;
	}
	public void setB_province(String b_province) {
		this.b_province = b_province;
	}
	@Override
	public String toString() {
		return "Assets_bank [b_id=" + b_id + ", b_user_id=" + b_user_id
				+ ", b_status=" + b_status + ", b_cardNum=" + b_cardNum
				+ ", b_bank=" + b_bank + ", b_branch=" + b_branch
				+ ", b_addtime=" + b_addtime + ", b_addip=" + b_addip + "]";
	}
	
	
	
}
