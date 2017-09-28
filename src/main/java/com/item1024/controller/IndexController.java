package com.item1024.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 阳十三
 * @email wdful165177@gmail.com
 * @blog http://www.item1024.com
 * 静态路由控制器
 */
@RestController
public class IndexController {
    private static final Logger logger = Logger.getRootLogger();
    //首页
    @RequestMapping("/index.py")
    public String index() {
        return "home";
    }

}
