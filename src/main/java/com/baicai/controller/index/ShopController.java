package com.baicai.controller.index;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baicai.controller.common.BaseController;
/**
* @Description: 积分商城
* @author 猪肉有毒 waitfox@qq.com  
* @date 2016年6月6日 上午12:54:41 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
@Controller
@RequestMapping("/shop")
public class ShopController extends BaseController{
	
	@RequestMapping("/index")
	public String shop(HttpServletRequest request,HttpServletResponse response){
		return "/views/index/shop";		
	}
}
