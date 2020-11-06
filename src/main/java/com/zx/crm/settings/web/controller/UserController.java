package com.zx.crm.settings.web.controller;

import com.zx.crm.settings.domain.User;
import com.zx.crm.settings.service.UserService;
import com.zx.crm.settings.service.impl.UserServiceImpl;
import com.zx.crm.utils.MD5Util;
import com.zx.crm.utils.PrintJson;
import com.zx.crm.utils.ServiceFactory;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();
        System.out.println(path);

        if ("/settings/user/login.do".equals(path)) {
            login(request, response);

        } else if ("/setting/uer/xxx.do".equals(path)) {

        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {

        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");
        //将密码的明文装换成MD5的密文形式
        loginPwd = MD5Util.getMD5(loginPwd);
        //接受浏览器端的ip地址
        String ip = request.getRemoteAddr();

        //未来业务层开发，统一使用代理类形态接口对象
        //利用动态代理生成了一个与持久层打交道的服务层对象。
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        /*User user = us.login(loginAct,loginPwd,ip);//传参数到业务层经行验证 需要账号 密码 ip

        //若是验证成功将user写入到Session中。
        request.setAttribute("user",user);
        //考虑到可能出现验证失败异常，，因此将验证操作写入到try..catch中*/
        try{
            User user = us.login(loginAct,loginPwd,ip);

            //允许到这表示没有抛异常  就代表验证成功。
          request.getSession().setAttribute("user",user);
            //需要给前端返回，前面判断的success 和msg。
            PrintJson.printJsonFlag(response,true);


        }catch (Exception e){
            //运行到这表示出现异常，说明业务层为我们验证登录失败，为controller抛出异常
            //表示登录失败,需要传success 和 msg
            e.printStackTrace();
            String msg =e.getMessage();
            /*
                我们现在作为controller，需要ajax请求提供多项信息

                可以有两种手段来处理：
                    （1)将多项信息打包成map，将map解析为json串
                    （2）创建一个Vo
                            private boolean success;
                            private String msa;
                  如果对于展现的信息将来会大量使用，我们创建一个vo类，使用方便
                  如果对于展现的信息只有在这个需求中能够使用，我们使用map就行了
            */
            Map <String ,Object> map = new HashMap<String ,Object>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }

    }
}