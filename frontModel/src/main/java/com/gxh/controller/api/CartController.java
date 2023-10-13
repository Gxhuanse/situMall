package com.gxh.controller.api;


import com.gxh.common.ResponseBean;
import com.gxh.entity.Cart;
import com.gxh.entity.Category;
import com.gxh.entity.UserBean;
import com.gxh.service.ICartService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 购物车 前端控制器
 * </p>
 *
 * @author gxhuanse
 * @since 2023-10-13
 */
@RestController
@RequestMapping("/api/userCart")
public class CartController {

    @Autowired
    ICartService cartService;

    @PostMapping("/cart")
    public ResponseBean add(Cart cart, HttpSession session){
        ResponseBean responseBean=null;
        try {
            //获取用户信息
            UserBean user= (UserBean) session.getAttribute("user");
            //判断是否登录
            if (user==null){
                responseBean=ResponseBean.failed(2);
            }else {
                cart.setUser_id(user.getId());
                cartService.add(cart);
                responseBean=ResponseBean.ok();
            }
        } catch (Exception e) {
            responseBean=ResponseBean.failed("添加err");
            throw new RuntimeException(e);
        }
        return responseBean;
    }

}

