package com.baicai.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baicai.controller.common.BaseController;
/**
 * 
* @Description: 用户中心控制器
* @author 猪肉有毒 waitfox@qq.com  
* @date 2016年5月7日 下午9:59:29 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
@Controller
@RequestMapping("/usercenter")
public class UserCenterController extends BaseController {
	
	@RequestMapping("/home")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		return "/views/usercenter/home";
	}

}
