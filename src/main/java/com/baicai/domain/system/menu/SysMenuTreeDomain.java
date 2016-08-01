package com.baicai.domain.system.menu;

import java.util.ArrayList;
import java.util.List;

public class SysMenuTreeDomain extends SysMenu {
	private List<SysMenuTreeDomain> child=new ArrayList<>();

	public List<SysMenuTreeDomain> getChild() {
		return child;
	}

	public void setChild(List<SysMenuTreeDomain> child) {
		this.child = child;
	}
	
}
