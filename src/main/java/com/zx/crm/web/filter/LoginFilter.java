package com.zx.crm.web.filter;

import com.zx.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request =(HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //判断放行登录页面和登录操作
        String path = request.getServletPath();
        if("/login.jsp".equals(path) || "/settings/user/login.do".equals(path)){
            filterChain.doFilter(request,response);

        } else {
            HttpSession session = request.getSession(false);
            if(session.getAttribute("user")!=null){
                filterChain.doFilter(request,response);

            }else {
            /*
             重定向的路径怎么写？
             应该使用绝对路径，
             转发：
                 使用的是一种特殊的绝对路径的使用方式，这种绝对路径前面不加/项目名，这种路径也成为内部路径
              重定向：
                  使用的是传统绝对路径的写法，前面必须以/项目名开头，后面跟具体的资源路径。


              为什么使用重定向，使用转发不行吗？
                转发之后，路径会停留在老路径上，而不是跳转之后最新资源的路径
                我们应该在为用户跳转登录页的同时，将浏览器的地址栏应该自动设置为当前登录页的路径


                request.getContextPath() 获取项目名 即crm

            */
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            }
        }
    }
}
