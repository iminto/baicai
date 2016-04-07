package com.baicai.core;
/**
 * 
* @Description: 自定义的业务异常类
* @author 猪肉有毒 waitfox@qq.com  
* @date 2016年4月7日 下午10:40:44 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public BusinessException() {   
    }  
  
    public BusinessException(String message) {  
        super(message);  
    }  
  
    public BusinessException(Throwable cause) {  
        super(cause);   
    }  
  
    public BusinessException(String message, Throwable cause) {  
        super(message, cause);  
    }  

}
