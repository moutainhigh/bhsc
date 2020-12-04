package com.bh.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bh.gmall.pms.entity.ProductAttributeCategory;
import com.bh.gmall.pms.mapper.ProductAttributeCategoryMapper;
import com.bh.gmall.pms.service.ProductAttributeCategoryService;
import com.bh.gmall.vo.PageInfoVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author whj
 * @since 2020-07-11
 */
@Component
@Service
public class ProductAttributeCategoryServiceImpl extends ServiceImpl<ProductAttributeCategoryMapper, ProductAttributeCategory> implements ProductAttributeCategoryService {

    @Autowired
    ProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Override
    public PageInfoVo roductAttributeCategoryPageInfo(Integer pageNum, Integer pageSize) {


        IPage<ProductAttributeCategory> page = productAttributeCategoryMapper.selectPage(new Page<ProductAttributeCategory>(pageNum, pageSize), null);


        //返回分页数据对象
        return PageInfoVo.getVo(page, pageSize.longValue());
    }
}
