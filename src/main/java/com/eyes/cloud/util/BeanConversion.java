package com.eyes.cloud.util;

import com.eyes.cloud.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Bean转换工具类
 * By CJ
 */
public class BeanConversion {
    public static <S, T> T copy(S source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        T target;
        try {
            target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new BusinessException("转换Bean错误");
        }
        return target;
    }

    public static <S, T> Optional<T> copyToOptional(S source, Class<T> targetClass) {
        return Optional.ofNullable(copy(source, targetClass));
    }

    public static <S, T> List<T> copyList(List<S> sources, Class<T> targetClass) {
        if (CollectionUtils.isEmpty(sources)) {
            return new ArrayList<>();
        }

        return sources.stream().map(source -> copy(source, targetClass)).collect(Collectors.toList());
    }

    public static <S, T> Set<T> copySet(Set<S> sources, Class<T> targetClass) {
        if (CollectionUtils.isEmpty(sources)) {
            return new HashSet<>();
        }

        return sources.stream().map(source -> copy(source, targetClass)).collect(Collectors.toSet());
    }

    public static <S, T> void copyToInstance(S source, T target) {
        if (source == null || target == null) {
            return;
        }
        BeanUtils.copyProperties(source, target);
    }
}