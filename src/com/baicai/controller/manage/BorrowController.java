package com.baicai.controller.manage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Description: 后台借款模块
* @author 猪肉有毒 waitfox@qq.com  
* @date 2015年11月13日 下午11:10:08 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
@Controller
@RequestMapping("/manage/borrow")
public class BorrowController {
	
	@RequestMapping("/loan/all")
	public String all(HttpServletRequest request,HttpServletResponse response){
		return "/manage/borrow/loan/borrowAll";		
	}
}
