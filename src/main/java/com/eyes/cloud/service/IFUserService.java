package com.eyes.cloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eyes.cloud.common.dto.Result;
import com.eyes.cloud.dto.inDto.user.UserInDto;
import com.eyes.cloud.entity.FUser;

/**
 * <p>
 * 网页用户表 服务类
 * </p>
 *
 * @author AC
 * @since 2020-09-25
 */
public interface IFUserService extends IService<FUser> {
    /**
     * 注册
     * @param inDto
     * @return
     */
    Result register(UserInDto inDto);

    /**
     * 登录
     * @param inDto
     * @return
     */
    Result login(UserInDto inDto);
}
