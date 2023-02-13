package com.git.bds.nyc.communal.service.address;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.git.bds.nyc.communal.mapper.mp.ShoppingAddressMapper;
import com.git.bds.nyc.communal.model.domain.ShoppingAddress;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收货地址表（也可用于发布商品和需求的时候用作发货地址表） 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-11-14 15:09:55
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ShoppingAddressServiceImpl extends MPJBaseServiceImpl<ShoppingAddressMapper, ShoppingAddress> implements ShoppingAddressService {

    /**
     * 按id获取地址信息
     *
     * @param id 身份证件
     * @return {@link ShoppingAddress}
     */
    @Override
    public ShoppingAddress getAddressInfoById(Long id) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<ShoppingAddress>()
                        .select(ShoppingAddress::getConsignee,
                                ShoppingAddress::getPhone,
                                ShoppingAddress::getLocation,
                                ShoppingAddress::getDetailedAddress)
                .eq(ShoppingAddress::getId,id));
    }
}
