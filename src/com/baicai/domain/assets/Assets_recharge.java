package com.baicai.domain.assets;
import java.math.BigDecimal;

import com.baicai.annotation.Column;
import com.baicai.annotation.Key;
import com.baicai.annotation.NotNull;
import com.baicai.annotation.Table;
import com.baicai.annotation.ValidType;
import com.baicai.core.Model;

public class Assets_recharge extends Model{

	@Key
	private Long r_id;
	private String r_BillNo;
	private Long r_user_id;
	private Integer r_status;
	@NotNull(message="充值金额不能为空")
	private BigDecimal r_money;
	private BigDecimal r_realmoney;
	private BigDecimal r_fee;
	private Integer r_type;
	@NotNull(message="充值方式不能为空")
	private String r_recharge_type;
	private String r_return;
	private Long r_verify_user;
	private Integer r_verify_time;
	private String r_verify_remark;
	private Integer r_addtime;
	private String r_addip;
	private String sign;
	private Integer is_warning;
	
	public static final String userRule = "userRules";
	public static final String[] userRules = {"r_money","r_recharge_type"};
	
	public Long getR_id() {
		return r_id;
	}
	public void setR_id(Long r_id) {
		this.r_id = r_id;
	}
	public String getR_BillNo() {
		return r_BillNo;
	}
	public void setR_BillNo(String r_BillNo) {
		this.r_BillNo = r_BillNo;
	}
	public Long getR_user_id() {
		return r_user_id;
	}
	public void setR_user_id(Long r_user_id) {
		this.r_user_id = r_user_id;
	}
	public Integer getR_status() {
		return r_status;
	}
	public void setR_status(Integer r_status) {
		this.r_status = r_status;
	}
	public BigDecimal getR_money() {
		return r_money;
	}
	public void setR_money(BigDecimal r_money) {
		this.r_money = r_money;
	}
	public BigDecimal getR_realmoney() {
		return r_realmoney;
	}
	public void setR_realmoney(BigDecimal r_realmoney) {
		this.r_realmoney = r_realmoney;
	}
	public BigDecimal getR_fee() {
		return r_fee;
	}
	public void setR_fee(BigDecimal r_fee) {
		this.r_fee = r_fee;
	}
	public Integer getR_type() {
		return r_type;
	}
	public void setR_type(Integer r_type) {
		this.r_type = r_type;
	}
	public String getR_recharge_type() {
		return r_recharge_type;
	}
	public void setR_recharge_type(String r_recharge_type) {
		this.r_recharge_type = r_recharge_type;
	}
	public String getR_return() {
		return r_return;
	}
	public void setR_return(String r_return) {
		this.r_return = r_return;
	}
	public Long getR_verify_user() {
		return r_verify_user;
	}
	public void setR_verify_user(Long r_verify_user) {
		this.r_verify_user = r_verify_user;
	}
	public Integer getR_verify_time() {
		return r_verify_time;
	}
	public void setR_verify_time(Integer r_verify_time) {
		this.r_verify_time = r_verify_time;
	}
	public String getR_verify_remark() {
		return r_verify_remark;
	}
	public void setR_verify_remark(String r_verify_remark) {
		this.r_verify_remark = r_verify_remark;
	}
	public Integer getR_addtime() {
		return r_addtime;
	}
	public void setR_addtime(Integer r_addtime) {
		this.r_addtime = r_addtime;
	}
	public String getR_addip() {
		return r_addip;
	}
	public void setR_addip(String r_addip) {
		this.r_addip = r_addip;
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
