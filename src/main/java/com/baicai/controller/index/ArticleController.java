package com.baicai.controller.index;

import com.baicai.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/article")
public class ArticleController  extends BaseController {

    @RequestMapping("/aboutus/{page}")
    public String invest(HttpServletRequest request, HttpServletResponse response){
        return "/views/static/article";
    }

}
