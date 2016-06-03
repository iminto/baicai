package com.baicai.core.database;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class DynamicDataSourceAspect {

	@Before("execution(* com.baicai.service..*.*(..))")
	public void setDynamicDataSource(JoinPoint jp) throws SecurityException, NoSuchMethodException {
		String methodName = jp.getSignature().getName();
		Method[] ms = jp.getTarget().getClass().getMethods();
		Method method = null;
		for (int i = 0; i < ms.length; i++) {
			if (ms[i].getName().equals(methodName)) {
				method = ms[i];
				break;
			}
		}
		TargetDataSource ds = method.getAnnotation(TargetDataSource.class);
		if (ds != null) {
			if (ds.value() == DynamicDataSourceContextHolder.DS.DATA_SOURCE_1) {
				DynamicDataSourceContextHolder.setDataSourceType(DynamicDataSourceContextHolder.DATA_SOURCE_1);
			} else if (ds.value() == DynamicDataSourceContextHolder.DS.DATA_SOURCE_2) {
				DynamicDataSourceContextHolder.setDataSourceType(DynamicDataSourceContextHolder.DATA_SOURCE_2);
			} else {
				DynamicDataSourceContextHolder.setDataSourceType(DynamicDataSourceContextHolder.DATA_SOURCE_1);
			}

		}
	}
}
