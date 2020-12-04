package com.bh.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bh.gmall.constant.SysCacheConstant;
import com.bh.gmall.pms.entity.ProductCategory;
import com.bh.gmall.pms.mapper.ProductCategoryMapper;
import com.bh.gmall.pms.service.ProductCategoryService;
import com.bh.gmall.vo.product.PmsProductCategoryWithChildrenItem;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author whj
 * @since 2020-07-11
 */
@Slf4j
@Service
@Component
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    ProductCategoryMapper categoryMapper;

    private Map<String, Object> map = new HashMap<>();

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;


    /**
     * 分布式缓存用redis来做
     *
     * @param i
     * @return
     */
    @Override
    public List<PmsProductCategoryWithChildrenItem> listCatelogWithChilder(Integer i) {
        Object cacheMenu = redisTemplate.opsForValue().get(SysCacheConstant.CATEGORY_MENU_CACHE_KEY);
        List<PmsProductCategoryWithChildrenItem> items;
        if (cacheMenu != null) {
            //缓存中有
            log.debug("菜单数据命中缓存......");
            items = (List<PmsProductCategoryWithChildrenItem>) cacheMenu;
        } else {
            items = categoryMapper.listCatelogWithChilder(i);
            redisTemplate.opsForValue().set(SysCacheConstant.CATEGORY_MENU_CACHE_KEY, items);
        }
        return items;
    }
}
