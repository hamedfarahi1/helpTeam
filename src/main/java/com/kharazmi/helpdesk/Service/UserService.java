package com.kharazmi.helpdesk.Service;

import com.kharazmi.helpdesk.Model.UserModel;

import java.util.Optional;

public interface UserService {

    public UserModel findByEmail(String email) ;
    public Optional<UserModel> findUserbyEmail(String email);
}
