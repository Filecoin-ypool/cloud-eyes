package com.eyes.cloud.task;

import com.eyes.cloud.service.IStorageDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author dyk
 * @version V1.0.0
 * @description
 * @date 2020/9/27
 * @since 1.0
 */
@Service
public class DealsStatusTask {
    @Autowired
    IStorageDealService storageDealService;

    @Scheduled(initialDelayString = "5000", fixedDelayString = "5000")
    void cycleExamine() {
        System.out.println("开始执行交易表状态刷新任务:"+ LocalDateTime.now());
        storageDealService.refreshDealsList();
        System.out.println("交易表状态刷新任务执行成功");
    }
}
