package com.poscoict.api.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Table(name="MEM_TB")
public class UserEntity implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MEM_SN")
    private Integer mem_sn;

    @Column(name ="MEM_ID")
    private String mem_id;

    @Column(name ="MEM_PW")
    private String mem_pw;

    @Column(name = "MEM_NAME")
    private String mem_name;

    @Column(name = "MEM_CONTENT")
    private String mem_content;

    @Column(name = "MEM_LOGIN")
    private Integer mem_login;

    @Column(name = "MEM_ROLE")
    private String mem_role;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="MEM_TB_SN")
    public List<AuthorityEntity> authList=new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        for (AuthorityEntity authorityEntity : this.authList) {    
            auth.add(new SimpleGrantedAuthority("ROLE_"+authorityEntity.getAuthority()));
        }
        // auth.add(new SimpleGrantedAuthority("ROLE_"+this.mem_role));
        return auth;
    }

    @Override
    public String getPassword() {
        return this.mem_pw;
    }

    @Override
    public String getUsername() {
        return this.mem_name;
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

}
