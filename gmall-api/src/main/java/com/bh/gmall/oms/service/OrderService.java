package com.bh.gmall.oms.service;

import com.bh.gmall.oms.entity.Order;
import com.bh.gmall.oms.entity.OrderItem;
import com.bh.gmall.vo.order.OrderConfirmVo;
import com.bh.gmall.vo.order.OrderCreateVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author whj
 * @since 2020-07-11
 */
public interface OrderService extends IService<Order> {

    /**
     * 订单确认
     *
     * @param id
     * @return
     */
    OrderConfirmVo orderConfirm(Long id);

    OrderCreateVo createOrder(BigDecimal totalPrice, Long addressId, String note);

    Order selectOne(String order_sn);

    List<OrderItem> selectList(String orderSn);
}
