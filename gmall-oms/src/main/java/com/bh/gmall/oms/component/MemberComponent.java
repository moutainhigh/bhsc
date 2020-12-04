package com.bh.gmall.oms.component;

import com.alibaba.fastjson.JSON;
import com.bh.gmall.constant.SysCacheConstant;
import com.bh.gmall.ums.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MemberComponent {

    @Autowired
    StringRedisTemplate redisTemplate;

    public Member getMemberByAccessToken(String accessToken) {
        String json = redisTemplate.opsForValue().get(SysCacheConstant.LOGIN_MEMBER + accessToken);
        if (!StringUtils.isEmpty(json)) {
            return JSON.parseObject(json, Member.class);
        }
        return null;
    }
}
