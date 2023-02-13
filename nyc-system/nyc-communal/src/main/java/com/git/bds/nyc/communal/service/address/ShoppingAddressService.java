package com.git.bds.nyc.communal.service.address;


import com.git.bds.nyc.communal.model.domain.ShoppingAddress;
import com.github.yulichang.base.MPJBaseService;

/**
 * <p>
 * 收货地址表（也可用于发布商品和需求的时候用作发货地址表） 服务类
 * </p>
 *
 * @author 成大事
 * @since 2022-11-14 15:09:55
 */
public interface ShoppingAddressService extends MPJBaseService<ShoppingAddress> {

    /**
     * 按id获取地址信息
     *
     * @param id 身份证件
     * @return {@link ShoppingAddress}
     */
    ShoppingAddress getAddressInfoById(Long id);
}
