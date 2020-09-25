package com.eyes.cloud.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author dyk
 * @version V1.0.0
 * @description
 * @date 2020/7/14
 * @since 1.0
 */
@Getter
@Component
public class MinerValues {


    @Value("${race.miner}")
    private String miner;

}