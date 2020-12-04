package com.bh.gmall.ums.service;

import com.bh.gmall.ums.entity.Member;
import com.bh.gmall.ums.entity.MemberReceiveAddress;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author whj
 * @since 2020-07-11
 */
public interface MemberService extends IService<Member> {

    Member login(String username, String password);

    Member getMemberByAccessToken(String accessToken);

    MemberReceiveAddress getMemberAddressByAddressId(Long addressId);

    List<MemberReceiveAddress> getMemberAddress(Long id);
}
