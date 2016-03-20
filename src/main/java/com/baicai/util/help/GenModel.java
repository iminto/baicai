package com.baicai.util.help;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

/**
 * 
 * @Description: 使用beetl来生成model类，未完成
 * @author 猪肉有毒 waitfox@qq.com
 * @date 2016年1月16日 下午11:31:04
 * @version V1.0 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
public class GenModel {
	public static final String RESOURCEPATH = "F:/data/eclipse/p2p/src/test/resources";

	public static void generetor(String tableName) throws IOException {
		FileResourceLoader resourceLo = new FileResourceLoader(RESOURCEPATH, "utf-8");
		resourceLo.setRoot(RESOURCEPATH);
		Configuration config = Configuration.defaultConfiguration();
		config.getResourceMap().put("root", null);// 这里需要重置root，否则会去找配置文件
		GroupTemplate gt = new GroupTemplate(resourceLo, config);
		Template t = gt.getTemplate("model.htm");
		TableBean tb = Generator.getTable(tableName);
		String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
		t.binding("table", tb);
		t.binding("package", Generator.PACKAGE_BASE);
		t.binding("createDate", date);
		String str = t.render();
		System.out.println(str);
		File f = new File(Generator.OUTPUT_PATH+tb.getTableNameCapitalized()+".java");// 新建一个文件对象
		FileWriter fw;
		try {
			fw = new FileWriter(f);// 新建一个FileWriter
			fw.write(str);// 将字符串写入到指定的路径下的文件中
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		generetor("p2p_project");
	}

}
