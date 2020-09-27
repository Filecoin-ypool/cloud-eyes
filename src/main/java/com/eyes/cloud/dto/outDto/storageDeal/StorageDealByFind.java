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
public class StorageDealByFind {
    private String proposalCid;
    private Integer state;
    private String message;
    private String provider;
    private String dataRef;
    private String cid;
    private String pieceCid;
    private String size;
    private String pricePerEpoch;
    private String duration;
    private String dealId;
}
