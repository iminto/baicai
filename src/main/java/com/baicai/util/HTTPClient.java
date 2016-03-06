/**
 * Web页面 访问工具
 * author waitfox@qq.com
 */
package com.baicai.util;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

public class HTTPClient {
    private URL url;
    private RequestHeaders headers = null;
    public static final String METHOD_POST = "POST";
    public static final String METHOD_GET = "GET";
    private String charset = "UTF-8";
    private String queryString = null;
    private int timeout = 30000;//默认超时时间30秒
    
    public HTTPClient(String url) {
        try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
		}
        headers = new RequestHeaders();
        headers.setUserAgent("Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0");
        //headers.setReferer("http://zhurouyoudu.com/");
        //headers.setAccept("image/jpeg, application/x-ms-application, image/gif, application/xaml+xml, image/pjpeg, application/x-ms-xbap, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
       // headers.setAcceptLanguage("zh-CN");
        //headers.setAcceptEncoding("gzip, deflate");
        headers.setHeader("Connection","Keep-Alive");
    }

    private String getContent(String method, String charset,boolean out) throws IOException {
    	HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setReadTimeout(timeout);
            if (headers != null) {
                for (Entry<String, String> entry : headers.getHeaders().entrySet()) {
                    conn.addRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            if (METHOD_POST.equals(method) && queryString != null) {
                conn.setDoOutput(true);
//                conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                conn.getOutputStream().write(queryString.getBytes());
            }           
            conn.connect();            
            InputStream is1 = conn.getInputStream();
            if(METHOD_POST.equals(method) &&out==false) return "SUCCESS";
			InputStream is2 = null;
            String encoding = conn.getContentEncoding();
            // 如果采用了压缩,则需要处理否则都是乱码
			if (encoding != null && encoding.contains("gzip")) {
				is2 = new GZIPInputStream(is1);
			} else if (encoding != null && encoding.contains("deflate")) {
				is2 = new InflaterInputStream(is1);
			} else {
				is2 = is1;
			}
            return read(new InputStreamReader(is2, charset));
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public String doGet() {
        try {
			return getContent(METHOD_GET, charset,true);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
    }

    public String doPost(boolean showReturnData) {
        try {
			return getContent(METHOD_POST, charset,showReturnData);
		} catch (IOException e) {
			e.printStackTrace();
			return "ERROR";		
		}
    }

    /**
     * 获取头信息
     * @return
     */
    public RequestHeaders getHeaders() {
        return headers;
    }

    /**
     * 设置请求头信息
     * @param headers
     */
    public void setHeaders(RequestHeaders headers) {
        this.headers = headers;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
    
    public static String read(Reader reader)
            throws IOException {
        StringBuilder strBuilder = new StringBuilder();
        char[] buffer = new char[1024];
        int read = 0;
        try {
            while ((read = reader.read(buffer)) > 0) {
                strBuilder.append(buffer, 0, read);
            }
        } finally {
            if (reader != null) {
                reader.close();
                reader = null;
            }
        }
        buffer = null;
        return strBuilder.toString();
    }

    /**
     * Headers
     * http://www.ietf.org/rfc/rfc2068.txt
     */
    public static class RequestHeaders {

        private static Map<String, String> headers;
        public RequestHeaders() {
            headers = new HashMap<String, String>();
        }
        /**
         * 设置Header的通用方法
         * @param args
         */
        public  void setHeader(String ... args){
    		if(args.length % 2 != 0)
    			throw new IllegalArgumentException("k,v未配对");
    		for(int i=0;i<args.length-1;i++){
    			headers.put(args[i], args[++i]);
    		}
    		
    	}
        public void setHeader(String key, String value) {
            headers.put(key, value);
        }
        /**
         * 获取所有头信息
         * @return
         */
        public Map<String, String> getHeaders() {
            return headers;
        }

        /**
         * 接收 Accept
         * @param accept
         */
        public void setAccept(String accept) {
            headers.put("Accept", accept);
        }

        public void setCookie(Map<String, String> map){
        	StringBuilder sb = new StringBuilder();
            for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
                String key = it.next();
                sb.append(key).append('=').append(map.get(key));
                if (it.hasNext())
                    sb.append("; ");
            }
           headers.put("Cookie", sb.toString());
        }
        /**
         * 接收字符集 Accept-Charset
         * @param acceptCharset
         */
        public void setAcceptCharset(String acceptCharset) {
            headers.put("Accept-Charset", acceptCharset);
        }
        /**
         * 接收编码 Accept-Encoding
         * @param acceptEncoding
         */
        public void setAcceptEncoding(String acceptEncoding) {
            headers.put("Accept-Encoding", acceptEncoding);
        }

        /**
         * 接收语言 Accept-Language
         * @param acceptLanguage
         */
        public void setAcceptLanguage(String acceptLanguage) {
            headers.put("Accept-Language", acceptLanguage);
        }

        /**
         * 主机 Host
         * @param host
         */
        public void setHost(String host) {
            headers.put("Host", host);
        }
        
        /** 不常用的HTTP头
         * 假如匹配 If-Match  假如修改 If-Modified-Since  认证 Authorization
         * 假如不匹 If-None-Match  假如归类 If-Range
         * 假如不修改 If-Unmodified-Since  最大转发量 Max-Forwards
         * 代理认证 Proxy-Authorization   范围 Range
         */
        
        /**
         * 提交者 Referer
         * @param referer
         */
        public void setReferer(String referer) {
            headers.put("Referer", referer);
        }

        /**
         * 用户代理 User-Agent
         * @param userAgent
         */
        public void setUserAgent(String userAgent) {
            headers.put("User-Agent", userAgent);
        }
    }
}
