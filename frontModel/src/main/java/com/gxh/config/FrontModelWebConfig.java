package com.gxh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FrontModelWebConfig implements WebMvcConfigurer {
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
}
