package com.baicai.controller.common;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;
/**
 * 
* @Description: 对jsonp的原生支持,只要请求中带着callback参数，即可自动返回jsonp data
* 如：访问：xxx.do?callback=call_fun 则会接收到返回：call_fun(json data)
* @author 猪肉有毒 waitfox@qq.com  
* @date 2016年4月7日 下午11:45:21 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
@ControllerAdvice
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
	public JsonpAdvice() {        
	    super("callback");    
	  }
	
	@Override
	protected MediaType getContentType( MediaType contentType, ServerHttpRequest request,
			ServerHttpResponse response ) {
		return new MediaType("application", "javascript", contentType.getCharSet());
	}
}
