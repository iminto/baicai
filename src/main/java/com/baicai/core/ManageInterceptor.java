package com.baicai.core;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.baicai.util.StringUtil;

/***
 * 后台权限拦截器
 * 
 * @author waitfox@qq.com
 * @date 2015-06-17
 *
 */
@Component
public class ManageInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		String mpath = request.getServletPath();// 形如：/manager/integral/default.do
		// 1、请求到登录页面 放行
		if (request.getServletPath().startsWith("login")) {
			return true;
		}
		Cookie adminLogin = BaseTool.getCookie(request, "adminer");
		if (adminLogin != null) {
			String adminId = BaseTool.decryptCookie(adminLogin.getValue());

			// 在这里判断权限
			mpath = StringUtil.ltrim(mpath, new char[] { '/' });
			mpath = mpath.replace("/", ".").substring(0, mpath.length() - 3);
			if (true) {
				//此处拦截，重定向
			}
		}
		// 如果没有权限重定向到登录页面
		// arg1.sendRedirect(arg0.getContextPath() + "/index.do");
		return true;
	}

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

}
