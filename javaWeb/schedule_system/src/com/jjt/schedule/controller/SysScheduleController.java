package com.jjt.schedule.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * ClassName: SysUserController
 * Package: com.jjt.schedule.controller
 * Description:
 *              需要实现 servlet 的接口
 *              主要处理和sysSchedule实体相关的请求：
 *                  1. 增加日程的请求   /schedule/add
 *                  2. 查询日程的请求   /schedule/find
 *                  3. 修改日程的请求   /schedule/update
 *                  4. 删除日程的请求   /schedule/remove
 *                  ... ...
 * @Author jjt
 * @Create 2023/12/15 11:22
 * @Version 1.0
 */

@WebServlet("/schedule/*")
public class SysScheduleController extends BaseController {

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("add");
    }

    protected void find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("find");
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("update");
    }

    protected void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("remove");
    }
}
