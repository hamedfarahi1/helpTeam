package com.kharazmi.helpdesk.Service;

import com.kharazmi.helpdesk.Model.UserModel;
import com.kharazmi.helpdesk.Repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements  UserService {
    @Autowired
    UserModelRepository userModelRepository ;
    @Override
    public UserModel findByEmail(String email){
    return  userModelRepository.findByEmail(email) ;
    }
    @Override
    public Optional<UserModel> findUserbyEmail(String email) {
        return  userModelRepository.findUserByEmail(email);
    }
}
