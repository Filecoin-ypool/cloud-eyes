package com.eyes.cloud.dto.outDto.storageDeal;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author dyk
 * @version V1.0.0
 * @description
 * @date 2020/9/25
 * @since 1.0
 */
@Data
public class StorageDealOutDto {
    private Integer id;

    /**
     * import返回的cid
     */
    private String cid;

    /**
     * 发起交易返回的cid
     */
    private String dealCid;

    /**
     * 交易id
     */
    private String dealId;

    /**
     * 支付钱包地址
     */
    private String wallet;

    /**
     * 存储矿工编号
     */
    private String miner;

    /**
     * 支付价格(单位是位/时间段)
     */
    private String epochPrice;

    /**
     * 最小存储时间段
     */
    private String minBlocksDuration;

    /**
     * 存储时间(指成功传输到服务器)
     */
    private LocalDateTime date;

    /**
     * 文件名(文件会根据存储时间存储到不同的文件夹)
     */
    private String fileName;

    /**
     * 存储交易状态
     */
    private String storageDealStatus;

    /**
     * 内部数据状态
     */
    private Integer status;
}
