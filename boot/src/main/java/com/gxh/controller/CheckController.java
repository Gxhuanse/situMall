package com.gxh.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.gxh.entity.UserBean;
import com.gxh.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/Check")
public class CheckController {

    //生成验证码
    @RequestMapping("/CaptchaImg/{time}")
    public void getcheckCode(HttpServletRequest request, HttpServletResponse response, @PathVariable("time") String time){
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(110, 40, 4, 0);

        //生成验证码秘钥
        String uuid = "uuidcontent";
        Cookie cookie = new Cookie("uuid",uuid);
        response.addCookie(cookie);
//        response.setHeader("Set-Cookie",WebConfig.RIGHT_CODE_KEY+"="+uuid);
        //图形验证码写出，可以写出到文件，也可以写出到流
        request.getSession().setAttribute("rightCode",captcha.getCode());
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        try (ServletOutputStream responseOutputStream= response.getOutputStream();){
            captcha.write(responseOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/LoginJwtCheck")
    public String checklog(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        Cookie cookie_jwt = JwtUtils.findCookie(cookies, "jwt");

        if (cookie_jwt==null){
            //首次登录
            System.out.println("首次登录");
            return "redirect:/Common/login";
        }else {
            //再次登录
            System.out.println("再次登录");
            String jwt = cookie_jwt.getValue();
            Claims claims = JwtUtils.parseJWT(jwt);
            if (claims!=null){//jwt合法
                System.out.println("jwt合法");
                String u = (String) claims.get("userNickName");
                UserBean userBean = new UserBean();
                userBean.setUserNickname(u);
                request.getSession().setAttribute("user",userBean);
                return "forward:/Common/AdmNavHome";
            }else {//jwt非法
                System.out.println("jwt非法");
                return "redirect:/Common/login";
            }
        }
    }

}
