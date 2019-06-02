package com.example.demo.Configration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by forget on 2018/12/13.
 */
@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("file:E:/ItemStatic/static/");
        registry.addResourceHandler("/upload/**").addResourceLocations("file:E:/ItemStatic/upload/");

    }


}
