package com.jjt.schedule.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * ClassName: BaseController
 * Package: com.jjt.schedule.controller
 * Description:
 *                  重写了service方法，该方法可以根据请求路径的最后一个路径名来分发执行相应的方法
 *                      路径名来分发执行相应的方法需要继承该类后进行相应实现，未实现将报错
 *                      分发执行的方法应该与service方法声明相比，除了名字不同，其他的都相同
 * @Author jjt
 * @Create 2023/12/15 12:13
 * @Version 1.0
 */
public class BaseController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 需要判断此次请求时？ 增删改查
        String requestURI = req.getRequestURI();//    /项目上下文路径/servlet路径/具体的资源   一般项目上下文路径设置为/
        /*
         *  提取 /项目上下文路径/servlet路径/具体的资源  当中 servlet路径/ 后面的字符串
         * */
        String[] split = requestURI.split("/");
        String methodName = split[split.length - 1]; // 拿到 / 后的最后一个元素

        /*
         *   通过反射技术，使用methodName来获取下面定义的某个方法，并执行
         * */
        Class aClass = this.getClass();  // 拿到当前类的字节码对象
        try {
            Method declaredMethod = aClass.getDeclaredMethod(methodName,
                    HttpServletRequest.class, HttpServletResponse.class);//获取方法, Declared 可以拿到protected以上修饰符修饰的方法
            // 拿到对应的方法后，执行该方法
            declaredMethod.setAccessible(true); // 暴力破解方法的访问修饰符的限制
            declaredMethod.invoke(this, req, resp);
        } catch (Exception e) {
            // 拿不到对应的方法
            e.printStackTrace();
        }
    }
}
