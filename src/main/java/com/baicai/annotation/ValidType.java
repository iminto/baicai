/**
* @Description: TODO
* @author 猪肉有毒 waitfox@qq.com  
* @date 2015年6月8日 下午1:32:06 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
*/
package com.baicai.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface ValidType {
	String valid();//验证类型
	String message() default "验证不通过";//验证信息
}
