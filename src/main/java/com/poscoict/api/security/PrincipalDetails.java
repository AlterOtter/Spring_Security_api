package com.poscoict.api.security;

import java.util.ArrayList;
import java.util.Collection;

import com.poscoict.api.entity.UserEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrincipalDetails implements UserDetails{

    private UserEntity user;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority("ROLE_"+user.getMem_role()));

        return auth;
    }

    @Override
    public String getPassword() {
        return user.getMem_pw();
    }

    @Override
    public String getUsername() {
        return user.getMem_name();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getSERIALNO() {
        return user.getMem_sn();
    }

    public String getID() {
        return user.getMem_id();
    }
    
}
