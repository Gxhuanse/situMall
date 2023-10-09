package com.gxh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Common")
public class CommonController {

    @RequestMapping("/login")
    public String userlogin(){
        return "/AdmUserLogin";
    }

    @RequestMapping("/logOut")
    public String logOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/Common/login";
    }

    @RequestMapping("/AdmNavHome")
    public String navHome(){
        return "home/AdmNavHome";
    }

    @RequestMapping("/NavIndex")
    public String NavIndex(){
        return "home/NavIndex";
    }
    @RequestMapping("/About")
    public String About(){
        return "home/About";
    }

    @RequestMapping("/userList")
    public String userList(){
        return "user/userList";
    }

    @RequestMapping("/userPass")
    public String userPass(){
        return "user/userPass";
    }

    @RequestMapping("/userAdd")
    public String userAdd(){
        return "user/userAdd";
    }

    @RequestMapping("/userinfo")
    public String userinfo(){
        return "user/userinfo";
    }

    @RequestMapping("/userUpdata")
    public String userUpdata(){
        return "user/userUpdata";
    }

    @RequestMapping("/categoryList")
    public String categoryList(){
        return "category/categoryList";
    }

    @RequestMapping("/categoryAdd")
    public String categoryAdd(){
        return "category/categoryAdd";
    }

    @RequestMapping("/categoryUpdata")
    public String categoryUpdata(){
        return "category/categoryUpdata";
    }

    @RequestMapping("/productList")
    public String productList(){
        return "product/productList";
    }

    @RequestMapping("/productAdd")
    public String productAdd(){
        return "product/productAdd";
    }

    @RequestMapping("/productUpdata")
    public String productUpdata(){
        return "product/productUpdata";
    }

    @RequestMapping("/orderList")
    public String orderList(){
        return "order/orderList";
    }

    @RequestMapping("/productCount")
    public String productCount(){
        return "count/productCount";
    }

}
