package com.baicai.controller.common;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baicai.util.PropertiesTool;
@Controller
@RequestMapping
public class IndexController {
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		return "/views/index/index";		
	}
	
	@RequestMapping("/manage")
	public String main(HttpServletRequest request,HttpServletResponse response){
		return "/manage/login";		
	}
	
	@RequestMapping("/json")
	@ResponseBody
	public Map<String, Object> json(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<>();  
        map.put("a", "张三");  
        map.put("b", "李四");  
        map.put("d", "D");  
        map.put("c", "张三丰");  
        map.put("e", "E");  
        map.put("f", "F"); 
        //map转为JSON字符串
        System.out.println(JSON.toJSON(map));
        String content = PropertiesTool.get("system", "projectType");
        Map<String, Object> ret = JSONObject.parseObject(content);
        System.out.println(ret+"->"+ret.get("3"));
        String project_status_List=PropertiesTool.get("system", "project_status_List");
        JSONArray jsonArray = JSON.parseArray(project_status_List);
        System.out.println(jsonArray);
        return map;  
	}

}
