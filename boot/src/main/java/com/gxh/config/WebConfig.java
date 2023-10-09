package com.gxh.config;

import com.gxh.controller.interceptors.MyFstInterceptor;
import com.gxh.controller.interceptors.MySecInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//@EnableWebMvc//使用此注解会替代掉SpringBoot自动配置的一些组件，导致比如无法访问静态资源的问题
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private MyFstInterceptor myFstInterceptor;

    @Autowired
    private MySecInterceptor mySecInterceptor;

    @Value("${upload.path}")
    private String path;

    /*关于图片上传后需要重启服务器才能刷新图片
    这是一种保护机制，为了防止绝对路径被看出来，目录结构暴露
    解决方法:将虚拟路径/images/
    向绝对路径 (path)
    映射*/
    /**
     * 添加静态资源的映射路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 添加静态资源映射路径
        registry.addResourceHandler("/img/upload/**")
                .addResourceLocations("File:" + path);
//                .addResourceLocations("file:" + System.getProperty("user.dir")+"/src/main/resources/static/img/upload/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myFstInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/Common/login","/Common/logOut","/User/login","/Check/**","/css/**","/js/**","/font/**");
//        registry.addInterceptor(mySecInterceptor).addPathPatterns("/**");
    }

//    @Bean
//    public MultipartResolver multipartResolver(){
//        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
//        resolver.setDefaultEncoding("UTF-8");
//        resolver.setResolveLazily(true);
//        resolver.setMaxInMemorySize(40960);
//        resolver.setMaxUploadSize(50*1024*1024);
//        return resolver;
//    }

}
