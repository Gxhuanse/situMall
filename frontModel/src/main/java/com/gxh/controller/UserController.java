package com.gxh.controller;

import com.gxh.common.ResponseBean;
import com.gxh.entity.UserBean;
import com.gxh.entity.dto.LoginDTO;
import com.gxh.entity.dto.PageDTO;
import com.gxh.entity.dto.user.UpdPassDTO;
import com.gxh.entity.dto.user.UserAddDTO;
import com.gxh.entity.dto.user.UserSeletPageConditionInDTO;
import com.gxh.service.IUserService;
import com.gxh.utils.JwtUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    IUserService service;

    @RequestMapping("/selectByByPageCondition")
    public ResponseBean selectByByPageCondition(UserSeletPageConditionInDTO userDto){
        ResponseBean responseBean;
        PageDTO pageInfo = null;
        try {
            pageInfo = service.selectIserByPageUseCondition(userDto);
            responseBean= ResponseBean.ok(0,pageInfo);
        } catch (Exception e) {
            responseBean=ResponseBean.failed("查询失败");
            throw new RuntimeException(e);
        }
        return responseBean;
    }

    @RequestMapping("/userAdd")
    public ResponseBean userAdd(UserAddDTO dto){
        ResponseBean responseBean;
        if (Objects.equals(dto.getUserNewpass(),dto.getUserRepass())){
            UserBean bean=dto;
            bean.setUserPass(dto.getUserRepass());
            int i = service.UserAdd(bean);
            if (i!=0){
                responseBean=ResponseBean.ok("添加成功");
            }else {
                responseBean=ResponseBean.failed("添加失败");
            }
        }else {
            responseBean=ResponseBean.failed("添加失败、两次密码不一致");
        }
        return responseBean;
    }

    @RequestMapping("/userDelete")
    public ResponseBean userDel(int id){
        ResponseBean responseBean;
        int i = service.userDeleteById(id);
        if (i!=0){
            responseBean=ResponseBean.ok("删除成功");
        }else {
            responseBean=ResponseBean.failed("删除失败");
        }
        return responseBean;
    }

    @RequestMapping("/userUpdata")
    public ResponseBean userUpdata(UserBean bean,HttpServletRequest request){
        ResponseBean responseBean;
        int i = service.userUpdate(bean);
        if (i!=0){
            request.getSession().setAttribute("user",bean);
            responseBean=ResponseBean.ok("修改成功");
        }else {
            responseBean=ResponseBean.failed("修改失败");
        }
        return responseBean;
    }

    @RequestMapping("/resetPass")
    public ResponseBean resetPass(UserBean bean){
        ResponseBean responseBean;
        try {
            int i = service.resetPass(bean.getId(), bean.getUserPass());
            if (i!=0)
                responseBean= ResponseBean.ok();
            else responseBean=ResponseBean.failed("重置失败");
        } catch (Exception e) {
            responseBean=ResponseBean.failed("重置失败err");
            throw new RuntimeException(e);
        }
        return responseBean;
    }

    @RequestMapping("/updatePass")
    public ResponseBean updatePass(UpdPassDTO dto){
        ResponseBean responseBean=null;
        try {
            int i = service.updatePass(dto.getId(), dto.getOldpass(), dto.getNewpass(), dto.getRepass());
            switch (i){
                case -1:responseBean=ResponseBean.failed("修改失败");break;
                case 0:responseBean=ResponseBean.ok();break;
                case 1:responseBean=ResponseBean.failed("新旧密码相同");break;
                case 2:responseBean=ResponseBean.failed("旧密码错误");break;
                case 3:responseBean=ResponseBean.failed("两次新密码不同");break;
                case 4:responseBean=ResponseBean.failed("新密码不规范");break;
            }
        } catch (Exception e) {
            responseBean=ResponseBean.failed("重置失败err");
            throw new RuntimeException(e);
        }
        return responseBean;
    }

    @RequestMapping("/updataStatus")
    public ResponseBean updataStatus(UserBean bean,HttpServletRequest request){
        ResponseBean responseBean;
        try {
            int i = service.updateStatus(bean);
            if (i!=0){
                request.getSession().setAttribute("user",bean);
                responseBean= ResponseBean.ok();
            }
            else responseBean=ResponseBean.failed("重置失败");
        } catch (Exception e) {
            responseBean=ResponseBean.failed("重置失败err");

            throw new RuntimeException(e);
        }
        return responseBean;
    }

    @PostMapping("/login")//用户登录
    public ResponseBean login(LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response){
        String name= loginDTO.getName();
        String pass= loginDTO.getPass();
        if (!Pattern.matches("\\S{3,16}",name)
                ||!Pattern.matches("\\S{3,16}",pass))//校验用户名密码规范性
            return ResponseBean.failed("登录失败,用户名密码不规范");
        Object checkCode = request.getSession().getAttribute("rightCode");//获取session中的验证码
        System.out.println("------------>验证码："+checkCode);
//        if (Objects.equals(loginDTO.getCaptcha(),checkCode))//判断验证码是否正确
        if (true)//跳过验证码校验
        {
            System.out.println("验证码正确");
            UserBean userBean=service.loginToBean(name,pass);
            if ((userBean!=null)//验证用户名密码是否正确
                    &&name.equals(userBean.getUserNickname())
                    &&pass.equals(userBean.getUserPass())) {
                if (userBean.getUserStatus()!=0){//验证用户是否被禁用
                    request.getSession().setAttribute("user",userBean);
                    if (loginDTO.getAuto()!=null){//用户勾选自动登录选项,添加token
                        Map<String,Object> map=new HashMap<>();
                        map.put("userNickName",userBean.getUserNickname());
                        Cookie cookie=new Cookie("jwt", JwtUtils.generateJwt(map));
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                    if (loginDTO.getRemember()!=null){//用户勾选记住密码选项,添加cookie
                        Cookie cookieName=new Cookie("username", name);
                        cookieName.setPath("/");
                        response.addCookie(cookieName);
                        Cookie cookiePass=new Cookie("userpass", pass);
                        cookiePass.setPath("/");
                        response.addCookie(cookiePass);
                    }
                    return ResponseBean.ok("登录成功");
                }else {
                    return ResponseBean.failed("用户被禁用");
                }
            }
            else {//用户名密码错误
                return ResponseBean.failed("用户名或密码错误");
            }
        }else {//验证码错误
            return ResponseBean.failed("验证码错误或未检测到验证码");
        }
    }

}
