package com.example.demo.adapter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 自定义静态资源映射，不影响默认映射
 * @author Administrator
 *
 */

//@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter{
	
	 /**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	//吧范文dsa文件夹里面的地址映射到static文件夹中
        registry.addResourceHandler("/dsa/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
