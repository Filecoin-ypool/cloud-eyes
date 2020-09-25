package com.eyes.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eyes.cloud.entity.AUser;
import com.eyes.cloud.mapper.AUserMapper;
import com.eyes.cloud.service.IAUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员用户表 服务实现类
 * </p>
 *
 * @author AC
 * @since 2020-09-25
 */
@Service
public class AUserServiceImpl extends ServiceImpl<AUserMapper, AUser> implements IAUserService {

}
