/**
 * @Description: 系统基本工具类，这个类里方的东西和web有耦合性，因此不放到CommonUtil类里面
 * @author 猪肉有毒 waitfox@qq.com  
 * @date 2015年6月2日 下午2:28:45 
 * @version V1.1  
 * 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
package com.baicai.core;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;
import com.baicai.util.CommonUtil;
import com.baicai.util.PropertiesTool;
import com.baicai.util.XorEncrypt;

public class BaseTool {
	// 默认登陆有效期
	public static final int LOGINVALIDTIME = Integer.valueOf(PropertiesTool
			.get("system", "LOGINVALID"));
	public static final int MONTH=0;//日期类型
	public static final int DAY=1;

	/**
	 * 判断是否为图片，通过取图片宽高来判断，如果有异常，就证明不是图片
	 * 
	 * @param imageFile
	 * @return
	 */
	public static boolean isImage(MultipartFile imageFile) {
		String fileName = imageFile.getOriginalFilename();
		// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length());
		ext = ext.toLowerCase();
		String[] fileType = { "bmp", "gif", "jpeg", "jpg", "png" };// 这里必须有序，否则结果一定会出错
		if (Arrays.binarySearch(fileType, ext) < 0) {
			return false;
		}
		Image img = null;
		try {
			img = ImageIO.read(imageFile.getInputStream());
			if (img == null || img.getWidth(null) <= 0
					|| img.getHeight(null) <= 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			img = null;
		}
	}

	/**
	 * 
	 * @param file
	 * @param dir
	 *            保存目录，针对根目录来说，前后都不需要带/
	 * @param keepname
	 *            是否保存原有文件名
	 * @return
	 */
	public static String upload(MultipartFile file, String dir, boolean keepname) {
		// 加上 5位随机数
		String uustr = System.currentTimeMillis() / 1000l
				+ UUID.randomUUID().toString().replace("-", "");
		String saveName = dir + File.separator;
		mkdir(saveName);
		if (keepname) {
			saveName += file.getOriginalFilename();
		} else {
			String ext = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf(".") + 1,
					file.getOriginalFilename().length());
			ext = ext.toLowerCase();
			saveName += uustr + "." + ext;
		}
		File source = new File(CommonUtil.WEBROOT + saveName);
		try {
			file.transferTo(source);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		return saveName;
	}

	public static void mkdir(String path) {
		File fd = null;
		try {
			fd = new File(path);
			if (!fd.exists()) {
				fd.mkdirs();
			}
		} catch (Exception e) {
		} finally {
			fd = null;
		}
	}

	public static Cookie getCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				String name = cookies[i].getName();
				if (name.equals(key)) {
					return cookies[i];
				}
			}
		}
		return null;
	}

	/**
	 * Cookie内容加密，防止篡改
	 * 
	 * @param data
	 * @return
	 */
	public static String encryptCookieValue(String data) {
		String key = PropertiesTool.get("system", "USER_SALT");
		XorEncrypt xor = new XorEncrypt(key);
		String encode = xor.encrypt(data);
		return encode;
	}

	/**
	 * 生成token，用处如下，使用过的地方加在下面 1.防止cookie被篡改
	 * 
	 * @param str
	 * @return
	 */
	public static String generatorToken(String str) {
		return CommonUtil.md5(PropertiesTool.get("system", "USER_SALT") + str
				+ PropertiesTool.get("system", "USER_SALT"));

	}

	/**
	 * 验证cookie，下同
	 * 
	 * @param userCookie
	 * @param tokenCookie
	 * @return
	 */
	public static boolean validToken(String userCookie, String tokenCookie) {
		return CommonUtil.md5(
				PropertiesTool.get("system", "USER_SALT") + userCookie
						+ PropertiesTool.get("system", "USER_SALT")).equals(
				tokenCookie);
	}

	public static boolean validCookie(Cookie userCookie, Cookie tokenCookie) {
		return CommonUtil.md5(
				PropertiesTool.get("system", "USER_SALT")
						+ userCookie.getValue()
						+ PropertiesTool.get("system", "USER_SALT")).equals(
				tokenCookie.getValue());
	}

	/**
	 * Cookies内容解密，需要考虑篡改后的数据是无法解密的，会报出异常，这样就判断是非法数据
	 * 但是存在被篡改后解密出一个新的结果，这就还需要一个token来验证
	 */
	public static String decryptCookie(String encStr) {
		try {
			String key = PropertiesTool.get("system", "USER_SALT");
			XorEncrypt xor = new XorEncrypt(key);
			return xor.decrypt(encStr);
		} catch (Exception e) {
			return "";
		}

	}


	/**
	 * 生成处理后的密码
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static String get_pass(Long userId, String password) {
		return CommonUtil.md5(CommonUtil.SHA1(CommonUtil.md5(password)
				.substring(7, 31) + userId));
	}


	/**
	 * 把页面参数Map转为字符串，用于分页等
	 * 
	 * @param map
	 * @return
	 */
	public static String pageParam(Map map) {
		StringBuilder sb = new StringBuilder(20);
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			sb.append(key).append("=").append(entry.getValue()).append("&");
		}
		return sb.toString();
	}


	/**
	 * 计算加N个月后的日期，确保1-31号加一天后啊2-28号，而不是2月31日或3月3日
	 * 
	 * @param time
	 *            传入时间戳
	 * @param add
	 *            传入需要增加的月份/天
	 * @param type
	 *            0 月标，1 天标
	 * @return
	 */
	public static int getTime(int time, int add, int type) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(time * 1000l));
		if (type == DAY) {
			calendar.add(Calendar.DAY_OF_MONTH, add);
		} else {
			calendar.add(Calendar.MONTH, add);
		}
		Date newd = calendar.getTime();
		return (int) (newd.getTime() / 1000l);
	}

	public static String getCode() {
		String code_str = "";
		for (int i = 0; i <= 5; i++) {
			code_str += new Random().nextInt(9);
		}
		return code_str;
	}

	/**
	 * 调用SpringMVC自带的过滤函数
	 * 
	 * @param input
	 * @return
	 */
	public static String htmlEscape(String input) {
		return HtmlUtils.htmlEscape(input);
	}
	
	/**
	 * 在项目启动时就获取服务器域名和端口
	 * 本来可以通过request对象获取，但是项目刚启动时是没有request对象的
	 * @return
	 */
	public static String getServer() {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ArrayList<String> endPoints = new ArrayList<String>();
		try {
			Set<ObjectName> objs = mbs.queryNames(new ObjectName(
					"*:type=Connector,*"), Query.match(Query.attr("protocol"),
					Query.value("HTTP/1.1")));
			String hostname = InetAddress.getLocalHost().getHostName();
			InetAddress[] addresses = InetAddress.getAllByName(hostname);
			for (Iterator<ObjectName> i = objs.iterator(); i.hasNext();) {
				ObjectName obj = i.next();
				String scheme = mbs.getAttribute(obj, "scheme").toString();
				String port = obj.getKeyProperty("port");
				for (InetAddress addr : addresses) {
					String host = addr.getHostAddress();
					String ep = scheme + "://" + host + ":" + port;
					endPoints.add(ep);
				}
			}
		} catch (Exception e) {
			return "";
		}
		if (endPoints.size() > 0) {
			return endPoints.get(0);
		} else {
			return "";
		}
	}
}
