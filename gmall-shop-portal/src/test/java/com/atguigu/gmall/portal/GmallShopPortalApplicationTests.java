package com.atguigu.gmall.portal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallShopPortalApplicationTests {

    @Test
    public void contextLoads() {
        int i = new BigDecimal("10.00").compareTo(new BigDecimal("10.00"));
        System.out.println(i);
    }

}
