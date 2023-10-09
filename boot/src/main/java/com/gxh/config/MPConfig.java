package com.gxh.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//MP的配置类
@Configuration
public class MPConfig {

    //拦截器配置类
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        //1.创建MybatisPlusInterceptor拦截器对象
        MybatisPlusInterceptor mpInterceptor=new MybatisPlusInterceptor();
        //2.添加分页拦截器
        //分页拦截器：拦截SQL并进行增强
        mpInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        //乐观锁拦截器
        mpInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        //防全表更新
        mpInterceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return mpInterceptor;
    }

}
