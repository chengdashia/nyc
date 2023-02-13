package com.git.bds.nyc.applet.api.service.address;

import cn.dev33.satoken.stp.StpUtil;
import com.git.bds.nyc.communal.mapper.mp.ShoppingAddressMapper;
import com.git.bds.nyc.communal.model.domain.ShoppingAddress;
import com.git.bds.nyc.communal.model.dto.ShoppingAddressDTO;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 成大事
 * @since 2023/2/13 16:41
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddressServiceImpl implements AddressService{


    private final ShoppingAddressMapper shoppingAddressMapper;

    /**
     * 获取自助购物地址
     *
     * @return {@link List}<{@link ShoppingAddressDTO}>
     */
    @Override
    public List<ShoppingAddressDTO> getSelfShoppingAddress() {
        return shoppingAddressMapper.selectJoinList(ShoppingAddressDTO.class,
                new MPJLambdaWrapper<ShoppingAddress>()
                        .select(ShoppingAddress::getId,
                                ShoppingAddress::getConsignee,
                                ShoppingAddress::getPhone,
                                ShoppingAddress::getLocation,
                                ShoppingAddress::getDetailedAddress,
                                ShoppingAddress::getIsDefault)
                        .eq(ShoppingAddress::getUserId, StpUtil.getLoginIdAsLong()));
    }
}
