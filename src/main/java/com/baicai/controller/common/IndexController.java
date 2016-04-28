package com.baicai.controller.common;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baicai.domain.project.Project;
import com.baicai.service.project.ProjectService;

@Controller
@RequestMapping
public class IndexController {
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		List<Project> proJectList=projectService.findProjectsOnIndex(6);
		request.setAttribute("proList", proJectList);
		return "/views/index/index";
	}
	
	@RequestMapping("/manage")
	public String main(HttpServletRequest request,HttpServletResponse response){
		return "/manage/login";		
	}

}
