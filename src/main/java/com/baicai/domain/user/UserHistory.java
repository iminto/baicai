package com.baicai.domain.user;

import java.util.Date;

import com.baicai.annotation.Table;
/**
 * @Description: 用户操作记录表模型类
 * @author: waitfox@qq.com
 * @version: 0.01
 * @date: 2016/05/22 01:36:04
 */
@Table(name = "user_history")
public class UserHistory {
	private Integer id;//
	private Integer uid;//用户ID
	private String action;//用户操作
	private String detaill;//操作细节
	private Date addtime;//操作时间
	private String addip;//操作IP
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDetaill() {
		return detaill;
	}
	public void setDetaill(String detaill) {
		this.detaill = detaill;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public String getAddip() {
		return addip;
	}
	public void setAddip(String addip) {
		this.addip = addip;
	}
	
	
}
