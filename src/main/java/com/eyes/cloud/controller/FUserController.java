package com.eyes.cloud.controller;


import com.eyes.cloud.common.dto.Result;
import com.eyes.cloud.config.CommonValue;
import com.eyes.cloud.dto.inDto.user.UserInDto;
import com.eyes.cloud.interceptor.Common;
import com.eyes.cloud.interceptor.UserLoginToken;
import com.eyes.cloud.service.IFUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 网页用户表 前端控制器
 * </p>
 *
 * @author AC
 * @since 2020-09-25
 */
@RestController
@RequestMapping(CommonValue.API_PREFIX + "/f_user")
@UserLoginToken
public class FUserController {
    @Autowired
    IFUserService userService;

    /**
     * 用户注册
     * @param inDto
     * @return
     */
    @PostMapping("/register")
    @UserLoginToken(required = false)
    Result register(UserInDto inDto){
        return userService.register(inDto);
    }

    /**
     * 用户登录
     * @param inDto
     * @return
     */
    @PostMapping("/login")
    @UserLoginToken(required = false)
    Result login(UserInDto inDto){
        return userService.login(inDto);
    }


}
