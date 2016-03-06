/**
* @Description: 非空注解
* @author 猪肉有毒 waitfox@qq.com  
* @date 2015年6月8日 下午4:21:35 
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
public @interface NotNull {
	boolean notNull() default true;
	String message() default "该字段不能为空";

}
