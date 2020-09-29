package com.eyes.cloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eyes.cloud.common.dto.Result;
import com.eyes.cloud.entity.Miner;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AC
 * @since 2020-09-29
 */
public interface IMinerService extends IService<Miner> {
    /**
     * 获取矿工列表
     * @return
     */
    Result getList();
}
