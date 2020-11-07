package com.zx.crm.settings.service;

import com.zx.crm.exception.LoginException;
import com.zx.crm.settings.domain.User;

import java.util.List;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;


    List<User> getUserList();
}
