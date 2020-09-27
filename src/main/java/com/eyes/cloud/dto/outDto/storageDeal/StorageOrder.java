package com.eyes.cloud.dto.outDto.storageDeal;

import lombok.Data;

/**
 * @author dyk
 * @version V1.0.0
 * @description
 * @date 2020/9/27
 * @since 1.0
 */
@Data
public class StorageOrder {
    private String error;
    private Long size;
    private String minPrice;
    private String unsealPrice;
    private Long paymentInterval;
    private Long paymentIntervalIncrease;
    private String miner;
    private MinerPeer minerPeer;
}
