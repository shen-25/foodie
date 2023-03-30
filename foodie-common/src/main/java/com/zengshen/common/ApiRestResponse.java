package com.zengshen.common;

import com.zengshen.common.enums.BusinessExceptionEnum;
import com.zengshen.common.utils.JsonUtil;
import lombok.Data;

import java.util.Map;

/**
 * @author word
 */
@Data
public class ApiRestResponse {

    // 响应状态码
    private int status;

    // 响应中的信息
    private String msg;

    // 响应的内容
    private Object data;

    private ApiRestResponse(Integer code, String msg, Object data) {
        this.status = code;
        this.msg = msg;
        this.data = data;
    }
    private ApiRestResponse(Integer code, String msg) {
        this.status = code;
        this.msg = msg;
    }

    private ApiRestResponse() {
        this(BusinessExceptionEnum.SUCCESS.getCode(),BusinessExceptionEnum.SUCCESS.getMsg());
    }

    private ApiRestResponse(Object data) {
        this(BusinessExceptionEnum.SUCCESS.getCode(),BusinessExceptionEnum.SUCCESS.getMsg(), data);
    }

    private ApiRestResponse(BusinessExceptionEnum businessExceptionEnum) {
        this.status = businessExceptionEnum.getCode();
        this.msg = businessExceptionEnum.getMsg();
    }


    private ApiRestResponse(BusinessExceptionEnum businessExceptionEnum, String msg) {
        this.status = businessExceptionEnum.getCode();
        this.msg = msg;
    }
    private ApiRestResponse(BusinessExceptionEnum businessExceptionEnum, Object data) {
        this.status = businessExceptionEnum.getCode();
        this.msg = businessExceptionEnum.getMsg();
        this.data = data;
    }

    public static ApiRestResponse success() {
        return new ApiRestResponse();
    }

    public static  ApiRestResponse success(Object result){
        return new ApiRestResponse(result);
    }

    public static  ApiRestResponse errorException(BusinessExceptionEnum businessExceptionEnum) {
        return new ApiRestResponse(businessExceptionEnum);
    }

    public static ApiRestResponse error() {
        return new ApiRestResponse(BusinessExceptionEnum.FAILED);
    }

    public static ApiRestResponse errorEnum(BusinessExceptionEnum businessExceptionEnum) {
        return new ApiRestResponse(businessExceptionEnum);
    }


    public static ApiRestResponse errorMsg(String msg) {
        return new ApiRestResponse(BusinessExceptionEnum.FAILED, msg);
    }

    public static ApiRestResponse errorMap(Map map) {
        return new ApiRestResponse(BusinessExceptionEnum.FAILED, JsonUtil.objectToString(map));
    }
}
