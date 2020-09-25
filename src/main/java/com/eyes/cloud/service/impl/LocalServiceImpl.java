package com.eyes.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eyes.cloud.entity.Local;
import com.eyes.cloud.mapper.LocalMapper;
import com.eyes.cloud.service.ILocalService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author AC
 * @since 2020-09-25
 */
@Service
public class LocalServiceImpl extends ServiceImpl<LocalMapper, Local> implements ILocalService {

}
