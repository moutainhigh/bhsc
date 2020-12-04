package com.bh.gmall.pms.service;

import com.bh.gmall.pms.entity.ProductCategory;
import com.bh.gmall.vo.product.PmsProductCategoryWithChildrenItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author whj
 * @since 2020-07-11
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    /**
     * 查询这个菜单及其子菜单
     *
     * @param i
     * @return
     */
    List<PmsProductCategoryWithChildrenItem> listCatelogWithChilder(Integer i);
}
