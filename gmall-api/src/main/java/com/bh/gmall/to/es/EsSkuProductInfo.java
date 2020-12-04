package com.bh.gmall.to.es;

import com.bh.gmall.pms.entity.SkuStock;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EsSkuProductInfo extends SkuStock implements Serializable {


    private String skuTitle;//sku的特定标题
    /**
     * 每个sku不同的属性以及他的值
     * <p>
     * 颜色：黑色
     * 内存：128
     * <p>
     * 销售属性名；
     */
    List<EsProductAttributeValue> attributeValues;


}
