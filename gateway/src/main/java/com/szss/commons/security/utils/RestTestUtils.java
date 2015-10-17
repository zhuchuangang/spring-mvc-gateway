package com.szss.commons.security.utils;

/**
 * Created by zcg on 15/4/9.
 */
public class RestTestUtils {
    //ERP 接口测试
    public static final String APIKEY = "appkey";
    public static final String APPSECRET = "123";

    //北京
//    public static final String IP = "http://172.31.3.101:8080";

        public static final String IP = "http://localhost:8080";

    public static String timestamp() {
        return (System.currentTimeMillis() / 1000) + "";
    }
}
