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
/**
 * 前台过滤器，目前只处理前台业务
* @Description: TODO
* @author 猪肉有毒 waitfox@qq.com  
* @date 2016年6月2日 下午11:17:10 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
public class FrontFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String path = request.getServletPath();
		String contextPath = request.getContextPath();
		req.setCharacterEncoding("utf-8");
		Cookie userLogin = BaseTool.getCookie(request, "openid");
		Cookie token=BaseTool.getCookie(request, "token");
		req.setAttribute("path", req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+request.getContextPath()+"/");
		request.setAttribute("loginUid", BaseTool.getUidFromCookie(userLogin));
		if (path.indexOf("/user") != -1 || path.indexOf("/usercenter") != -1 || path.indexOf("/safecenter") != -1) {
			//如果不是登陆或注册的首页，但是又包含user的前缀
			if (userLogin == null || token==null||!BaseTool.validCookie(userLogin, token)) {
				response.sendRedirect(contextPath + "/site/login.do");
			} else {
				chain.doFilter(req, res);
			}
		} else {
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
