package com.baicai.controller.common;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baicai.util.CaptchaRender;
import com.baicai.util.CommonUtil;
/**
 * 通用控制器，一般是一些不需要身份验证就能看到的页面
 * @author waitfox@qq.com
 *
 */

@Controller
@RequestMapping("/common")
public class Common {
	
	@RequestMapping("/valicode")
	public void valicode(HttpServletResponse resp){
		 BufferedImage image = new BufferedImage(60, 20, BufferedImage.TYPE_INT_RGB);
		 String valicode=CaptchaRender.drawGraphic(image);
		 String encryptString=CommonUtil.encrypt(valicode.toUpperCase(), CommonUtil.VALICODE_SALT);
		 Cookie cookie=new Cookie("Vali", encryptString);
		 cookie.setHttpOnly(true);
		 cookie.setMaxAge(600);//验证码有效期为10分钟
		 cookie.setPath("/");
		 resp.setHeader("Pragma","no-cache");
		 resp.setHeader("Cache-Control","no-cache");
		 resp.setDateHeader("Expires", 0);
		 resp.setContentType("image/jpeg");
		 resp.addCookie(cookie);
		 ServletOutputStream sos = null;
		 try {
			 sos = resp.getOutputStream();
			 ImageIO.write(image, "jpeg",sos);
			 sos.flush();
			 sos.close();
		} catch (IOException e) {
			
		}
	}
	
	@RequestMapping("/html/{page}")
	public String html(HttpServletRequest request, @PathVariable String page){
		return "/html/"+page;
	}
	
	@RequestMapping("/404")
	public String Error404(HttpServletRequest request,HttpServletResponse resp){
		return "/views/public/404";
	}

}
