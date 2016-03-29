package com.baicai.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baicai.domain.system.Pagination;
import com.baicai.service.project.ProjectService;
import com.baicai.util.NumberHelper;

@Controller
@RequestMapping
public class InvestController {
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping("/invest")
	public String invest(HttpServletRequest request,HttpServletResponse response){
		Integer page=NumberHelper.str2int(request.getParameter("page"));
		Pagination proJectList=projectService.getProjectList(page);
		request.setAttribute("pages", proJectList);
		return "/views/index/invest";		
	}

}
