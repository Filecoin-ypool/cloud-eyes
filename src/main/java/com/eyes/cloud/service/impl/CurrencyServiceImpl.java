package com.eyes.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eyes.cloud.entity.Currency;
import com.eyes.cloud.mapper.CurrencyMapper;
import com.eyes.cloud.service.ICurrencyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 币种表 服务实现类
 * </p>
 *
 * @author AC
 * @since 2020-09-25
 */
@Service
public class CurrencyServiceImpl extends ServiceImpl<CurrencyMapper, Currency> implements ICurrencyService {

}
