package com.poscoict.api.service;


import javax.transaction.Transactional;

import com.poscoict.api.entity.UserEntity;
import com.poscoict.api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FilterService implements UserDetailsService{

    @Autowired 
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String mem_sn) throws UsernameNotFoundException {
        try{
            if(mem_sn != null){

                UserEntity user=userRepository.UserTokenLogin(Integer.parseInt(mem_sn));
                // log.info(String.valueOf(user.authList.size()));
                return user;
            }
        }catch(RuntimeException e){
            log.info(e.getMessage());
            return null;
        }

        return null;
    }
    
}
