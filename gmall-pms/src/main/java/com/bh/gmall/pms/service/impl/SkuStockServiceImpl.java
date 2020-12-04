package com.bh.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bh.gmall.pms.entity.SkuStock;
import com.bh.gmall.pms.mapper.SkuStockMapper;
import com.bh.gmall.pms.service.SkuStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * <p>
 * sku的库存 服务实现类
 * </p>
 *
 * @author whj
 * @since 2020-07-11
 */
@Service
@Component
public class SkuStockServiceImpl extends ServiceImpl<SkuStockMapper, SkuStock> implements SkuStockService {


    @Autowired
    SkuStockMapper skuStockMapper;

    @Override
    public BigDecimal getSkuPriceBySkuId(Long skuId) {
        return skuStockMapper.selectById(skuId).getPrice();
    }
}
