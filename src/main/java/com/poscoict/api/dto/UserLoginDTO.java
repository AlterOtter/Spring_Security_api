package com.poscoict.api.dto;

import com.poscoict.api.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserLoginDTO {
    String mem_id;
    String mem_pw;
    String access_token;
    
    public UserEntity getUserEntity(){
        return UserEntity.builder().mem_id(this.mem_id).mem_pw(this.mem_pw).build();
    }
}
