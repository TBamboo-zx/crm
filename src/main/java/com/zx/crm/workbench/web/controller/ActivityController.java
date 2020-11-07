package com.zx.crm.workbench.web.controller;

import com.zx.crm.settings.domain.User;
import com.zx.crm.settings.service.UserService;
import com.zx.crm.settings.service.impl.UserServiceImpl;

import com.zx.crm.utils.DateTimeUtil;
import com.zx.crm.utils.PrintJson;
import com.zx.crm.utils.ServiceFactory;
import com.zx.crm.utils.UUIDUtil;
import com.zx.crm.vo.PageList;
import com.zx.crm.workbench.domain.Activity;
import com.zx.crm.workbench.sevice.ActivitySevice;
import com.zx.crm.workbench.sevice.impl.ActivityServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ActivityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();
        System.out.println("进入到市场活动控制器");

        if ("/workbench/activity/getUserList.do".equals(path)) {
            //查询user表里的数据  返回一个UserList
            getUserList(request,response);

        } else if ("/workbench/activity/save.do".equals(path)) {
            save(request,response);
        }else  if("/workbench/activity/pageList.do".equals(path)){
            pageList(request,response);
        }

    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        //第一步获取参事
       String  name=request.getParameter("searchName");
       String  owner=request.getParameter("searchOwner");
       String  startDate = request.getParameter("searchDate");
       String  endDate = request.getParameter("searchEndDate");
       String  pageNoStr= request.getParameter("pageNo");
       String  pageSizeStr = request.getParameter("pageSize");
       int pageNo = Integer.valueOf(pageNoStr);
       int pageSize = Integer.valueOf(pageSizeStr);
       //跳过的数据条数：
       int skipCount = (pageNo-1)*pageSize;
       Map<String ,Object> map = new HashMap<String, Object>();
       map.put("name",name);
       map.put("owner",owner);
       map.put("startDate",startDate);
       map.put("endDate",endDate);
       map.put("pageSize",pageSize);
       map.put("skipCount",skipCount);
       //创建业务层对象 就行业务分析：
        ActivitySevice activitySevice = (ActivitySevice) ServiceFactory.getService(new ActivityServiceImpl());
        //传了map给业务层 查询结果应该返回一个vo类；
        PageList<Activity> activityPageList = activitySevice.getPageList(map);
        PrintJson.printJsonObj(response,activityPageList);

    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        //首先需要接受数据 创建一个vo类用于接受数据
        Activity activity =new Activity();
        String id = UUIDUtil.getUUID();
        
        activity.setId(id);
        activity.setOwner( request.getParameter("actOwner") );
        activity.setName(request.getParameter("actName"));
        activity.setStartDate(request.getParameter("actStateDate"));
        activity.setEndDate(request.getParameter("actEndDate"));
        activity.setCost(request.getParameter("actCost"));
        activity.setDescription(request.getParameter("actDescription"));
        activity.setCreateTime(DateTimeUtil.getSysTime());
        User user = (User)request.getSession().getAttribute("user");
        activity.setCreateBy(user.getName());

        //创建业务层对象用于与dao层交互
        ActivitySevice activitySevice =(ActivitySevice) ServiceFactory.getService(new ActivityServiceImpl());
         int result = activitySevice.save(activity);
         if(result ==1){
             PrintJson.printJsonFlag(response,true);
         }else {
             PrintJson.printJsonFlag(response,false);
         }



    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        //利用动态代理生成动态代理对象与业务层打交道,因为是为了和tbl_user打交道 所以创建User的业务层

        UserService userService = (UserService)ServiceFactory.getService(new UserServiceImpl());
        //调用业务层的查询方法返回一个userList
        List<User> userList = userService.getUserList();
        //将这个list装换成json
        PrintJson.printJsonObj(response,userList);
    }


}