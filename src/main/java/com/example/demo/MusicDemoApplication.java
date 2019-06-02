package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan("com.example.demo.Dao")
public class MusicDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicDemoApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        DataSize size = DataSize.ofMegabytes(50);
        factory.setMaxFileSize(size);
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(size);
        return factory.createMultipartConfig();

    }

//	private CorsConfiguration buildConfig(){
//       CorsConfiguration corsconfig=new CorsConfiguration();
//       corsconfig.addAllowedHeader("*");
//       corsconfig.addAllowedMethod("*");
//       corsconfig.addAllowedOrigin("*");
//       corsconfig.setAllowCredentials(true);
//       corsconfig.setMaxAge(172800L);
//       return corsconfig;
//	}
//	@Bean
//	public FilterRegistrationBean  corsFilter(){
//		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**",buildConfig());
//		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//		bean.setOrder(0);
//		return bean;
//	}

}
