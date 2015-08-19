package com.baicai.domain.assets;
import java.math.BigDecimal;

import com.baicai.annotation.Key;
import com.baicai.core.Model;

public class Assets extends Model{
	
	@Key
	private Long user_id;
	private BigDecimal total_money;
	private BigDecimal real_money;
	private BigDecimal frost_money;
	private BigDecimal have_interest;
	private BigDecimal wait_interest;
	private BigDecimal wait_total_money;
	private BigDecimal yuebao_money;
	private BigDecimal yuebao_income;
	private Integer yuebao_end_bearing_time;
	private BigDecimal exp_money;
	private BigDecimal exp_use_money;
	private String sign;
	private int is_warning;
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public BigDecimal getTotal_money() {
		return total_money;
	}
	public void setTotal_money(BigDecimal total_money) {
		this.total_money = total_money;
	}
	public BigDecimal getExp_money() {
		return exp_money;
	}
	public void setExp_money(BigDecimal exp_money) {
		this.exp_money = exp_money;
	}
	public BigDecimal getExp_use_money() {
		return exp_use_money;
	}
	public void setExp_use_money(BigDecimal exp_use_money) {
		this.exp_use_money = exp_use_money;
	}
	public BigDecimal getReal_money() {
		return real_money;
	}
	public void setReal_money(BigDecimal real_money) {
		this.real_money = real_money;
	}
	public BigDecimal getFrost_money() {
		return frost_money;
	}
	public void setFrost_money(BigDecimal frost_money) {
		this.frost_money = frost_money;
	}
	public BigDecimal getHave_interest() {
		return have_interest;
	}
	public void setHave_interest(BigDecimal have_interest) {
		this.have_interest = have_interest;
	}
	public BigDecimal getWait_interest() {
		return wait_interest;
	}
	public void setWait_interest(BigDecimal wait_interest) {
		this.wait_interest = wait_interest;
	}
	public BigDecimal getWait_total_money() {
		return wait_total_money;
	}
	public void setWait_total_money(BigDecimal wait_total_money) {
		this.wait_total_money = wait_total_money;
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
	public Integer getYuebao_end_bearing_time() {
		return yuebao_end_bearing_time;
	}
	public void setYuebao_end_bearing_time(Integer yuebao_end_bearing_time) {
		this.yuebao_end_bearing_time = yuebao_end_bearing_time;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public int getIs_warning() {
		return is_warning;
	}
	public void setIs_warning(int is_warning) {
		this.is_warning = is_warning;
	}
	
	
	
}
