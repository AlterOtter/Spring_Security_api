package com.poscoict.api.controller;


import com.poscoict.api.dto.ResponseDTO;
import com.poscoict.api.dto.UserLoginDTO;
import com.poscoict.api.entity.UserEntity;
import com.poscoict.api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService uservice;

    @PostMapping("/login")
    public ResponseDTO<?> UserLoginController(@RequestBody UserLoginDTO dto){
        System.out.println("ee");
        try {
            String data=uservice.Login(dto);
            return ResponseDTO.<String>builder().data(data).build();
        } catch (Exception e) {
           return ResponseDTO.<String>builder().error(e.getMessage()).build();
        }

    }

    @PostMapping("/tokenlogin")
    public ResponseDTO<?> UserTokenLoginController(@AuthenticationPrincipal UserEntity user,@RequestBody UserLoginDTO dto){
        // try {
        //    System.out.println("TokenLOGIN : "+user.getMem_name());
           
        //     UserEntity result = uservice.tokenLogin(dto);   
        //     return ResponseDTO.<UserEntity>builder().data(result).build();
            
        // } catch (Exception e) {
        //     return ResponseDTO.<String>builder().error(e.getMessage()).build();
        // }
        return ResponseDTO.<UserEntity>builder().data(user).build();
    }

    @PostMapping("/tokenlogout")
    public ResponseDTO<?> UserTokenLogOutController(@RequestBody UserLoginDTO dto){
        try {
            UserEntity result = uservice.tokenLogout(dto);   
            return ResponseDTO.<UserEntity>builder().data(result).build();
            
        } catch (Exception e) {
            return ResponseDTO.<String>builder().error(e.getMessage()).build();
        }

    }



}
