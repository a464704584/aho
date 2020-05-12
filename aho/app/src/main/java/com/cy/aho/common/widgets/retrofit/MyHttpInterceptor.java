package com.cy.aho.common.widgets.retrofit;


import com.cy.aho.MyApplication;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @创建者 CY
 * @创建时间 2019/8/21 20:00
 * @描述
 */
public class MyHttpInterceptor implements Interceptor {
    //最大重试次数
    private int maxTry=3;

    private static Set<String> requestKeySet = new HashSet<>();



    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response;
        request = addCommonParams(request);
        String requestKey = md5(request.toString());
        //如果存在该请求
        if (requestKeySet.contains(requestKey)) {
            response = new Response.Builder()
                    .code(499)
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .message("重复请求")
                    .build();
            return response;
        }

        requestKeySet.add(requestKey);
        try {
            //请求
            response = chain.proceed(request);
        }catch (IOException e){
            throw e;
        }finally {
            //移除重复请求,防止异常抛出 导致之后的请求 进不来
            requestKeySet.remove(requestKey);
        }
        //请求结束移除请求key
        return response;
    }

    /**
     * 添加公共参数
     * @param request
     * @return
     */
    private Request addCommonParams(Request request) {
        Request.Builder builder = request.newBuilder();
        builder.addHeader("token", ""+MyApplication.getToken());
        return builder.build();
    }

    private String md5(String plainText) {
        //定义一个字节数组
        byte[] secretBytes = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(plainText.getBytes());
            //获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        //将加密后的数据转换为16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
