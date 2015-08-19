package com.baicai.domain.assets;
import java.math.BigDecimal;

import com.baicai.annotation.Column;
import com.baicai.annotation.Key;
import com.baicai.annotation.Table;
import com.baicai.core.Model;

public class Assets_cash extends Model{

	@Key
	private Long c_id;
	private Long c_user_id;
	private Integer c_status;
	private String c_cardNum;
	private Long c_bank;
	private String c_branch;
	private BigDecimal c_money;
	private BigDecimal c_realmoney;
	private BigDecimal c_fee;
	private Long c_verify_user;
	private Integer c_verify_time;
	private String c_verify_remark;
	private Integer c_addtime;
	private String c_addip;
	private String sign;
	private Integer is_warning;
	
	public static final String userRule = "userRules";
	public static final String[] userRules = {"c_cardNum","c_money"};
	
	public Long getC_id() {
		return c_id;
	}
	public void setC_id(Long c_id) {
		this.c_id = c_id;
	}
	public Long getC_user_id() {
		return c_user_id;
	}
	public void setC_user_id(Long c_user_id) {
		this.c_user_id = c_user_id;
	}
	public Integer getC_status() {
		return c_status;
	}
	public void setC_status(Integer c_status) {
		this.c_status = c_status;
	}
	public String getC_cardNum() {
		return c_cardNum;
	}
	public void setC_cardNum(String c_cardNum) {
		this.c_cardNum = c_cardNum;
	}
	public Long getC_bank() {
		return c_bank;
	}
	public void setC_bank(Long c_bank) {
		this.c_bank = c_bank;
	}
	public String getC_branch() {
		return c_branch;
	}
	public void setC_branch(String c_branch) {
		this.c_branch = c_branch;
	}
	public BigDecimal getC_money() {
		return c_money;
	}
	public void setC_money(BigDecimal c_money) {
		this.c_money = c_money;
	}
	public BigDecimal getC_realmoney() {
		return c_realmoney;
	}
	public void setC_realmoney(BigDecimal c_realmoney) {
		this.c_realmoney = c_realmoney;
	}
	public BigDecimal getC_fee() {
		return c_fee;
	}
	public void setC_fee(BigDecimal c_fee) {
		this.c_fee = c_fee;
	}
	public Long getC_verify_user() {
		return c_verify_user;
	}
	public void setC_verify_user(Long c_verify_user) {
		this.c_verify_user = c_verify_user;
	}
	public Integer getC_verify_time() {
		return c_verify_time;
	}
	public void setC_verify_time(Integer c_verify_time) {
		this.c_verify_time = c_verify_time;
	}
	public String getC_verify_remark() {
		return c_verify_remark;
	}
	public void setC_verify_remark(String c_verify_remark) {
		this.c_verify_remark = c_verify_remark;
	}
	public Integer getC_addtime() {
		return c_addtime;
	}
	public void setC_addtime(Integer c_addtime) {
		this.c_addtime = c_addtime;
	}
	public String getC_addip() {
		return c_addip;
	}
	public void setC_addip(String c_addip) {
		this.c_addip = c_addip;
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
	
}
