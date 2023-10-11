package com.gxh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/Common")
public class CommonController {

    @RequestMapping("/index")
    public String index(){
        return "/index";
    }

    @GetMapping("/proList")
    public String proList(){
        return "/proList";
    }

    @GetMapping("/search")
    public String search(){
        return "/search";
    }

}
