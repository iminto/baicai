package com.baicai.controller.index;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baicai.controller.common.BaseController;
import com.baicai.domain.project.Project;
import com.baicai.domain.system.ErrorMsg;
import com.baicai.domain.system.Pagination;
import com.baicai.service.project.ProjectService;
import com.baicai.util.NumberHelper;

@Controller
@RequestMapping
public class InvestController extends BaseController{
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping("/invest")
	public String invest(HttpServletRequest request,HttpServletResponse response){
		Integer page=NumberHelper.str2int(request.getParameter("page"));
		Pagination proJectList=projectService.getProjectList(page);
		request.setAttribute("pages", proJectList);
		return "/views/index/invest";		
	}
	
	@RequestMapping("/view/{bid}")
	public String view(HttpServletRequest request,HttpServletResponse response,@PathVariable String bid){
		ErrorMsg errorMsg = new ErrorMsg();
		long proid=NumberHelper.str2long(bid);
		Project project=null;
		if(proid!=0){
			project=projectService.findProjectByproId(proid);
		}
		if(project==null){
			errorMsg.setErrMsg("标号不存在");
			request.setAttribute("error", errorMsg);
			return "/views/public/msg";
		}
		request.setAttribute("project_info", project);
		return "/views/index/view";
	}
	
	@RequestMapping("/borrow")
	public String borrow(HttpServletRequest request,HttpServletResponse response){
		return "/views/index/borrow";		
	}

}
