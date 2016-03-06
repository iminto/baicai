package com.baicai.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 通用工具类
 * 
 * @author waitfox@qq.com
 *
 */
public class CommonUtil {
	public static final String VALICODE_SALT = "S5%CVgl;560JL@fd--see";
	public static final String NULLSALT = "";
	public static final String ROOT = CommonUtil.class.getResource("/")
			.getPath();// class文件绝对路径
	public static final String WEBROOT = ROOT.substring(0, ROOT.length() - 16);// 网站根目录

	/**
	 * SHA1散列处理
	 * 
	 * @param srcStr
	 *            原字符串
	 * @param salt
	 *            盐值
	 * @return
	 */
	public static final String encrypt(String srcStr, String salt) {
		try {
			if (!salt.equals("")) {
				srcStr += salt;
			}
			String result = "";
			MessageDigest md = MessageDigest.getInstance("SHA1");
			byte[] bytes = md.digest(srcStr.getBytes("utf-8"));
			for (byte b : bytes) {
				String hex = Integer.toHexString(b & 0xFF).toUpperCase();
				result += ((hex.length() == 1) ? "0" : "") + hex;
			}
			return result;
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 验证码验证
	 * @param md5RandomCode 加密后的字符串
	 * @param inputRandomCode 传入的字符串
	 * @param salt 盐值，需要和加密前保持一致
	 * @return
	 */
	public static boolean validate(String md5RandomCode,
			String inputRandomCode, String salt) {
		if (StringUtil.isBlank(md5RandomCode)
				|| StringUtil.isBlank(inputRandomCode)) {
			return false;
		}
		inputRandomCode = inputRandomCode.toUpperCase();
		inputRandomCode = encrypt(inputRandomCode, salt);
		return inputRandomCode.equals(md5RandomCode);
	}

	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static int getTime() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	public static String time2Date(Integer time, String format) {
		String date = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		date = sdf.format(new Date(time * 1000l));
		return date;

	}
	
	public static int Date2Time(String dateStr,String format){ 
        Date date = new Date();   
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
        try {  
            date = sdf.parse(dateStr);  
           return (int) (date.getTime()/1000);  
        } catch (Exception e) {  
            return 0; 
        }  
	}

	public static String md5(String strs) {
		byte[] source = strs.getBytes();
		char str[] = new char[16 * 2];
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
		} catch (NoSuchAlgorithmException e) {
		}
		return new String(str);

	}

	public static String SHA1(String strs) {
		byte[] _bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(strs.getBytes());
			_bytes = md.digest();
		} catch (NoSuchAlgorithmException ex) {

		}
		return new BigInteger(1, _bytes).toString(16);

	}

	public static String md5file(File file) throws Exception {
		byte[] _bytes = null;
		InputStream is = new FileInputStream(file);
		MessageDigest digest = MessageDigest.getInstance("MD5");
		byte[] buffer = new byte[8192];
		int read = 0;
		while ((read = is.read(buffer)) > 0) {
			digest.update(buffer, 0, read);
		}
		_bytes = digest.digest();
		if (is != null) {
			is.close();
			is = null;
		}
		return new BigInteger(1, _bytes).toString(16);
	}

	public static String getCRC32(String str) {
		java.util.zip.CRC32 crc32 = new java.util.zip.CRC32();
		crc32.update(str.getBytes());
		String retcrc32 = Long.toHexString(crc32.getValue());
		return retcrc32;
	}
	
	/**
	 * 获取最近七天的时间，待优化
	 * 返回格式为<日期:起始时刻毫秒：终点时刻毫秒>，如果为今天，终点时刻为现在时间
	 */
	public static Map<String, Long[]> getLatest7Day(){
		String todayStr="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		todayStr = sdf.format(new Date(System.currentTimeMillis()));
		todayStr=todayStr+" 00:00:00";
		Date today = new Date();   
		DateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
		Map<String, Long[]> data=new TreeMap<>();
		try {
			today=sdf2.parse(todayStr);
			long todaybegin=today.getTime();
			data.put(new SimpleDateFormat("Mdd").format(todaybegin), new Long[]{todaybegin/1000,System.currentTimeMillis()/1000});
			for (int i = 1; i <=6; i++) {
				data.put(new SimpleDateFormat("Mdd").format(new Date(todaybegin-86400000*i)), new Long[]{(todaybegin-86400000*i)/1000,(todaybegin-86400000*i+86539000)/1000});
			}
		} catch (ParseException e) {			
		}
		return data;
	}

}
