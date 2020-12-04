package com.bh.gmall.pms.mapper;

import com.bh.gmall.pms.entity.ProductAttribute;
import com.bh.gmall.pms.entity.ProductAttributeValue;
import com.bh.gmall.to.es.EsProductAttributeValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 存储产品参数信息的表 Mapper 接口
 * </p>
 *
 * @author whj
 * @since 2020-07-11
 */
public interface ProductAttributeValueMapper extends BaseMapper<ProductAttributeValue> {

    List<EsProductAttributeValue> selectProductBaseAttrAndValue(Long id);

    List<ProductAttribute> selectProductSaleAttrName(Long id);
}
