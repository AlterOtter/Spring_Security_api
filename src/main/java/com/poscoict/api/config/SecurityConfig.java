package com.poscoict.api.config;

import com.poscoict.api.security.JwtFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

@SpringBootConfiguration
@EnableWebSecurity // @EnableWebSecurity이 바로 @AuthenticationPrincipal 어노테이션을 통해 Authentication 객체 속에 들어있는 principal 필드를 가져올 수 있게 합니다.
@EnableGlobalMethodSecurity(securedEnabled = true) // Controller 메소드에 직접적으로 Role을 부여할 수 있습니다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and() //cors 옵션 체크  //Cross-Origin Resource Sharing
                .csrf().disable() //새션 비사용 //Cross-Site Request Forgery
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() //시큐리티에서 세션 관리 금지
                .httpBasic().disable()
                .formLogin().disable()
                .authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
                	.antMatchers("/user/login").permitAll() // 누구나 로그인 가능
                .anyRequest().authenticated();
         


        http.addFilterBefore(jwtFilter, CorsFilter.class);
    }

    
}