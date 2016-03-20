package com.baicai.service.project;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baicai.core.BaseService;
import com.baicai.dao.project.ProjectDAO;
import com.baicai.domain.project.Project;
@Service
public class ProjectService extends BaseService{
	@Autowired
	private ProjectDAO projectDAO;
	
	public  List<Project> findProjectsOnIndex(Integer cnt){
		return projectDAO.findProjectsOnIndex(cnt);
	}
}
