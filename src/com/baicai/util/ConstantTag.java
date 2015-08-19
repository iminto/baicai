/**
 * @Description: 常量标签
 * @author 猪肉有毒 waitfox@qq.com  
 * @date 2015年6月4日 下午1:18:09 
 * @version V1.0  
 * 我只为你回眸一笑，即使不够倾国倾城，我只为你付出此生，换来生再次相守
 */
package com.baicai.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import com.alibaba.fastjson.JSONObject;


/**
 * 使用方式：<cos:query item="projectType"/> <c:forEach items="${projectType}"
 * var="entry"> ${entry.key}---${entry.value} </c:forEach>
 * 
 * @author waitfox@qq.com
 *
 */
public class ConstantTag extends SimpleTagSupport {
	private String item;// 标签属性，指定迭代集合元素，为集合元素指定的名称，必须

	@SuppressWarnings("unchecked")
	public void doTag() throws JspException, IOException {
		String content = PropertiesTool.get("system", item);
		if (item.indexOf("_List") != -1) {
			List ret = (List) JSONObject.parseObject(content);
			getJspContext().setAttribute(item, ret);
		} else {
			Map<String, Object> ret = JSONObject.parseObject(content);
			getJspContext().setAttribute(item, ret);
		}

	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

}
