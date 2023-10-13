package com.gxh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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

    @GetMapping("/proDetail")
    public String proDetail(){
        return "/proDetail";
    }

    @GetMapping("/login")
    public String AdmUserLogin(){
        return "/AdmUserLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/index";
    }

    @GetMapping("/cart")
    public String cart(){
        return "/cart";
    }

}
