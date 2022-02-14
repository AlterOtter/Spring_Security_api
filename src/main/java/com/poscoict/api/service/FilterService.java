package com.poscoict.api.service;


import com.poscoict.api.entity.UserEntity;
import com.poscoict.api.repository.UserRepository;
import com.poscoict.api.security.PrincipalDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FilterService implements UserDetailsService{

    @Autowired 
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mem_sn) throws UsernameNotFoundException {
        try{
            if(mem_sn != null){

                UserEntity user=userRepository.UserTokenLogin(Integer.parseInt(mem_sn));
                
                PrincipalDetails user_details 
                    =  PrincipalDetails.builder().user(user).build();

                return user_details;
            }
        }catch(RuntimeException e){
            return null;
        }

        return null;
    }
    
}
