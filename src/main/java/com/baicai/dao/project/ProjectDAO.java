package com.baicai.dao.project;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baicai.core.BaseDAO;
import com.baicai.core.DaoUtil;
import com.baicai.domain.project.Project;
@Component
public class ProjectDAO {
	@Autowired
	private BaseDAO<Project> dao;
	
	public Project findProjectByid(Integer id){
		String sql="SELECT * FROM {project} WHERE id=?";
		return dao.queryForBean(Project.class, DaoUtil.format(sql), new Object[]{id});
	}
	
	public Project findProjectByproId(Long proId){
		String sql="SELECT * FROM {project} WHERE proId=?";
		return dao.queryForBean(Project.class, DaoUtil.format(sql), new Object[]{proId});
	}
	
	/**
	 * 返回首页需要展示的标，按照剩余金额排序
	 * @param cnt
	 * @return
	 */
	public List<Project> findProjectsOnIndex(Integer cnt){
		String sql="SELECT p.*,p.proAccount-p.proAccountYes remainAmount FROM p2p_project p";
		sql+=" WHERE p.proStatus=1 ORDER BY remainAmount DESC LIMIT ?";
		List<Project> list=new ArrayList<>();
		list=dao.queryForList(Project.class, DaoUtil.format(sql), new Object[]{cnt});
		return list;
	}
}
