package com.eyes.cloud.exception;

import com.eyes.cloud.common.enums.ResCodeEnum;
import lombok.Data;

/**
 * @author fugq
 */
@Data
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -5410668235095793003L;
    private int errCode;
    private String errMsg;

    public BusinessException() {
        super();
    }

    public BusinessException(String errMsg) {
        super(errMsg);
        errCode = -1;
        this.errMsg = errMsg;
    }

    public BusinessException(ResCodeEnum codeEnum) {
        super(codeEnum.getMsg());
        errCode = codeEnum.getCode();
        this.errMsg = codeEnum.getMsg();
    }

    public BusinessException(int errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
