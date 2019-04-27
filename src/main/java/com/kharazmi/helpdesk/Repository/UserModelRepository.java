package com.kharazmi.helpdesk.Repository;

import com.kharazmi.helpdesk.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
* Generated by Spring Data Generator on 13/04/2019
*/
@Repository
public interface UserModelRepository extends JpaRepository<UserModel, Integer>, JpaSpecificationExecutor<UserModel> {


    @Query(value = "SELECT * FROM user as r where r.email = :email",nativeQuery = true)
    UserModel findByEmail(@Param("email") String email);
    @Query(value = "SELECT * FROM user  where email = :email",nativeQuery = true)
    Optional<UserModel> findUserByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM user as r where r.id = :id",nativeQuery = true)
    UserModel findID(@Param("id") int id);
// farmoon started
    @Query(value = "SELECT * FROM user as r where r.UserName = :UserName",nativeQuery = true)
    UserModel findByUserName(@Param("UserName") String UserName);
    @Query(value = "SELECT * FROM user as r where r.PhoneNumber = :PhoneNumber",nativeQuery = true)
    UserModel findByPhoneNumber(@Param("PhoneNumber") String PhoneNumber);
// farmoon ended

}
