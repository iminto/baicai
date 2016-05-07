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
 
public class PropertiesTool {
    public static String get(String configFile, String key) {
    	Properties properties = new Properties();
    		try {
				properties.load(PropertiesTool.class.getClassLoader().getResourceAsStream(configFile+".properties"));
				String value = properties.getProperty(key);
		        return value;			
    		} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
    }
 
    public static void main(String[] args) throws IOException {
        System.out.println(get("system", "tableFix"));
    }
}
