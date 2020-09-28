package com.eyes.cloud.dto.inDto.storageDeal;

import lombok.Data;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author dyk
 * @version V1.0.0
 * @description
 * @date 2020/9/27
 * @since 1.0
 */
@Data
public class UploadDto {
    MultipartFile file;
    private String miner;

    public void setMiner(String miner) {
        if (StringUtils.isEmpty(miner) || "null".equals(miner)) {
            miner = "t010400";
        }
        this.miner = miner;
    }
}
