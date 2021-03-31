package com.zgd.hw.util;

import com.zgd.hw.constant.HWCrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author huangwei
 * @description 用来判断是否是Ajax请求
 * @create 2021-03-31-10:59
 */
public class HWCrowdUtil {

    public static String md5(String source){
        // 1.判断source是否有效
        if(source == null || source.length() == 0) {

            // 2.如果不是有效的字符串抛出异常
            throw new RuntimeException(HWCrowdConstant.MESSAGE_STRING_INVALIDATE);
        }

        try {
            // 3.获取MessageDigest对象
            String algorithm = "md5";

            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            // 4.获取明文字符串对应的字节数组
            byte[] input = source.getBytes();

            // 5.执行加密
            byte[] output = messageDigest.digest(input);

            // 6.创建BigInteger对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);

            // 7.按照16进制将bigInteger的值转换为字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();

            return encoded;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;

    }

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
