package com.bh.gmall.search;

import com.bh.gmall.vo.search.SearchParam;
import com.bh.gmall.vo.search.SearchResponse;

/**
 * 商品检索服务
 */
public interface SearchProductService {
    SearchResponse searchProduct(SearchParam searchParam);
}
