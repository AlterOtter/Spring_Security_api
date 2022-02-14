package com.poscoict.api.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.api.repository.UserRepository;
import com.poscoict.api.security.TokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterCeptor implements HandlerInterceptor {
    
    @Autowired
    UserRepository userrRepository;

    @Autowired 
    TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // String mem_id = (String)request.getAttribute("mem_id");
        // String mem_pw = (String)request.getAttribute("mem_pw");

        // UserEntity userEntity=userrRepository.UserLogin(mem_id, mem_pw);

        // tokenProvider.create(userEntity);
        // PrintWriter writer = response.getWriter();

        //권한 부분 spring security

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
