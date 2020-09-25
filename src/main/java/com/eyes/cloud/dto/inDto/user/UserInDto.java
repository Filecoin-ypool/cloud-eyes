package com.eyes.cloud.dto.inDto.user;

import lombok.Data;

/**
 * @author dyk
 * @version V1.0.0
 * @description
 * @date 2020/9/25
 * @since 1.0
 */
@Data
public class UserInDto {
    /**
     * 电话
     */
    private Long phone;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String code;
    /**
     * 邀请码
     */
    private String invite;
}
