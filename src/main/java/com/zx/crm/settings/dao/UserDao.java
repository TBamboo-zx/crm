package com.zx.crm.settings.dao;

import com.zx.crm.settings.domain.User;

import java.util.Map;

public interface UserDao {
    User login(Map<String, String> map);
}
