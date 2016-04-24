package com.baicai.controller.user;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baicai.domain.user.User;
import com.baicai.service.user.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

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
			if(userService.existUserByName(user.getUserName())){
				errorMap.put("userName", "用户名已存在");
			}else if(userService.existUserByEmail(user.getEmail())){
				errorMap.put("userName", "邮箱已存在");
			}else{
				int i=userService.saveUser(user);
			}
		} else {
			errorMap = user.getErrorMap();
		}
		request.setAttribute("err", errorMap);
		return "/views/user/register";
	}

}
