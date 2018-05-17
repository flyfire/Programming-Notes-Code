package com.ztiany.mall.web.servlet;

import com.ztiany.mall.config.AppConfig;
import com.ztiany.mall.domain.User;
import com.ztiany.mall.exception.AppException;
import com.ztiany.mall.exception.DataAccessException;
import com.ztiany.mall.service.UserService;
import com.ztiany.mall.utils.BeanFactory;
import com.ztiany.mall.utils.LogUtils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 18.5.13 22:06
 */

@WebServlet(value = {"/AccountServlet"})
public class AccountServlet extends AbsBaseServlet {


    /**
     * 用户登录：步骤：
     * 1. 获取提交的数据
     * 2. 根据数据查询数据库
     * 3. 查到则把用户保存到Session中，并提示登录成功
     * 4. 没有查找则提示提示登录错误
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DataAccessException {

        LogUtils.debug("account login called");

        //获取参数
        String username = request.getParameter(AppConfig.USERNAME);
        String password = request.getParameter(AppConfig.PASSWORD);

        //调用登录服务
        UserService userService = BeanFactory.getUserService();
        User user = userService.login(username, password);

        //判断用户是否登录成功 user是否是null
        if (user != null) {

            //判断用户是否勾选了自动登录
            String autoLogin = request.getParameter(AppConfig.AUTO_LOGIN);

            if (AppConfig.AUTO_LOGIN.equals(autoLogin)) {
                //要自动登录
                //创建存储用户名的cookie
                Cookie cookieUsername = new Cookie(AppConfig.COOKIE_USERNAME, user.getUsername());
                cookieUsername.setMaxAge(10 * 60);
                //创建存储密码的cookie
                Cookie cookie_password = new Cookie(AppConfig.COOKIE_PASSWORD, user.getPassword());
                cookie_password.setMaxAge(10 * 60);

                response.addCookie(cookieUsername);
                response.addCookie(cookie_password);
            }

            //将user对象存到session中
            HttpSession session = request.getSession();
            session.setAttribute(AppConfig.USER, user);
            response.sendRedirect(request.getContextPath() + "/index.jsp");

        } else {
            request.setAttribute(AppConfig.LOGIN_ERROR, "用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    /**
     * 用户注册，步骤
     * 1.  获取参数
     * 2. 参数 转换为 User对象
     * 3. 检测 参数 是否合法(字段是否合法，用户名是否存在)
     * 4. 合法：保存用户到数据库，跳转到等界面
     * 5. 不合法：提示参数不合法
     */
    public void register(HttpServletRequest request, HttpServletResponse response) {
        throw new AppException(new RuntimeException("你好啊"));
    }

    public void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取参数
        String username = request.getParameter(AppConfig.USERNAME);
        //调用登录服务
        UserService userService = BeanFactory.getUserService();
        boolean exist = false;
        try {
            exist = userService.checkUserExist(username);
        } catch (DataAccessException e) {
            //这个异常可以吞掉，不影响正常流程
            LogUtils.error(this, e);
            throw new AppException(e);
        }
        response.getWriter().write(" {\"isExist\":" + exist + "}");
    }
}
