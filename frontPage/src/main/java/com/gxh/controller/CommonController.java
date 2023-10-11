package com.gxh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Common")
public class CommonController {

    @RequestMapping("/index")
    public String index(){
        return "/index";
    }

    @RequestMapping("/special")
    public String special(){
        return "/special";
    }

    @RequestMapping("/mall")
    public String mall(){
        return "/mall";
    }



}
