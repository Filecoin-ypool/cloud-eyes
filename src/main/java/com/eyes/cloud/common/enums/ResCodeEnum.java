package com.eyes.cloud.common.enums;

public enum ResCodeEnum {
    //code码
    SUCCESS(200, "success"),
    ERROR(-1, "系统繁忙"),
    UPDATE_FAIL(5001, "修改失败"),
    AUTHORITY_FAIL(401, "权限不足"),
    TOKEN_ERROR(400, "token异常");

    private int code;
    private String msg;

    ResCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
