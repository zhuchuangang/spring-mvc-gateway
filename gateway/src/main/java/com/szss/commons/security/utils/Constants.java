package com.szss.commons.security.utils;


public class Constants {
    public static final String API_KEY_PARAMETER_NAME = "apikey";
    public static final String REQUEST_TIMESTAMP_PARAMETER_NAME = "timestamp";
    public static final String REQUEST_SALT_PARAMETER_NAME = "salt";
    public static final String SECURE_HASH_PARAMETER_NAME = "signature";
    public static final String REQUEST_HTTP_METHOD_PARAMETER_NAME = "httpMethod";
    public static final String REQUEST_URI_PARAMETER_NAME= "requestURI";
    public static final String REQUEST_METHOD_PARAMETER_NAME= "method";

    /**
     * 有效时间误差，默认为10分钟。
     */
    public static final Integer EFFECTIVE_TIME=10;
}
