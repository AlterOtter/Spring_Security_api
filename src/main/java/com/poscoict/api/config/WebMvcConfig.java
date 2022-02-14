package com.poscoict.api.config;

import java.util.List;

import com.poscoict.api.Resolver.UserInfoArguementResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class WebMvcConfig implements WebMvcConfigurer{
    private final long MAX_AGE_SECS= 3600;
    
    @Autowired
    private UserInfoArguementResolver userInfoArguementResolver;

    @Override
    public void addCorsMappings(CorsRegistry registry){

        registry.addMapping("/**")
        .allowedOrigins("http://localhost:3000")
        .allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS")
        .allowedHeaders("*")
        .allowCredentials(true)
        .maxAge(MAX_AGE_SECS);
    }
    
    //#region 스프링 빈 등록
    /*
    // 기능 이용시  사용자 권한 확인
    @Bean 
	public FilterRegistrationBean<?> TokenAuthorizationFilter() {
		FilterRegistrationBean<JwtFilter> filterRegistrationBean = new FilterRegistrationBean<JwtFilter>(jwtfilter);		
        filterRegistrationBean.addUrlPatterns("/user/tokenlogin"); // string 여러개를 가변인자로 받는 메소드
		return filterRegistrationBean;
	}
    //#endregion
    */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:/upload/");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> arguemntResolvers){
        arguemntResolvers.add(userInfoArguementResolver);
    }

}
