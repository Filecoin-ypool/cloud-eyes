package com.eyes.cloud.interceptor;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author fugq
 * @version V1.0
 * @date 18-4-10 上午9:55
 */
@Component
@ConfigurationProperties(prefix = "common")
@Data
public class Common {
    public final static String API = "/api/v1";
    public final static String USER_ID = "user_id";
    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
}
