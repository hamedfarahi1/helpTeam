package com.kharazmi.helpdesk.Repository;

import com.kharazmi.helpdesk.Model.EmailVerfyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Generated by Spring Data Generator on 17/04/2019
 */
@Repository
public interface EmailVerfyModelRepository extends JpaRepository<EmailVerfyModel, Integer>, JpaSpecificationExecutor<EmailVerfyModel>
{
    EmailVerfyModel findByToken(String token);
}
