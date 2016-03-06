/**
* @Description: 验证助手
* @author 猪肉有毒 waitfox@qq.com  
* @date 2015年6月8日 下午12:52:09 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
*/
package com.baicai.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorHelper {
	public static Boolean filter_var(final String valids,final FILTER type,String... addon){
		boolean retval = false;
		switch(type.name()){
		case "IDCARD":
			retval=FILTER.getIDCARD(valids);
			break;
		case "ISEMPTY":
			retval=FILTER.getISEMPTY(valids);
			break;
		case "PASSWORD":
			retval=FILTER.getIsPassword(valids);
			break;
		default:
			retval = valids.matches(type.getRegexp());
		}		
		return retval;
	}
	
	public static Boolean filter_var_free(final String valids,final String reg){
		boolean retval = false;
		retval = valids.matches(reg);
		return retval;
	}
	public static List<String> preg_match_all(final String source, final String regex) {
		List<String> result = new ArrayList<String>();
		final Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(source);
		while (matcher.find()) {
				result.add(matcher.group());	
		}
		return result;
	}

	public static String preg_match(final String source, final String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(source);
		return matcher.find() ? matcher.group() : "";
	}

	public static String preg_replace(final String source, final String regex, final String replacement) {
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(source);
		return matcher.find() ? source.replace(matcher.group(), replacement) : source;
	}
	
	/**
     * 判断type是否基本类型,如String,Boolean,Number,Date
     * 
     */
	public static Boolean basicType(Class type) {
        if (type.isArray()) {// 如是数组
            return basicType(type.getComponentType());// 判断元素类型
        } else {
            return type.isPrimitive() || type.equals(String.class) || type.equals(Boolean.class) || Number.class.isAssignableFrom(type) || java.util.Date.class.isAssignableFrom(type);
        }
    }


    /**
     * 验证字符串是否符合正则表达式
     */
    public static Boolean regex(String input, String regex) {
        return Pattern.compile(regex).matcher(input).find();
    }
}
