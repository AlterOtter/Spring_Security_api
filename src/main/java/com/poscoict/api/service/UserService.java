package com.poscoict.api.service;


import com.poscoict.api.dto.UserLoginDTO;
import com.poscoict.api.entity.UserEntity;
import com.poscoict.api.repository.UserRepository;
import com.poscoict.api.security.TokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    
    @Autowired
    UserRepository uRepository;

    @Autowired
    TokenProvider token;

    public String Login(UserLoginDTO dto){


        UserEntity entity = dto.getUserEntity();
        System.out.println(entity.toString());
        UserEntity res_entity=uRepository.UserLogin(entity.getMem_id(), entity.getMem_pw());

        token.create(res_entity);

        return token.create(res_entity);
    }


    public UserEntity tokenLogin(UserLoginDTO dto) {
        if(dto==null||dto.getAccess_token()==null)throw new RuntimeException("DTO was NULL Object");

        String user_sn= token.GetTokenBody(dto.getAccess_token());
        UserEntity uentity = uRepository.UserTokenLogin(Integer.parseInt(user_sn));
        uentity.setMem_login(1);
        uRepository.save(uentity);

        return uentity;
    }

    public UserEntity tokenLogout(UserLoginDTO dto) {
        if(dto==null||dto.getAccess_token()==null)throw new RuntimeException("DTO was NULL Object");

        String user_sn= token.GetTokenBody(dto.getAccess_token());
        UserEntity uentity = uRepository.UserTokenLogin(Integer.parseInt(user_sn));
        uentity.setMem_login(0);
        uRepository.save(uentity);

        return uentity;
    }
}
