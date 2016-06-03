package com.baicai.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baicai.controller.common.BaseController;
import com.baicai.core.BaseTool;
import com.baicai.domain.system.ErrorMsg;
import com.baicai.domain.user.User;
import com.baicai.service.user.UserService;
import com.baicai.util.CommonUtil;
import com.baicai.util.StringUtil;

@Controller
@RequestMapping("/site")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/views/user/login";
	}

	@RequestMapping("/reg")
	public String register(HttpServletRequest request, HttpServletResponse response) {
		return "/views/user/register";
	}

	@RequestMapping("/doreg")
	public String doRegister(HttpServletRequest request, HttpServletResponse response, User user) {
		Map<String, String> errorMap = new HashMap<String, String>();
		if (user.validate(User.regRule)) {// 做最基本的参数合法性校验
			if (userService.existUserByName(user.getUsername())) {
				errorMap.put("userName", "用户名已存在");
			} else if (userService.existUserByEmail(user.getEmail())) {
				errorMap.put("userName", "邮箱已存在");
			} else {
				long i = userService.saveUser(user);
			}
		} else {
			errorMap = user.getErrorMap();
		}
		if (errorMap.isEmpty()) {// 跳转到注册成功页
			request.setAttribute("userName", user.getUsername());
			return "/views/user/register2";
		} else {
			request.setAttribute("err", errorMap);
			return "/views/user/register";
		}
	}

	@RequestMapping("/dologin")
	public String doLogin(HttpServletRequest request, HttpServletResponse response, User user) {
		ErrorMsg errorMsg = new ErrorMsg();
		Cookie vcookie = BaseTool.getCookie(request, "Vali");
		String authcode = request.getParameter("valicode");// 验证码
		if (StringUtil.isBlank(user.getUsername()) || StringUtil.isBlank(user.getLoginpass())) {
			errorMsg.setErrMsg("用户名或密码不能为空");
			request.setAttribute("error", errorMsg);
			return "/views/public/msg";
		}
		if (StringUtil.isBlank(authcode) || vcookie == null) {
			errorMsg.setErrMsg("验证码错误！");
			request.setAttribute("error", errorMsg);
			return "/views/public/msg";
		}
		String authCookie = vcookie.getValue();
		// 判断输入的验证码是否正确
		boolean auth = CommonUtil.validate(authCookie, authcode.toUpperCase(), CommonUtil.VALICODE_SALT);
		vcookie.setMaxAge(0); // 判断完就删除验证码cookie，禁止重用验证码
		vcookie.setPath("/");
		response.addCookie(vcookie);
		if (auth == false) {
			errorMsg.setErrMsg("验证码错误！！");
			request.setAttribute("error", errorMsg);
			return "/views/public/msg";
		}
		User u = userService.login(user);
		if (u == null) {
			errorMsg.setErrMsg("用户名或密码错误");
			request.setAttribute("error", errorMsg);
			return "/views/public/msg";
		}
		String uidAndTime = u.getUserid() + "|" + System.currentTimeMillis() / 1000;
		String token = BaseTool.encryptCookieValue(uidAndTime);
		Cookie cookie = new Cookie("openid", token);
		cookie.setHttpOnly(true);// 不允许页面修改，增强安全性，不设置有效期，也即关机则失效
		cookie.setPath("/");
		response.addCookie(cookie);
		Cookie safecode = new Cookie("token", BaseTool.generatorSafeCode(token));// 对cookie加校验，防篡改
		safecode.setHttpOnly(true);
		safecode.setPath("/");
		response.addCookie(safecode);
		return redirectTo("/usercenter/home.do");
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie userLogin = BaseTool.getCookie(request, "openid");
		if (userLogin != null) {
			userLogin.setMaxAge(0); // 设置0就是删除
			userLogin.setHttpOnly(true);
			userLogin.setPath("/");
			response.addCookie(userLogin);
			userService.logout(BaseTool.getUidFromCookie(userLogin));
		}
		Cookie token = BaseTool.getCookie(request, "token");
		if (token != null) {
			token.setMaxAge(0); // 设置0就是删除
			token.setHttpOnly(true);
			token.setPath("/");
			response.addCookie(token);
		}

		return "/views/user/login";
	}

}
