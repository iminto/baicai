package com.baicai.controller.common;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.alibaba.fastjson.JSON;
import com.baicai.core.BusinessException; 
/**
 * 全局异常处理
* @Description: TODO
* @author 猪肉有毒 waitfox@qq.com  
* @date 2016年4月7日 下午11:03:18 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
	private Logger log=LoggerFactory.getLogger(ExceptionControllerAdvice.class);
	
	@ExceptionHandler
    public String expAdvice(HttpServletRequest request, HttpServletResponse response, Exception ex){
        request.setAttribute("ex", ex.getMessage());
        log.error(ex.getMessage());
        Map<String, Object> map = new HashMap<String, Object>();
        if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
                .getHeader("X-Requested-With") != null && request.getHeader(
                "X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
        	// 如果不是ajax，返回页面       	
            map.put("success", false);
            if (ex instanceof BusinessException) {
                map.put("errorMsg", ex.getMessage());
            } else {
                map.put("errorMsg", "系统异常！");
          }
        	return "/views/public/500";
        }else{
        	// 如果是ajax请求，JSON格式返回
            try {
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                map.put("success", false);
                // 为安全起见，只有业务异常我们对前端可见，否则统一归为系统异常
                if (ex instanceof BusinessException) {
                    map.put("errorMsg", ex.getMessage());
                } else {
                    map.put("errorMsg", "系统异常！");
                }
                writer.write(JSON.toJSONString(map));
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
