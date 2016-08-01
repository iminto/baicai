package com.baicai.domain.system.menu;
import com.baicai.corewith.data.Model;
/**
 * @Description: 菜单表模型类
 * @author: waitfox@qq.com
 * @version: 0.01
 * @date: 2016/08/01 23:49:47
 */
public class SysMenu extends Model{
	private Integer id;//菜单ID
	private String menuname;//菜单名
	private Integer pid;//父节点
	private String url;//菜单连接
	private String iconurl;//icon图标地址或样式
	private String desc;//简单说明
	private Integer flag;//使用标记，1正常0禁用
	private String path;//路径表
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIconurl() {
		return iconurl;
	}
	public void setIconurl(String iconurl) {
		this.iconurl = iconurl;
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
