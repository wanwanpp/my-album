package com.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 王萍 on 2017/1/20 0020.
 */
@Controller

public class IndexController {
    @RequestMapping("/index.do")
    public String index(){
        return "index";
    }

    @RequestMapping("/home.do")
    public String home(){
        return "home";
    }
}
