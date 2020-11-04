package com.zx.crm.settings.service;

import com.zx.crm.exception.LoginException;
import com.zx.crm.settings.domain.User;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;
}
