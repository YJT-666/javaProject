package com.jjt.schedule.controller;

import com.jjt.schedule.pojo.SysUser;
import com.jjt.schedule.service.SysUserService;
import com.jjt.schedule.service.impl.SysUserServiceImpl;
import com.jjt.schedule.util.MD5Util;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ClassName: SysUserController
 * Package: com.jjt.schedule.controller
 * Description:
 *              需要实现 servlet 的接口
 * @Author jjt
 * @Create 2023/12/15 11:22
 * @Version 1.0
 */
@WebServlet("/user/*")
public class SysUserController extends BaseController {

    private SysUserService sysUserService = new SysUserServiceImpl();

    /**
     *  该方法用于接受用户注册请求的业务处理方法(接口，业务接口，不是java当中的interface的概念)
     * @param req  the req
     * @param resp the resp
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 接受客户端提交的参数
        String username = req.getParameter("username");
        String userPwd = req.getParameter("userPwd");
        // 2 调用服务层方法，完成注册功能
            // 将参数放入一个 SysUser对象中，再调用regist方法时传入
        SysUser sysUser = new SysUser(null, username, userPwd);
        int rows = sysUserService.regist(sysUser);
        // 3 根据注册的结果（成功  失败）做页面跳转
        if(rows > 0) {
            // 注册成功，响应重定向到注册成功的页面
            resp.sendRedirect("/registSuccess.html");
        } else {
            // 注册失败，响应重定向到注册失败的页面
            resp.sendRedirect("/registFail.html");
        }
    }

    /**
     *  该方法接收登录请求，完成登录的业务接口
     *
     * @param req  the req
     * @param resp the resp
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 接收用户名和密码，封装生产 sysUser 实体类对象
        String username = req.getParameter("username");
        String userPwd = req.getParameter("userPwd");

        // 2. 调用服务层方法，根据用户名查询用户信息
        SysUser loginUser = sysUserService.findUsername(username);

        // 3. 根据查询结果执行相应的页面跳转
        if (loginUser == null) {
            // 响应重定向，跳转到用户名有误提示页面
            resp.sendRedirect("/loginUsernameError.html");
        } else if(!MD5Util.encrypt(userPwd).equals(loginUser.getUserPwd())) {
            // 响应重定向，跳转到密码有误提示页面
            resp.sendRedirect("/loginUserPwdError.html");
        } else {
            // 验证通过，响应重定向，跳转到首页
            resp.sendRedirect("/showSchedule.html");
        }
    }
}
