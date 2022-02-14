package com.poscoict.api.repository;



import com.poscoict.api.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    @Query(value="select * from MEM_TB where mem_id=?1 and mem_pw=?2",nativeQuery = true)
    public UserEntity UserLogin(String mem_id,String mem_pw);

    @Query(value = "select * from MEM_TB where mem_sn=?1",nativeQuery = true)
    public UserEntity UserTokenLogin(Integer mem_sn);
}
