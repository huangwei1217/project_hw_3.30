package com.zgd.hw.mvc.interceptor;

import com.zgd.hw.constant.HWCrowdConstant;
import com.zgd.hw.exception.AccessForbiddenException;
import entity.Admin;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author huangwei
 * @description
 * @create 2021-03-31-16:08
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    // 拦截器的preHandle方法，在handler层执行之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 先从请求域中获取session对象
        HttpSession session = request.getSession();

        // 从session对象中获取账户
        Admin admin = (Admin)session.getAttribute(HWCrowdConstant.ATTR_NAME_LOGIN_ADMIN);

        // 判断账户是否为空
        if (admin == null){

            // 为空就抛出异常
            throw new AccessForbiddenException(HWCrowdConstant.MESSAGE_ACCESS_FORBIDEN);
        }

        // 不为空就确认
        return true;
    }
}
