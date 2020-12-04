package com.bh.gmall.pms.service;

import com.bh.gmall.pms.entity.ProductAttributeCategory;
import com.bh.gmall.vo.PageInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author whj
 * @since 2020-07-11
 */
public interface ProductAttributeCategoryService extends IService<ProductAttributeCategory> {

    /**
     * 分页查询所有的属性分类
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfoVo roductAttributeCategoryPageInfo(Integer pageNum, Integer pageSize);
}
