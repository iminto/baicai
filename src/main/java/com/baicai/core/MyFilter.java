package com.baicai.core;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String path = request.getServletPath();
		String contextPath = request.getContextPath();
		System.out.println("过滤路径:" + path);
		req.setCharacterEncoding("utf-8");
		Cookie userLogin = BaseTool.getCookie(request, "userlogin");
		Cookie token=BaseTool.getCookie(request, "token");
		req.setAttribute("path", req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+request.getContextPath()+"/");
		if (path.indexOf("/user/login") != -1 || path.indexOf("/user/reg")!=-1||path.indexOf("/user/doreg")!=-1 ) {
			//如果说是登陆或注册的首页，允许通过
			chain.doFilter(req, res);
		} else if (path.indexOf("/user") != -1 || path.indexOf("/usercenter") != -1 || path.indexOf("/safecenter") != -1) {
			//如果不是登陆或注册的首页，但是又包含user的前缀
			if (userLogin == null || token==null||!BaseTool.validCookie(userLogin, token)) {
				response.sendRedirect(contextPath + "/user/login.jsp");
			} else {
				request.setAttribute("loginUid", BaseTool.encryptCookieValue(userLogin.getValue()));
				chain.doFilter(req, res);
			}
		} else {
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
