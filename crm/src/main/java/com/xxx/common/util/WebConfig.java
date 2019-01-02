package com.xxx.common.util;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/api/");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //允许全部请求跨域
        registry.addMapping("/**");
    }

    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        InterceptorRegistration ir = registry.addInterceptor(new JwtInterceptor());
        ir.addPathPatterns("/api/**");
        ir.excludePathPatterns("/api/login");
        ir.excludePathPatterns("/api/customer_add");
        ir.excludePathPatterns("/api/product_list");
        ir.excludePathPatterns("/api/users");
        ir.excludePathPatterns("/api/banner");
        ir.excludePathPatterns("/api/invest");
        ir.excludePathPatterns("/api/profit");
        ir.excludePathPatterns("/api/profit_list");
        ir.excludePathPatterns("/api/is_vip");
        ir.excludePathPatterns("/api/signing_details");
    }*/

    /*@Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();//过滤器
        registrationBean.setFilter(filter); return registrationBean;
    }*/


}