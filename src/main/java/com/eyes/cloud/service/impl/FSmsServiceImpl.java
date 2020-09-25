package com.eyes.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eyes.cloud.entity.FSms;
import com.eyes.cloud.mapper.FSmsMapper;
import com.eyes.cloud.service.IFSmsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 短信模版表 服务实现类
 * </p>
 *
 * @author AC
 * @since 2020-09-25
 */
@Service
public class FSmsServiceImpl extends ServiceImpl<FSmsMapper, FSms> implements IFSmsService {

}
