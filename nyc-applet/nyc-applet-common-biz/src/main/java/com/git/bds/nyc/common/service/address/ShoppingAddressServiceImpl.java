package com.git.bds.nyc.common.service.address;


import com.git.bds.nyc.common.mapper.mp.ShoppingAddressMapper;
import com.git.bds.nyc.common.model.domain.ShoppingAddress;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收货地址表（也可用于发布商品和需求的时候用作发货地址表） 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-11-14 15:09:55
 */
@Service
public class ShoppingAddressServiceImpl extends MPJBaseServiceImpl<ShoppingAddressMapper, ShoppingAddress> implements ShoppingAddressService {

}
