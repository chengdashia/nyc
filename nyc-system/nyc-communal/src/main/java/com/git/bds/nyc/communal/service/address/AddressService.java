package com.git.bds.nyc.communal.service.address;


import com.git.bds.nyc.communal.model.domain.Address;
import com.git.bds.nyc.communal.model.dto.AddressAddDTO;
import com.git.bds.nyc.communal.model.dto.AddressDTO;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * <p>
 * 收货地址表（也可用于发布商品和需求的时候用作发货地址表） 服务类
 * </p>
 *
 * @author 成大事
 * @since 2023-02-15 13:50:16
 */
public interface AddressService extends MPJBaseService<Address> {

    /**
     * 按id获取地址信息
     *
     * @param id 地址id
     * @return {@link Address}
     */
    Address getAddressInfoById(Long id);

    /**
     * 添加地址
     *
     * @param addressAddDTO 地址添加数据
     * @return {@link Boolean}
     */
    Boolean addAddress(AddressAddDTO addressAddDTO);

    /**
     * 修改默认地址
     *
     * @param id 身份证件
     * @return {@link Boolean}
     */
    Boolean modifyDefaultAddress(Long id);

    /**
     * 获取我的地址列表
     *
     * @return {@link List}<{@link AddressDTO}>
     */
    List<AddressDTO> getMyAddress();

}
