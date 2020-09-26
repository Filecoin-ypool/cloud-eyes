package com.eyes.cloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eyes.cloud.common.dto.Result;
import com.eyes.cloud.dto.inDto.user.UserInDto;
import com.eyes.cloud.entity.FUser;
import com.eyes.cloud.exception.BusinessException;
import com.eyes.cloud.interceptor.CacheKey;
import com.eyes.cloud.mapper.FUserMapper;
import com.eyes.cloud.service.IFUserService;
import com.eyes.cloud.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * <p>
 * 网页用户表 服务实现类
 * </p>
 *
 * @author AC
 * @since 2020-09-25
 */
@Service
public class FUserServiceImpl extends ServiceImpl<FUserMapper, FUser> implements IFUserService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 注册
     *
     * @param inDto
     * @return
     */
    @Override
    @Transactional
    public Result register(UserInDto inDto) {
        LambdaQueryWrapper<FUser> lambdaQueryWrapper = Wrappers.<FUser>lambdaQuery().eq(FUser::getUsername, inDto.getUsername());
        List<FUser> list = list(lambdaQueryWrapper);
        if (list.size() > 0) {
            throw new BusinessException("用户名重复!");
        }
        if (StringUtils.isEmpty(inDto.getPassword())) {
            throw new BusinessException("密码不能为空");
        }
        FUser user = new FUser();
        user.setPassword(Md5Util.generate(inDto.getPassword()));
        user.setCreateAt(LocalDateTime.now());
        user.setUsername(inDto.getUsername());
        baseMapper.insert(user);
        return Result.ok("注册成功");
    }

    /**
     * 登录
     *
     * @param inDto
     * @return
     */
    @Override
    public Result login(UserInDto inDto) {
        //根据username查询
        LambdaQueryWrapper<FUser> wrapper = Wrappers.<FUser>lambdaQuery().eq(FUser::getUsername, inDto.getUsername());
        FUser user = baseMapper.selectOne(wrapper);
        if (user == null) {
            throw new BusinessException("用户名不存在");
        }
        boolean verify = Md5Util.verify(inDto.getPassword(), user.getPassword());
        if (!verify) {
            throw new BusinessException("密码错误");
        }
        //通过后获取生产token
        String token = createOrRemoveToken(user, true);
        return Result.ok(token);
    }

    /**
     * 生产token
     */
    private String createOrRemoveToken(FUser user, boolean isCreate) {
        String token = UUID.randomUUID().toString().replace("-", "");
        String loginTokenKey = CacheKey.getWebLoginTokenKey(token, user.getUsername());
        //单点登录
        String pattern = CacheKey.getWebLoginTokenKeyPatternByUsername(user.getUsername());
        Set<String> keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
        if (isCreate) {
            redisTemplate.opsForValue().set(loginTokenKey, JSON.toJSONString(user));
        }
        return token;
    }
}
