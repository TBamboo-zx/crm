package com.zx.crm.settings.service.impl;

import com.zx.crm.exception.LoginException;
import com.zx.crm.settings.dao.UserDao;
import com.zx.crm.settings.domain.User;
import com.zx.crm.settings.service.UserService;
import com.zx.crm.utils.DateTimeUtil;
import com.zx.crm.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);


    @Override
    //参数分析，前面两个是为了查询数据库 ， 后面ip时查询玩数据库之后对比ip验证
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {
        Map<String,String> map = new HashMap<String, String>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);

        User user= userDao.login(map);
        if(user==null){
            throw new LoginException("账号密码错误");
        }
        //如果程序运行到这 说明账号密码正确
        //需要继续向下验证其他3项信息

        //验证失效时间
        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();
        if(expireTime.compareTo(currentTime)<0){
            throw new LoginException("已经过了有效时间");
        }
        //验证锁的状态
        String stateLock = user.getLockState();
        if("0".equals(stateLock)){
            throw  new LoginException("账号被锁定");
        }
        //验证ip地址
        String allowIps = user.getAllowIps();
        if(!allowIps.contains(ip)){
            throw  new LoginException("该ip没有访问权限");
        }

        return user;
    }

    @Override
    public List<User> getUserList() {
        System.out.println("执行业务层");
        List<User> uList = userDao.getUserList();
        return  uList;
    }
}
