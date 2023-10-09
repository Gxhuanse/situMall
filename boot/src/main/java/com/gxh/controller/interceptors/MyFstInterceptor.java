package com.gxh.controller.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyFstInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyFstInterceptor----01----preHandle执行---:"+request.getRequestURI());
        Object attribute = request.getSession().getAttribute("user");
        if (attribute!=null){
            return true;
        }else {
            response.sendRedirect("/BootStart/Common/login");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyFstInterceptor----01----postHandle---:"+request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyFstInterceptor----01----afterCompletion---:"+request.getRequestURI());
        System.out.println();
    }
}
