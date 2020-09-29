package com.eyes.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eyes.cloud.dto.outDto.storageDeal.StatisticsDto;
import com.eyes.cloud.dto.outDto.storageDeal.StorageDealDayList;
import com.eyes.cloud.dto.outDto.storageDeal.StorageDealOutDto;
import com.eyes.cloud.entity.StorageDeal;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author AC
 * @since 2020-09-25
 */
public interface StorageDealMapper extends BaseMapper<StorageDeal> {
    /**
     * 根据用户id获取他所有的日期分类列表
     * @param uid
     * @return
     */
    List<StorageDealDayList> getDayList(Integer uid);

    /**
     * 根据日期字符串和用户id获取该用户该日期下所有内容
     * @param uid
     * @param day
     * @return
     */
    List<StorageDealOutDto> getList(Integer uid,String day);

    /**
     * 统计查询
     * @param uid
     * @return
     */
    List<StatisticsDto> statistics(Integer uid);
}
