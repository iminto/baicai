package com.baicai.service.project;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baicai.core.BaseDAO;
import com.baicai.core.DynamicDataSourceContextHolder.DS;
import com.baicai.core.TargetDataSource;
import com.baicai.dao.project.ProjectDAO;
import com.baicai.domain.project.Project;
import com.baicai.domain.system.Pagination;
@Service
public class ProjectService{
	@Autowired
	protected BaseDAO dao;
	@Autowired
	private ProjectDAO projectDAO;
	
	@TargetDataSource(DS.DATA_SOURCE_1)
	public  List<Project> findProjectsOnIndex(Integer cnt){
		return projectDAO.findProjectsOnIndex(cnt);
	}
	
	@SuppressWarnings("unchecked")
	public Pagination getProjectList(Integer page){
		Pagination pager=new Pagination();
		String sql_where = " 1  ";
		String countSQL="SELECT count(*)  FROM {project} WHERE "+sql_where;
		int count=dao.queryForInt(countSQL);
		pager.setTotal(count);
		pager.setPage(page);
		String sql="SELECT *  FROM {project} WHERE "+sql_where+" order by prostatus ASC LIMIT ?,?";
		List<Project> list=dao.queryForList(Project.class, sql,new Object[]{pager.getOffset(),pager.getPageSize()});
		pager.setList(list);	
		return pager;
	}
}
