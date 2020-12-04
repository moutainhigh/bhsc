package com.bh.gmall.pms.service;

import com.bh.gmall.pms.entity.ProductAttribute;
import com.bh.gmall.vo.PageInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author whj
 * @since 2020-07-11
 */
public interface ProductAttributeService extends IService<ProductAttribute> {

    /**
     * 查出某个属性分类下所有的销售属性和基本参数
     *
     * @param cid
     * @param type
     * @param pageSize
     * @param pageNum
     * @return
     */
    PageInfoVo getCategoryAttributes(Long cid, Integer type, Integer pageSize, Integer pageNum);
}
