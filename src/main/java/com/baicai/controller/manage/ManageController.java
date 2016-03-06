package com.baicai.controller.manage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class ManageController {
	
	@RequestMapping("/pages/{page}")
	public String pages(HttpServletRequest request,HttpServletResponse response, @PathVariable String page){
		return "/manage/"+page;		
	}
	
	@RequestMapping("/menu/{page}")
	public String menus(HttpServletRequest request,HttpServletResponse response, @PathVariable String page){
		return "/manage/menu/"+page;		
	}

}
