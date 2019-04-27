package com.kharazmi.helpdesk.Repository;

import com.kharazmi.helpdesk.Model.TicketModel;
import com.kharazmi.helpdesk.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketModelRepository  extends JpaRepository<TicketModel, Integer>, JpaSpecificationExecutor<TicketModel> {

}
