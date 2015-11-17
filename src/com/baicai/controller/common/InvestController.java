package com.baicai.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class InvestController {
	
	@RequestMapping("/invest")
	public String invest(HttpServletRequest request,HttpServletResponse response){
		return "/views/index/invest";		
	}

}
