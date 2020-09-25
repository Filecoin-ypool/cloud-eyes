package com.eyes.cloud.common.enums;

import lombok.Getter;

/**
 * @author: fugq
 * @date: 2019/10/15 22:19
 * @description:
 */
@Getter
public enum VerifyCodeTypeEnum {
    register,
    login,
    create_trade_password,
    update_trade_password,
    update_password,
    forget_password, //忘记密码
    withdrawal_notify,
    update_phone,
    withdraw_verify, //提现验证码
    post_financing;  //post节点理财

}
