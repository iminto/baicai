package com.baicai.domain.system.menu;
import com.baicai.corewith.data.Model;
/**
 * @Description: 菜单角色关联表模型类
 * @author: waitfox@qq.com
 * @version: 0.01
 * @date: 2016/08/01 23:55:51
 */
public class SystemMenurole extends Model{
	private Integer id;//
	private Integer menuid;//菜单
	private Integer roleid;//角色
	private Integer uid;//用户，待扩展
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMenuid() {
		return menuid;
	}
	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
}
