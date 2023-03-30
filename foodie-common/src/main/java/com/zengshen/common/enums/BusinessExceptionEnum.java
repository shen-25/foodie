package com.zengshen.common.enums;

public enum BusinessExceptionEnum {

    /**
     * 枚举类型
     */

    SUCCESS(200 , "SUCCESS"),
    FAILED(500, "FAILED"),

    // 50x


    // 用户 相关错误 60x
    USER_NOT_EXIST(600, "用户不存在"),
    PASSWORD_ERROR(601, "密码不正确");

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
