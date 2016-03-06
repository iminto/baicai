/**
 * @Description: 常用正则枚举 Unicode里中文、日文与韩文的汉字是放在同一个（一些）区块里的，主要是在“CJK Unified Ideographs”当中，
 * 还有一些在“CJK Unified Ideographs Extension A”与“CJK Unified Ideographs Extension B”当中
 * @author 猪肉有毒 waitfox@qq.com  
 * @date 2015年6月8日 下午12:44:41 
 * @version V1.0  
 * 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
package com.baicai.util;

public enum FILTER {
			EMAIL("(?i)^[_a-z0-9\\-]+([._a-z0-9\\-]+)*@[a-z0-9\\-]+([.a-z0-9\\-]+)*(\\.[a-z]{2,4})$"), 
			NUMBER("[+-]?\\d+[.0-9]*"),
			MOBILEPHONE("^(13|15|18|17)\\d{9}$"), 
			PHONES("^0(10|2[0-9]|\\d{3})\\d{7,8}$"), 
			URL("(?i)^(http|https|www|ftp|)?(://)?([_a-z0-9\\-].)+[_a-z0-9\\-]+(\\/[_a-z0-9\\-])*(\\/)*([_a-z0-9\\-].)*([_a-z0-9\\-&#?=])*$"), 
			CASE("^[a-zA-Z]+$"), 
			IPV4("(?<=(\\b|\\D))(((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))"), 
			IPV6(".*/([0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+:[0-9a-f]+(%[a-zA-Z0-9]+)?):([0-9]+)"), 
			CHINESE("[\\u4e00-\\u9fa5]*"), 
			TWOBYTES("[^\\x00-\\xff]"), IDCARD("IDCARD"), 
			CJK("\\p{InCJK Unified Ideographs}"), 
			ISBN10("^(?:(\\d{9}[0-9X])|(?:(\\d{1,5})(?:\\-|\\s)(\\d{1,7})(?:\\-|\\s)(\\d{1,6})(?:\\-|\\s)([0-9X])))$"), 
			ISBN13("^(978|979)(?:(\\d{10})|(?:(?:\\-|\\s)(\\d{1,5})(?:\\-|\\s)(\\d{1,7})(?:\\-|\\s)(\\d{1,6})(?:\\-|\\s)([0-9])))$"), 
			ISEMPTY("ISEMPTY"),
			PASSWORD("PASSWORD");
	private String regexp;

	private FILTER(String regexp) {
		this.regexp = regexp;
	}

	public String getRegexp() {
		return regexp;
	}

	public static boolean getIDCARD(String idcard) {
		return IdcardValidator.isValidatedAllIdcard(idcard);
	}

	public static boolean getISEMPTY(String str) {
		return (str == null) || (str.trim().length() < 1);
	}
	
	/**
	 * 判断是否是密码，长度>=6并且包含字母
	 * @param password
	 * @return
	 */
	public static boolean getIsPassword(String password){
		return password.length()>=6 && StringUtil.containLetter(password);
	}
}
