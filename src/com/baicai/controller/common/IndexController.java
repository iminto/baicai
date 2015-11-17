package com.baicai.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		return "/views/index/index";		
	}
	
	@RequestMapping("/manage")
	public String main(HttpServletRequest request,HttpServletResponse response){
		return "/manage/login";		
	}

}
