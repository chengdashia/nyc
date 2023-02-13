package com.git.bds.nyc.applet.api.service.address;

import com.git.bds.nyc.communal.model.dto.ShoppingAddressDTO;

import java.util.List;

/**
 * @author 成大事
 * @since 2023/2/13 16:41
 */
public interface AddressService {


    /**
     * 获取自助购物地址
     *
     * @return {@link List}<{@link ShoppingAddressDTO}>
     */
    List<ShoppingAddressDTO> getSelfShoppingAddress();
}
