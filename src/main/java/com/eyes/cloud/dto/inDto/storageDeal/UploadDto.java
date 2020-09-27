package com.eyes.cloud.dto.inDto.storageDeal;

import lombok.Data;
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
    private String miner="t01800";
}
