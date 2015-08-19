package com.baicai.domain.assets;
import java.math.BigDecimal;

import com.baicai.annotation.Column;
import com.baicai.annotation.Key;
import com.baicai.annotation.Table;
import com.baicai.core.Model;

public class Bill extends Model{

	@Key
	private Long b_id;
	private Long user_id;
	private BigDecimal b_money;
	private Integer b_type;
	private String b_itemtype;
	private BigDecimal u_total_money;
	private BigDecimal u_real_money;
	private BigDecimal u_frost_money;
	private BigDecimal u_have_interest;
	private BigDecimal u_wait_interest;
	private BigDecimal u_wait_total_money;
	private BigDecimal yuebao_money;
	private BigDecimal yuebao_income;
	private BigDecimal exp_money;
	private Long b_mark;
	private Integer b_time;
	private String remark;
	private String b_addip;
	private String sign;
	private Integer is_warning;
	
	public Long getB_id() {
		return b_id;
	}
	public void setB_id(Long b_id) {
		this.b_id = b_id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public BigDecimal getB_money() {
		return b_money;
	}
	public void setB_money(BigDecimal b_money) {
		this.b_money = b_money;
	}
	public Integer getB_type() {
		return b_type;
	}
	public void setB_type(Integer b_type) {
		this.b_type = b_type;
	}
	public String getB_itemtype() {
		return b_itemtype;
	}
	public void setB_itemtype(String b_itemtype) {
		this.b_itemtype = b_itemtype;
	}
	public BigDecimal getU_total_money() {
		return u_total_money;
	}
	public void setU_total_money(BigDecimal u_total_money) {
		this.u_total_money = u_total_money;
	}
	public BigDecimal getU_real_money() {
		return u_real_money;
	}
	public void setU_real_money(BigDecimal u_real_money) {
		this.u_real_money = u_real_money;
	}
	public BigDecimal getU_frost_money() {
		return u_frost_money;
	}
	public void setU_frost_money(BigDecimal u_frost_money) {
		this.u_frost_money = u_frost_money;
	}
	public BigDecimal getU_have_interest() {
		return u_have_interest;
	}
	public void setU_have_interest(BigDecimal u_have_interest) {
		this.u_have_interest = u_have_interest;
	}
	public BigDecimal getU_wait_interest() {
		return u_wait_interest;
	}
	public void setU_wait_interest(BigDecimal u_wait_interest) {
		this.u_wait_interest = u_wait_interest;
	}
	public BigDecimal getU_wait_total_money() {
		return u_wait_total_money;
	}
	public void setU_wait_total_money(BigDecimal u_wait_total_money) {
		this.u_wait_total_money = u_wait_total_money;
	}
	public BigDecimal getYuebao_money() {
		return yuebao_money;
	}
	public void setYuebao_money(BigDecimal yuebao_money) {
		this.yuebao_money = yuebao_money;
	}
	public BigDecimal getYuebao_income() {
		return yuebao_income;
	}
	public void setYuebao_income(BigDecimal yuebao_income) {
		this.yuebao_income = yuebao_income;
	}
	public Long getB_mark() {
		return b_mark;
	}
	public void setB_mark(Long b_mark) {
		this.b_mark = b_mark;
	}
	public BigDecimal getExp_money() {
		return exp_money;
	}
	public void setExp_money(BigDecimal exp_money) {
		this.exp_money = exp_money;
	}
	public Integer getB_time() {
		return b_time;
	}
	public void setB_time(Integer b_time) {
		this.b_time = b_time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	
}
