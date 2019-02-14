package com.baicai.corewith.data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baicai.corewith.annotation.NotNull;
import com.baicai.corewith.annotation.Size;
import com.baicai.corewith.annotation.ValidType;
import com.baicai.corewith.util.ArrayHelper;
import com.baicai.corewith.util.FILTER;
import com.baicai.corewith.util.ValidatorHelper;

/**
 * 基于jdbcTemplate的简单ORM类
 * 注意：由于使用了new，导致模型里面的操作可能不在事务控制下，不要大量使用这里面的方法。
 * 另外，反射的使用也会降低效率
 * @TODO: 可能存在的安全隐患
 * @author waitfox@qq.com
 * @created 2015-05-03 01:12:01
 * @version 0.15 默认表名转为小写
 */
public class Model {

	private static final Logger logger = LoggerFactory.getLogger(Model.class);
	private Map<String, String> errorMap = new HashMap<String, String>();

	public Model() {
	}


	/**
	 * 对Model进行验证，目前支持三种注解，其中validType注解是可扩展的，可以根据需要进行扩展
	 * 用法如下：user.validate(User.regRule);Map<String, String>
	 * errorMap=user.getErrorMap(); 判断errorMap是否非空即可。
	 * 
	 * @param rules
	 *            验证的类型，需要去domain里配置，可针对业务有不同的认证规则
	 * @return
	 */
	public boolean validate(String rules) {
		Field[] at = this.getClass().getDeclaredFields();
		Field rulesf = null;
		String[] rulesArr = {};// 需要验证的字段数组
		try {
			rulesf = this.getClass().getDeclaredField(rules);
			rulesArr = (String[]) rulesf.get(this);
		} catch (NoSuchFieldException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		for (Field field : at) {
			field.setAccessible(true);
			if (!ArrayHelper.inArray(rulesArr, field.getName())) {
				continue;// 不需要验证的字段跳过
			} else {
				try {
					doValidType(field);
					doSize(field);
					doNotNull(field);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("对BEAN做validate时报错，对象的内容为:"+this);
				}
				
			}
		}
		return this.errorMap.isEmpty();//如果这个数组为空，那么就算验证通过

	}

	public Map<String, String> getErrorMap() {
		return this.errorMap;
	}

	/**
	 * 对validType注解进行验证
	 */
	private boolean doValidType(Field field) throws IllegalArgumentException,
			IllegalAccessException {
		boolean passed = true;
		if (field.isAnnotationPresent(ValidType.class) == true) {

			if (field.getAnnotation(ValidType.class).valid().equals("password")) {
				if (ValidatorHelper.filter_var(String.valueOf(field.get(this)),
						FILTER.PASSWORD)) {
				} else {
					passed = false;
					errorMap.put(field.getName(),
							field.getAnnotation(ValidType.class).message());
				}
			}
			if (field.getAnnotation(ValidType.class).valid().equals("email")) {
				if (ValidatorHelper.filter_var(String.valueOf(field.get(this)),
						FILTER.EMAIL)) {
				} else {
					passed = false;
					errorMap.put(field.getName(),
							field.getAnnotation(ValidType.class).message());
				}
			}
			if (field.getAnnotation(ValidType.class).valid().equals("mobile")) {
				if (ValidatorHelper.filter_var(String.valueOf(field.get(this)),
						FILTER.MOBILEPHONE)) {
				} else {
					passed = false;
					errorMap.put(field.getName(),
							field.getAnnotation(ValidType.class).message());
				}
			}

		}
		return passed;
	}

	/**
	 * 对size注解进行验证
	 * 
	 * @param field
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private boolean doSize(Field field) throws IllegalArgumentException,
			IllegalAccessException {
		boolean passed = true;
		if (field.isAnnotationPresent(Size.class) == true) {
			if (field.getAnnotation(Size.class).min() > ((String) field
					.get(this)).length()) {
				passed = false;
				errorMap.put(field.getName(), field.getAnnotation(Size.class)
						.message());
			}
			if (field.getAnnotation(Size.class).max() < ((String) field
					.get(this)).length()) {
				passed = false;
				errorMap.put(field.getName(), field.getAnnotation(Size.class)
						.message());
			}
		}
		return passed;
	}

	/**
	 * 处理notNull注解
	 * 
	 * @param field
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private boolean doNotNull(Field field) throws IllegalArgumentException,
			IllegalAccessException {
		boolean passed = true;
		if (field.isAnnotationPresent(NotNull.class) == true) {
			if (field.getType().getSimpleName().equals("int")
					|| field.getType().getSimpleName().equals("Integer")
					|| field.getType().getSimpleName().equals("long")
					|| field.getType().getSimpleName().equals("Long")) {
				if (field.get(this) == null) {
					errorMap.put(field.getName(),
							field.getAnnotation(NotNull.class).message());
					return false;
				}
			}
			if (field.getType().getSimpleName().equals("String")) {
				if (field.get(this) == null || field.get(this).equals("")) {
					errorMap.put(field.getName(),
							field.getAnnotation(NotNull.class).message());
					return false;
				}
			} else {
				if (field.get(this) == null) {
					errorMap.put(field.getName(),
							field.getAnnotation(NotNull.class).message());
					return false;
				}
			}

		}
		return passed;
	}
	
	/**
	 * 判断对象是否真的为空，不建议大量使用，反射效率不高
	 * 可能有考虑不完善的地方
	 * 因此加上不推荐的标注
	 * @return
	 */
	@Deprecated
	public boolean isNullObject(){
		boolean isNull=true;//默认为空
		Field[] at = this.getClass().getDeclaredFields();
		for (Field field : at) {
			field.setAccessible(true);
			try {
				if (field.getModifiers()!=25 && field.get(this) != null){
					isNull=false;//只要有一个属性非空，那就认为非空了
					break;
				}
			} catch (Exception e) {
				
			} 
		}
		return isNull;
	}

}
