package com.eyes.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eyes.cloud.common.dto.Result;
import com.eyes.cloud.entity.Miner;
import com.eyes.cloud.mapper.MinerMapper;
import com.eyes.cloud.service.IMinerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author AC
 * @since 2020-09-29
 */
@Service
public class MinerServiceImpl extends ServiceImpl<MinerMapper, Miner> implements IMinerService {
    /**
     * 获取矿工列表
     *
     * @return
     */
    @Override
    public Result getList() {
        List<Miner> list = list();
        List<String> strList = new ArrayList<>();
        list.forEach(miner -> strList.add(miner.getMiner()));
        return Result.ok(strList);
    }
}
