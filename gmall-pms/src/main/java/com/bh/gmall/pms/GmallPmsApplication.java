package com.bh.gmall.pms;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 缓存的使用场景：
 * 一些固定的数据，不太变化的数据，高频访问的数据（基本不变），变化频率低的都可以入缓存，加速系统的访问。
 * 缓存的目的：提高系统查询效率，提供性能
 * <p>
 * <p>
 * 1）、将菜单缓存起来，以后查询直接去缓存中拿即可；
 * 设计模式：模板模式：
 * 操作xxx都有对应的xxxTemplate；
 * JdbcTemplate、RestTemplate、RedisTemplate、MongoTemplate
 * <p>
 * RedisTemplate<Object, Object>；  k-v；
 * v有五种类型、String、V
 * StringRedisTemplate: k-v都是String的。
 * <p>
 * 引入一个场景，猜这个场景的xxxAutoConfiguration，
 * 帮我们注入能操作这个技术的组件，这个场景的配置信息都在xxxProperties中说明了(prefix = "spring.redis")使用哪种前缀配置
 * <p>
 * <p>
 * 2、如果发现事务加不上。开启基于注解的事务功能  @EnableTransactionManagement
 * 如果要真的开启什么功能就显式的加上这个注解。。。。
 * <p>
 * 3、事务的最终解决方案；
 * 1）、普通加事务。导入jdbc-starter，@EnableTransactionManagement，加@Transactional
 * 2）、方法自己调自己类里面的加不上事务。
 * 1）、导入aop包，开启代理对象的相关功能
 * <dependency>
 * <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-boot-starter-aop</artifactId>
 * </dependency>
 * 2）、获取到当前类真正的代理对象，去掉方法即可
 * 1）、@EnableAspectJAutoProxy(exposeProxy = true):暴露代理对象
 * 2）、获取代理对象；
 */

@EnableAspectJAutoProxy(exposeProxy = true)
@EnableDubbo
@MapperScan(basePackages = "com.atguigu.gmall.pms.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class GmallPmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallPmsApplication.class, args);
    }

}
