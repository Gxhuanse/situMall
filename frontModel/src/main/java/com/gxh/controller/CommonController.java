package com.gxh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/Common")
public class CommonController {

    @RequestMapping("/index")
    public String index(){
        return "/index";
    }

}
