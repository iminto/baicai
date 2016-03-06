/**
* @Description: 配置文件读取工具
* @author 猪肉有毒 waitfox@qq.com  
* @date 2015年5月29日 下午3:06:19 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
*/
package com.baicai.util;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class PropertiesTool {
    private static final Pattern PATTERN = Pattern.compile("\\$\\{([^\\}]+)\\}");//支持简单变量使用
 
    public static String get(String configFile, String key) {
    	Properties properties = new Properties();
        try {
			properties.load(PropertiesTool.class.getClassLoader().getResourceAsStream(configFile+".properties"));
			String value = properties.getProperty(key);
	        Matcher matcher = PATTERN.matcher(value);
	        StringBuffer buffer = new StringBuffer();
	        while (matcher.find()) {
	            String matcherKey = matcher.group(1);
	            String matchervalue = properties.getProperty(matcherKey);
	            if (matchervalue != null) {
	                matcher.appendReplacement(buffer, matchervalue);
	            }
	        }
	        matcher.appendTail(buffer);
	        return buffer.toString();
		} catch (IOException e) {
			return "";
		}
        
    }
 
    public static void main(String[] args) throws IOException {
        System.out.println(get("system", "tableFix"));
    }
}
