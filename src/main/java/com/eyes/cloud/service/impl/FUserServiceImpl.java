package com.eyes.cloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eyes.cloud.common.dto.Result;
import com.eyes.cloud.dto.inDto.user.FUserAddIn;
import com.eyes.cloud.dto.inDto.user.UserInDto;
import com.eyes.cloud.entity.FUser;
import com.eyes.cloud.exception.BusinessException;
import com.eyes.cloud.interceptor.CacheKey;
import com.eyes.cloud.mapper.FUserMapper;
import com.eyes.cloud.service.IFUserService;
import com.eyes.cloud.util.Md5Util;
import com.eyes.cloud.util.ShareCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
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
    public Result register(UserInDto inDto) {
        //验证码 todo
        LambdaQueryWrapper<FUser> lambdaQueryWrapper = Wrappers.<FUser>lambdaQuery().eq(FUser::getPhone, inDto.getPhone());
        List<FUser> list = list(lambdaQueryWrapper);
        if (list.size() > 0) {
            throw new BusinessException("用户名重复!");
        }
        if (StringUtils.isEmpty(inDto.getPassword())) {
            throw new BusinessException("密码不能为空");
        }
        //判断邀请码是否存在
        if (null != inDto.getInvite()) {
            //查询用户
            QueryWrapper<FUser> fatherUserQueryWrapper = new QueryWrapper<>();
            fatherUserQueryWrapper.eq("invite", inDto.getInvite());
            Integer count = baseMapper.selectCount(fatherUserQueryWrapper);
            if (count < 1) {
                throw new BusinessException("不存在此邀请码，如无邀请人可不填写邀请码");
            }
        }

        //不存在，先添加
        FUserAddIn user = new FUserAddIn();
        user.setPhone(inDto.getPhone());
        user.setPassword(Md5Util.generate(inDto.getPassword()));
        user.setCreateAt(LocalDateTime.now());
        user.setPInvite(inDto.getInvite());
        baseMapper.addUser(user);
        //插入成功，保存邀请码
        if (null != user.getResultId()) {
            String code = ShareCodeUtil.toSerialCode(user.getResultId());
            FUser fUser = new FUser();
            fUser.setInvite(code);
            fUser.setId(user.getResultId());
            baseMapper.updateById(fUser);
        }
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
        //根据phone查询
        QueryWrapper<FUser> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", inDto.getPhone());
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
        long phone = user.getPhone();
        String token = UUID.randomUUID().toString().replace("-", "");
        String loginTokenKey = CacheKey.getWebLoginTokenKey(token, phone);
        //单点登录
        String pattern = CacheKey.getWebLoginTokenKeyPatternByPhone(phone);
        Set<String> keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
        if (isCreate) {
            redisTemplate.opsForValue().set(loginTokenKey, JSON.toJSONString(user));
        }
        return token;
    }
}
