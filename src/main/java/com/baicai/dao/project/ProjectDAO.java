package com.baicai.dao.project;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baicai.core.database.BaseDAO;
import com.baicai.domain.project.Project;
@Component
public class ProjectDAO {
	@Autowired
	private BaseDAO<Project> dao;
	
	public Project findProjectByid(Integer id){
		String sql="SELECT * FROM {project} WHERE id=?";
		return dao.queryForBean(Project.class, sql, new Object[]{id});
	}
	
	public Project findProjectByproId(Long proId){
		String sql="SELECT * FROM {project} WHERE proid=?";
		return dao.queryForBean(Project.class, sql, new Object[]{proId});
	}
	
	/**
	 * 返回首页需要展示的标，按照剩余金额排序
	 * @param cnt
	 * @return
	 */
	public List<Project> findProjectsOnIndex(Integer cnt){
		String sql="SELECT p.*,p.proaccount-p.proaccountyes remainamount FROM {project} p";
		sql+=" WHERE p.prostatus=1 ORDER BY remainamount DESC LIMIT ?";
		List<Project> list=new ArrayList<>();
		list=dao.queryForList(Project.class, sql, new Object[]{cnt});
		return list;
	}
}
