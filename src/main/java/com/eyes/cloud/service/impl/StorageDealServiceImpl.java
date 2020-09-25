package com.eyes.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eyes.cloud.common.dto.Result;
import com.eyes.cloud.dto.outDto.storageDeal.StorageDealDayList;
import com.eyes.cloud.dto.outDto.storageDeal.StorageDealOutDto;
import com.eyes.cloud.entity.StorageDeal;
import com.eyes.cloud.mapper.StorageDealMapper;
import com.eyes.cloud.service.IStorageDealService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author AC
 * @since 2020-09-25
 */
@Service
public class StorageDealServiceImpl extends ServiceImpl<StorageDealMapper, StorageDeal> implements IStorageDealService {
    /**
     * 获取日期分类列表
     *
     * @param uid
     * @return
     */
    @Override
    public Result getDayList(Integer uid) {
        List<StorageDealDayList> dayList = baseMapper.getDayList(uid);
        return Result.ok(dayList);
    }

    /**
     * 获取某日期下的内容列表
     *
     * @param uid
     * @param day
     * @return
     */
    @Override
    public Result getList(Integer uid, String day) {
        List<StorageDealOutDto> list = baseMapper.getList(uid, day);
        return Result.ok(list);
    }

    /**
     * 详情获取
     *
     * @param uid
     * @param id
     * @return
     */
    @Override
    public Result getById(Integer uid, String id) {
        LambdaQueryWrapper<StorageDeal> lambdaQueryWrapper = Wrappers.<StorageDeal>lambdaQuery().eq(StorageDeal::getUserId, uid).eq(StorageDeal::getId, id);
        StorageDeal storageDeal = getOne(lambdaQueryWrapper);
        return Result.ok(storageDeal);
    }
}
