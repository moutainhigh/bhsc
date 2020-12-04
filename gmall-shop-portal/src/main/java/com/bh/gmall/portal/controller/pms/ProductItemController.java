package com.bh.gmall.portal.controller.pms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bh.gmall.constant.ProductInfoConstant;
import com.bh.gmall.pms.service.ProductService;
import com.bh.gmall.to.CommonResult;
import com.bh.gmall.to.es.EsProduct;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
public class ProductItemController {

    @Reference
    ProductService productService;

    @Qualifier("mainThreadPoolExecutor")
    @Autowired
    ThreadPoolExecutor threadPoolExecutor;

    @Qualifier("otherThreadPoolExecutor")
    @Autowired
    ThreadPoolExecutor otherThreadPoolExecutor;

    @Autowired
    RedissonClient redisson;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    /**
     * 商品的详情
     *
     * @param id
     * @return
     */
    @GetMapping("/item/{id}.html")
    public CommonResult productInfo(@PathVariable("id") Long id) {

        EsProduct esProduct = productService.productAllInfo(id);
        return new CommonResult().success(esProduct);
    }


    /**
     * 根据skuId查询单个商品的详情，先去缓存中查，如果缓存中没有，获取分布式锁去es查再放入缓存中，保证es中的数据始终为新值
     *
     * @param id
     * @return
     */
    @GetMapping("/item/sku/{id}.html")
    public CommonResult productSkuInfo(@PathVariable("id") Long id) {
        EsProduct esProduct = null;

        /**
         * 先去缓存中查询
         */
        esProduct = (EsProduct) redisTemplate.opsForValue().get(ProductInfoConstant.SKU_PRODUCT_INFO + id);

        /**
         * 如果缓存中不存在（未缓存或者出现缓存击穿问题(大量并发去数据库查同一条数据)，防止大量并发去es/mysql），需要先去获取分布式锁
         */
        if (esProduct == null) {
            //获取分布式锁去es中查询，为使得锁粒度更细，锁名skuInfoLock_id
            RLock lock = redisson.getLock("skuInfoLock" + id);
            try {
                lock.lock(3, TimeUnit.SECONDS);
                esProduct = productService.produSkuInfo(id);
                //查到数据后将数据放到缓存中，设置一个默认过期时间
                redisTemplate.opsForValue().set(ProductInfoConstant.SKU_PRODUCT_INFO + id, esProduct, 1L, TimeUnit.DAYS);
            } finally {
                lock.unlock();
            }
        }


//        EsProduct esProduct = productService.produSkuInfo(id);
        return new CommonResult().success(esProduct);
    }


    /**
     * 数据库（商品的基本信息表、商品的属性表、商品的促销表）和  es(info/attr/sale)
     * <p>
     * 查加缓存
     * 1、第一次查。肯定长。
     *
     * @return
     */
    public EsProduct productInfo2(Long id) {


        CompletableFuture.supplyAsync(() -> {
            return "";
        }, threadPoolExecutor).whenComplete((r, e) -> {
            System.out.println("处理结果" + r);
            System.out.println("处理异常" + e);
        });
        //1、商品基本数据（名字介绍等） 100ms   异步


        //2、商品的属性数据  300ms
        new Thread(() -> {
            System.out.println("查属性信息");
        }).start();

        //3、商品的营销数据  SmsService 1s 500ms
        new Thread(() -> {
            System.out.println("查营销信息");
        }).start();
        //4、商品的配送数据  WuliuService 2s  700ms
        new Thread(() -> {
            System.out.println("查配送信息");
        }).start();
        //5、商品的增值服务数据  SaleService  1s 1s
        new Thread(() -> {
            System.out.println("查增值信息");
        }).start();

        //otherThreadPoolExecutor.submit()

        //8s  2.5s； 需要速度快。 开启异步化 最多1s，取决最长的服务调用。
        //高并发系统的优化
        //1、加缓存
        //2、开异步
        return null;
    }

}
