package com.bh.gmall.cart.service;

import com.bh.gmall.cart.vo.CartItem;
import com.bh.gmall.cart.vo.CartResponse;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 购物车服务
 */
public interface CartService {

    /**
     * 添加商品区购物车
     *
     * @param skuId
     * @param cartKey
     * @param accessToken
     * @return
     */
    CartResponse addToCart(Long skuId, Integer num, String cartKey, String accessToken) throws InterruptedException, ExecutionException;

    /**
     * 修改购物项数量
     *
     * @param skuId
     * @param num
     * @param cartKey
     * @param accessToken
     * @return
     */
    CartResponse updateCartItem(Long skuId, Integer num, String cartKey, String accessToken);

    /**
     * 获取购物车的所有数据
     *
     * @param cartKey
     * @param accessToken
     * @return
     */
    CartResponse listCart(String cartKey, String accessToken);

    /**
     * s删除购物项
     *
     * @param skuId
     * @param cartKey
     * @param accessToken
     * @return
     */
    CartResponse delCartItem(Long skuId, String cartKey, String accessToken);

    /**
     * 清空购物车
     *
     * @param cartKey
     * @param accessToken
     * @return
     */
    CartResponse clearCart(String cartKey, String accessToken);

    /**
     * 选中或不选择某些商品
     *
     * @param skuIds
     * @param ops
     * @param cartKey
     * @param accessToken
     * @return
     */
    CartResponse checkCartItems(String skuIds, Integer ops, String cartKey, String accessToken);

    /**
     * 获取某个用户的购物车选则的商品
     *
     * @param accessToken
     * @return
     */
    List<CartItem> getCartItemForOrder(String accessToken);

    void removeCartItem(String accessToken, List<Long> skuIds);
}
