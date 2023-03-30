package com.zengshen.common.enums;

public enum BusinessExceptionEnum {

    /**
     * 枚举类型
     */

    SUCCESS(200 , "SUCCESS"),
    FAILED(500, "FAILED"),

    // 50x


    // 用户 相关错误 60x
    USER_NOT_EXIST(500, "用户不存在"),
    USER_IS_EXIST(500, "用户已存在"),
    PASSWORD_DOES_NOT_MATCH(500, "两次密码不一致"),
    PASSWORD_ERROR(500, "密码不正确");

    // 业务码 0 代表成功
    private Integer code;

    // 响应消息
    private String msg;

    BusinessExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }
}
