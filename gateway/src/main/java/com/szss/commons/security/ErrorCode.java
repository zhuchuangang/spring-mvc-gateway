package com.szss.commons.security;

import java.util.HashMap;
import java.util.Map;

/**
 * https://github.com/bolasblack/http-api-guide
 *
 * Error Description(Chinese)	Error Description(English)
 * 0	成功	Success
 * 1	未知错误	Unknown error
 * 2	服务暂不可用	Service temporarily unavailable
 * 3	未知的方法	Unsupported openapi method
 * 4	接口调用次数已达到设定的上限	Open api request limit reached
 * 5	请求来自未经授权的IP地址	Unauthorized client IP address:%s
 * 6	无权限访问该用户数据	No permission to access data
 * 7	来自该refer的请求无访问权限	No permission to access data for this referer
 * 100	请求参数无效	Invalid parameter
 * 101	api key无效	Invalid API key
 * 102	session key无效	Session key invalid or no longer valid
 * 103	call_id参数无效	Invalid/Used call_id parameter
 * 104	无效签名	Incorrect signature
 * 105	请求参数过多	Too many parameters
 * 106	未知的签名方法	Unsupported signature method
 * 107	timestamp参数无效	Invalid/Used timestamp parameter
 * 108	无效的user id	Invalid user id
 * 109	无效的用户资料字段名	Invalid user info field
 * 110	无效的access token	Access token invalid or no longer valid
 * 111	access token过期	Access token expired
 * 112	session key过期	Session key expired
 * 114   无效的ip参数 Invalid Ip
 * 210	用户不可见	User not visible
 * 211	获取未授权的字段	Unsupported permission
 * 212	没有权限获取用户的email	No permission to access user email
 * 800	未知的存储操作错误	Unknown data store API error
 * 801	无效的操作方法	Invalid operation
 * 802	数据存储空间已超过设定的上限	Data store allowable quota was exceeded
 * 803	指定的对象不存在	Specified object cannot be found
 * 804	指定的对象已存在	Specified object already exists
 * 805	数据库操作出错，请重试	A database error occurred. Please try again
 * 900	访问的应用不存在	No such application exists
 * 950	批量操作已开始，请先调用end_batch接口结束前一个批量操作	begin_batch already called, please make sure to call end_batch first
 * 951	结束批量操作的接口调用不应该在start_batch接口之前被调用	end_batch called before start_batch
 * 952	每个批量调用不能包含多于20个接口调用	Each batch API can not contain more than 20 items
 * 953	该接口不适合在批量调用操作中被使用	Method is not allowed in batch mode
 */

/**
 * HTTP状态码	Error_code	Error_msg	备注
 * 500	30600	Internal Server Error	服务器内部错误。
 * 405	30601	Method Not Allowed	不允许的操作（指定了错误的HTTP方法或API）。
 * 400	30602	Request Params Not Valid	请求参数非法。
 * 403	30603	Authentication Failed	权限校验错误 。
 * 402	30604	Quota Use Up Payment Required	无quota。
 * 404	30605	Data Required Not Found	请求数据不存在。
 * 408	30606	Request Time Expires Timeout	请求已超时。
 * 408	30607	Channel Token Timeout	channel_token已经过期。
 * 404	30608	Bind Relation Not Found	绑定关系不存在。
 * 404	30609	Bind Number Too Many	绑定数过多。
 * 409	30610	Duplicate Operation	重复操作。
 * 404	30611	Group Not Found	组不存在。
 */
public enum ErrorCode {
    //======================================================
    //=====================系统级错误========================
    //======================================================
    UNKNOWN_ERROR("0", "未知错误", 500),
    SUCCESS("1", "成功", 200),
    PARAM_VALID_ERROR("-1", "参数验证错误", 500),
    //======================================================
    //=====================业务级错误========================
    //======================================================


    //======================付款订单的取消部分=========
    ORDER_NOT_SHIPMENTS("1", "未发货", 200),
    ORDER_ALREADY_SHIPMENTS("2", "已经发货", 200),
    ORDER_NOT_EXIST("3", "ERP没有此订单", 200),

    //======================售后申请单信息部分=========
    REFUND_INFO_SAVE_SUCCESS("1", "接收成功", 200),
    REFUND_INFO_SAVE_FAIL("2", "接收失败", 200),
    REFUND_INFO_EXIST("3", "ERP存在ERP发起的申请信息", 200),

    //======================售后状态部分=============
    REFUND_NOT_EXIT("1","ERP没有此售后申请信息",200),
    REFUND_SUCCESS("2","售后状态回传成功",200);


    private String code;
    private String desc;
    private int httpStatus;

    private ErrorCode(String code, String desc, int httpStatus) {
        this.code = code;
        this.desc = desc;
        this.httpStatus = httpStatus;
    }

    public Map map() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        result.put("desc", desc);
        return result;
    }

    public Map map(String message) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", code);
        if (message != null) {
            result.put("desc", message + desc);
        }
        return result;
    }


    public String toString() {
        return code + ":" + desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public static void main(String[] args) {
        ErrorCode[] errorCodes = ErrorCode.values();
        for (ErrorCode errorCode : errorCodes) {
            System.out.println(errorCode);
        }
    }
}
