package com.zgd.hw.mvc.config;

import com.google.gson.Gson;
import com.zgd.hw.constant.HWCrowdConstant;
import com.zgd.hw.exception.LoginFailedException;
import com.zgd.hw.util.HWCrowdUtil;
import com.zgd.hw.util.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author huangwei
 * @description 这是一个基于注解的异常处理类
 * @create 2021-03-31-12:56
 */
@ControllerAdvice
public class HWCrowdExceptionResover {

    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(
            LoginFailedException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        // 出现异常跳转的页面
        String viewName = "admin-login";
//        String viewName = "system-error";

        return commonResolve(viewName,exception,request,response);

    }
    private ModelAndView commonResolve(

            // 异常处理完后要去的页面
            String viewName,

            // 实际捕捉到的异常
            Exception exception,

            // 当前请求对象
            HttpServletRequest request,

            // 当前相应对象
            HttpServletResponse response
    ) throws IOException {
        // 1.首先判断是否为Ajax请求
        boolean judgeRequestType = HWCrowdUtil.judgeRequestType(request);

        // 2.是Ajax请求
        if (judgeRequestType){
            // 3.创建ResultEntity对象和gson对象
            ResultEntity<Object> resultEntity = new ResultEntity<>();
            Gson gson = new Gson();

            // 4.将ResultEntity对象转化成json字符串对象
            String json = gson.toJson(resultEntity);

            // 5.将json对象作为响应体返回给浏览器
            response.getWriter().write(json);

            // 由于上面已经通过原生的response对象返回了响应，所以不提供ModelAndView对象
            return null;
        }

        // 6.不是ajax请求，创建modelandview
        ModelAndView modelAndView = new ModelAndView();

        // 7.将exception对象存入模型
        modelAndView.addObject(HWCrowdConstant.ATTR_NAME_EXCEPTION,exception);

        // 8.设置对应的视图名称
        modelAndView.setViewName(viewName);

        // 9.返回modelandview对象
        return modelAndView;

    }
}
