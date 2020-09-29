package com.eyes.cloud.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.eyes.cloud.common.enums.ResCodeEnum;
import com.eyes.cloud.exception.BusinessException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * 登陆状态验证拦截器
 *
 * @author alibeibei
 */
@Data
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private Common common;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Class<?> beanType = handlerMethod.getBeanType();
        UserLoginToken methodAnnotation = method.getAnnotation(UserLoginToken.class);
        UserLoginToken classAnnotation = beanType.getAnnotation(UserLoginToken.class);

        boolean verifyToken = false;
        boolean classToken = false;
        if (classAnnotation != null) {
            classToken = classAnnotation.required();
        }
        if (classToken) {
            if (methodAnnotation == null) {
                verifyToken = true;
            } else {
                verifyToken = methodAnnotation.required();
            }
        } else {
            if (methodAnnotation != null) {
                verifyToken = methodAnnotation.required();
            }
        }

        //web通道验证
        if (verifyToken) {
            String token = request.getHeader("token");
            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ":" + token);
            if (StringUtils.isEmpty(token)) {
//                throw new BusinessException(ResCodeEnum.TOKEN_ERROR);
                request.setAttribute(Common.USER_ID, 0);
                Common.threadLocal.set(0);
                return true;
            }
            String pattern = CacheKey.getWebLoginTokenKeyPatternByToken(token);
            Set<String> keys = redisTemplate.keys(pattern);
            if (keys == null || keys.size() == 0) {
                throw new BusinessException(ResCodeEnum.TOKEN_ERROR);
            }
            String key = keys.toArray(new String[]{})[0];
            String value = redisTemplate.opsForValue().get(key);
            if (StringUtils.isEmpty(value)) {
                throw new BusinessException(ResCodeEnum.TOKEN_ERROR);
            }
            JSONObject obj = JSONObject.parseObject(value);
            int uid = obj.getInteger("id");
            request.setAttribute(Common.USER_ID, uid);
            Common.threadLocal.set(uid);
            return true;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Common.threadLocal.remove();
    }
}
