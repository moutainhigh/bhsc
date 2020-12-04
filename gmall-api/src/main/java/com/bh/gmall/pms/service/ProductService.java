package com.bh.gmall.pms.service;

import com.bh.gmall.pms.entity.Product;
import com.bh.gmall.to.es.EsProduct;
import com.bh.gmall.vo.PageInfoVo;
import com.bh.gmall.vo.product.PmsProductParam;
import com.bh.gmall.vo.product.PmsProductQueryParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author whj
 * @since 2020-07-11
 */
public interface ProductService extends IService<Product> {

    /**
     * 查询商品详情
     *
     * @param id
     * @return
     */
    Product productInfo(Long id);

    /**
     * 根据复杂查询条件返回分页数据
     *
     * @param productQueryParam
     * @return
     */
    PageInfoVo productPageInfo(PmsProductQueryParam productQueryParam);

    /**
     * 保存商品数据
     *
     * @param productParam
     */
    void saveProduct(PmsProductParam productParam);

    /**
     * 批量上下架
     *
     * @param ids
     * @param publishStatus
     */
    void updatePublishStatus(List<Long> ids, Integer publishStatus);

    /**
     * 查询商品详情
     *
     * @param id
     * @return
     */
    EsProduct productAllInfo(Long id);

    EsProduct produSkuInfo(Long id);
}
