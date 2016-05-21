package com.baicai.domain.system;
/**
 * 
* @Description: 封装错误信息
* @author 猪肉有毒 waitfox@qq.com  
* @date 2016年5月21日 下午11:17:01 
* @version V1.0  
* 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
public class ErrorMsg {
	private Integer errCode;//错误代码，非必须
	private String errMsg;//错误信息
	private String url;//跳转网址
	
	public ErrorMsg() {
	}	
	
	public ErrorMsg(String url) {
		super();
		this.url = url;
	}

	public ErrorMsg(String errMsg, String url) {
		super();
		this.errMsg = errMsg;
		this.url = url;
	}
	public Integer getErrCode() {
		return errCode;
	}
	public void setErrCode(Integer errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
