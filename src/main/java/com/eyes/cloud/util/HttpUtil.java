package com.eyes.cloud.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Map;

/**
 * @author dyk
 * @version V1.0.0
 * @description
 * @date 2020/9/26
 * @since 1.0
 */
public class HttpUtil {

    /**
     * 生成post请求的JSON请求参数
     *
     * @return HttpEntity
     */
    public static HttpEntity<Map<String, Object>> generatePostJson(Map<String, Object> jsonMap,String token) {

        //如果需要其它的请求头信息、都可以在这里追加
        HttpHeaders httpHeaders = new HttpHeaders();


//        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        MediaType type = MediaType.parseMediaType("application/json");

        httpHeaders.setContentType(type);

        httpHeaders.set("Authorization","Bearer "+token);

        return new HttpEntity<>(jsonMap, httpHeaders);
    }
}
