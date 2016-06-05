package com.baicai.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baicai.controller.common.BaseController;
/**
 * 
* @Description: 安全中心
* @author 猪肉有毒 waitfox@qq.com  
* @date 2016年6月5日 下午11:50:40 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
@Controller
@RequestMapping("/safecenter")
public class SafeCenterController  extends BaseController{
	
	@RequestMapping("/index")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		return "/views/safecenter/index";
	}
	
	@RequestMapping("/changephone")
	public String changephone(HttpServletRequest request, HttpServletResponse response) {
		return "/views/safecenter/changephone";
	}
	
	@RequestMapping("/bindemail")
	public String bindemail(HttpServletRequest request, HttpServletResponse response) {
		return "/views/safecenter/bindemail";
	}
	
	@RequestMapping("/realname")
	public String realname(HttpServletRequest request, HttpServletResponse response) {
		return "/views/safecenter/realname";
	}
}
