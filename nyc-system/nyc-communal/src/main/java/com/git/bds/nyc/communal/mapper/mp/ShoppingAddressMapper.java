package com.git.bds.nyc.communal.mapper.mp;

import com.git.bds.nyc.communal.model.domain.ShoppingAddress;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 收货地址表（也可用于发布商品和需求的时候用作发货地址表） Mapper 接口
 * </p>
 *
 * @author 成大事
 * @since 2022-11-14 15:09:55
 */
@Mapper
public interface ShoppingAddressMapper extends MPJBaseMapper<ShoppingAddress> {

}
