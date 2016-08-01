package com.baicai.service.system;

import java.util.ArrayList;
import java.util.List;
import com.baicai.domain.system.menu.SysMenu;
import com.baicai.domain.system.menu.SysMenuTreeDomain;

public class MenuService {
	
	public List<SysMenu> getAllMenus() {
		List<SysMenu> menus=new ArrayList<>();//待查取
		return menus;
	}
	
	/**
     * 递归获取树形菜单
     */
	public List<SysMenuTreeDomain> getAllMenusRecursion() {
		List<SysMenu> menus=getAllMenus();
		List<SysMenuTreeDomain> trees = new ArrayList<>();
		for (SysMenu m:menus ){
            SysMenuTreeDomain tree=new SysMenuTreeDomain();
            tree.setId(m.getId());
            tree.setMenuname(m.getMenuname());
            tree.setPid(m.getPid());
            List<SysMenuTreeDomain> c = new ArrayList<>();
            tree.setChild(c);
            tree.setUrl(m.getUrl());
            tree.setIconurl(m.getIconurl());
            tree.setFlag(m.getFlag());
            trees.add(tree);
        }
		for (SysMenuTreeDomain t:trees){
            if(t.getPid()==0){
                getAllMenusRecursion(trees,t);
            }
        }
        return trees;
	}
	
	public SysMenuTreeDomain getAllMenusRecursion(List<SysMenuTreeDomain> menuList, SysMenuTreeDomain d){
		List<SysMenuTreeDomain> children = new ArrayList<>();
        for (SysMenuTreeDomain m:menuList){
            if(m.getPid()!=null && m.getPid()==d.getId()){
                getAllMenusRecursion(menuList,m);
                children.add(m);
            }
        }
        d.setChild(children);
        return d;
	}
}
