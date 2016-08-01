package com.baicai.domain.system.menu;

import com.baicai.corewith.data.Model;

/**
 * @Description: 角色表模型类
 * @author: waitfox@qq.com
 * @version: 0.01
 * @date: 2016/08/01 23:53:10
 */
public class SysRole extends Model {
	private Integer roleid;//
	private String rolename;// 角色名
	private String desc;// 简单说明
	private Integer flag;// 使用标记，1正常0禁用

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}
