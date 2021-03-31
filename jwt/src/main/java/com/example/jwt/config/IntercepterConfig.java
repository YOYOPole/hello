package com.example.jwt.config;

import com.example.jwt.inteceptor.JWTIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTIntercepter())
                .addPathPatterns("/**")              //其它接口token认证
                .excludePathPatterns("/user/login");//所有用户都放行
    }
}
