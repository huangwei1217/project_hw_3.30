package com.zgd.hw.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huangwei
 * @description 用来判断是否是Ajax请求
 * @create 2021-03-31-10:59
 */
public class HWCrowdUtil {
    public static boolean judgeRequestType(HttpServletRequest request){
        // 获取消息的请求头信息
        String accept = request.getHeader("Accept");
        String x_requested = request.getHeader("X-Requested-With");

        // 判断
        return (accept != null && accept.contains("application/json"))
                ||
                (x_requested != null && x_requested.contains("XMLHttpRequest"));
    }
}
