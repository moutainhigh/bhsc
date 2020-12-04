package com.bh.gmall.portal.controller.payment;


import com.alibaba.dubbo.config.annotation.Reference;

import com.bh.gmall.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/ali/pay")
public class PaymentController {


    @Reference
    PaymentService PaymentService;

    /**
     * 去支付
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/pay", produces = {"text/html"})
    public String pay(@RequestParam("orderSn") String orderSn,
                      @RequestParam("accessToken") String accessToken) {
        String string = PaymentService.pay(orderSn, accessToken);
        return string;
    }

    /**
     * 接收支付宝异步通知
     */
    @ResponseBody
    @RequestMapping("/pay/success/async")
    public String paySuccess(HttpServletRequest request) throws UnsupportedEncodingException {

        //封装支付宝数据
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        log.debug("订单【{}】===支付宝支付异步通知进来....", params.get("out_trade_no"));

        String result = PaymentService.resolvePayResult(params);
        return result;
    }
}
