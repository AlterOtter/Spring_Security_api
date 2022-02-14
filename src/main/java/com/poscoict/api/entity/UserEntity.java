package com.poscoict.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


}
