package com.eyes.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eyes.cloud.dto.inDto.user.FUserAddIn;
import com.eyes.cloud.entity.FUser;

/**
 * <p>
 * 网页用户表 Mapper 接口
 * </p>
 *
 * @author AC
 * @since 2020-09-25
 */
public interface FUserMapper extends BaseMapper<FUser> {

    /**
     * 添加用户，姓名，已经邀请关系等
     * @param user
     * @return
     */
    int addUser(FUserAddIn user);
}
