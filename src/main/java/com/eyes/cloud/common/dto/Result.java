package com.eyes.cloud.common.dto;

import com.eyes.cloud.common.enums.ResCodeEnum;
import lombok.Data;

@Data
public final class Result {
    private int code;
    private String msg;
    private Object data;

    public Result() {
    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static Result ok() {
        return new Result(ResCodeEnum.SUCCESS.getCode(), "success", null);
    }

    /**
     * 保存方法统一抽取返回方法
     * @param b
     * @return
     */
    public static Result getResult(boolean b) {
        if (b) {
            return Result.ok();
        } else {
            return Result.build(ResCodeEnum.UPDATE_FAIL);
        }
    }

    public static Result ok(Object data) {
        return new Result(ResCodeEnum.SUCCESS.getCode(), "success", data);
    }

    public static Result build(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result build(int code, Object data) {
        return new Result(code, data);
    }

    public static Result build(ResCodeEnum resCodeEnum) {
        return new Result(resCodeEnum.getCode(), resCodeEnum.getMsg(), null);
    }
}
