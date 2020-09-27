package com.eyes.cloud.dto.inDto.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

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
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String username;

    /**
     * 电话
     */
    private Long phone;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;

}
