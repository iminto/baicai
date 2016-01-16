package com.baicai.util;

import java.io.IOException;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 * 
* @Description: 使用beetl来生成model类，未完成
* @author 猪肉有毒 waitfox@qq.com  
* @date 2016年1月16日 下午11:31:04 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
public class GenModel {
	private static ApplicationContext ctx = new FileSystemXmlApplicationContext(
			"F:/data/eclipse/p2p/target/p2p/WEB-INF/config/applicationContext-core.xml");

	public static void main(String[] args) throws IOException {
		String dir = GenModel.class.getResource("/").toString();
		dir = dir.substring(0, dir.length() - 13) + "p2p/WEB-INF/";// 获取类加载器当前路径
		JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
		FileResourceLoader resourceLo = new FileResourceLoader("F:/data/eclipse/p2p/src/test/resources","utf-8");
		resourceLo.setRoot("F:/data/eclipse/p2p/src/test/resources");
		Configuration config = Configuration.defaultConfiguration();
		config.getResourceMap().put("root",null);//这里需要重置root，否则会去找配置文件
		GroupTemplate gt = new GroupTemplate(resourceLo, config);
		Template t = gt.getTemplate("model.htm");
		
		t.binding("table", "beetl");
		String str = t.render();
		System.out.println(str);

	}

}
