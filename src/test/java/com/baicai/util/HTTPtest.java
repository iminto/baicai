package com.baicai.util;
import com.baicai.util.HTTPClient;
import com.baicai.util.HTTPClient.RequestHeaders;
public class HTTPtest {
	public static void main(String[] args) {
		HTTPClient curl=new HTTPClient("http://sdkhttp.eucp.b2m.cn/sdk/SDKService?wsdl");
		RequestHeaders headers =curl.getHeaders();
		headers.setHost("sdkhttp.eucp.b2m.cn");
		headers.setHeader("Content-Type", "text/xml; charset=UTF-8");
//		Map<String, String> params = new HashMap<String, String>();
//        params.put("sn", "SDK-DLS-010-00309");
//        params.put("pwd", "A49B5D471A014C58C8B119533FAD1C0B");
//        params.put("mobile", "15867253361");
//        params.put("content", "hello");
//        params.put("ext", "");
//        StringBuilder query=new StringBuilder();
//        for (Map.Entry<String, String> entry : params.entrySet()) {
//            query.append("&").append(entry.getKey()).append("=").append(entry.getValue());
//        }
//        System.out.println(query.toString());
//        
//		curl.setQueryString(query.toString());
		String postData="<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\"><SOAP-ENV:Body><ns8957:registEx xmlns:ns8957=\"http://sdkhttp.eucp.b2m.cn/\"><arg0 xsi:type=\"xsd:string\">3SDK-EMY-0130-PBWUP</arg0><arg1 xsi:type=\"xsd:string\">123456</arg1><arg2 xsi:type=\"xsd:string\">009334</arg2></ns8957:registEx></SOAP-ENV:Body></SOAP-ENV:Envelope>";
		curl.setQueryString(postData);
		System.out.println(curl.doPost(true));
		
		StringBuilder sb=new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\">");
		sb.append("<SOAP-ENV:Body><ns4312:sendSMS xmlns:ns4312=\"http://sdkhttp.eucp.b2m.cn/\"><arg0 xsi:type=\"xsd:string\">3SDK-EMY-0130-PBWUP</arg0><arg1 xsi:type=\"xsd:string\">123456</arg1><arg2 xsi:type=\"xsd:string\"></arg2>");
		sb.append("<arg4 xsi:type=\"xsd:string\">");
		sb.append("Java hehe");
		sb.append("</arg4><arg5 xsi:type=\"xsd:string\"></arg5><arg6 xsi:type=\"xsd:string\">UTF-8</arg6><arg7 xsi:type=\"xsd:int\">5</arg7><arg3 xsi:type=\"xsd:string\">");
		sb.append("15867253361");
		sb.append("</arg3></ns4312:sendSMS></SOAP-ENV:Body></SOAP-ENV:Envelope>");
		curl.setQueryString(sb.toString());
		System.out.println(curl.doPost(true));
//		for (int i = 0; i < 200; i++) {
//			curl.doPost(true);
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//			}
//		}
		
	}

}
