package com.baicai.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baicai.domain.user.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		return "/views/user/login";
	}

	@RequestMapping("/reg")
	public String register(HttpServletRequest request, HttpServletResponse response) {
		return "/views/user/register";
	}

	@RequestMapping("/doreg")
	public String doRegister(HttpServletRequest request, HttpServletResponse response, User user) {
		Map<String, String> errorMap = new HashMap<String, String>();
		if (user.validate(User.regRule)) {
			user.save();
		} else {
			errorMap = user.getErrorMap();
		}
		request.setAttribute("errorMap", errorMap);
		return "/views/user/register";
	}

}
