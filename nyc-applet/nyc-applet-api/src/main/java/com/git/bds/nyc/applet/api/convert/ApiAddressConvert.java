package com.git.bds.nyc.applet.api.convert;

import com.git.bds.nyc.applet.api.model.vo.address.AddressInfoVO;
import com.git.bds.nyc.applet.api.model.vo.address.AddressVO;
import com.git.bds.nyc.communal.model.domain.Address;
import com.git.bds.nyc.communal.model.dto.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 成大事
 * @since 2023/2/16 14:09
 */
@Mapper
public interface ApiAddressConvert {

    ApiAddressConvert INSTANCE = Mappers.getMapper(ApiAddressConvert.class);


    /**
     * 购物狂
     *
     * @param list 列表
     * @return {@link List}<{@link AddressVO}>
     */
    List<AddressVO> toVOList(List<AddressDTO> list);


    /**
     * 到地址info vo
     *
     * @param address 住址
     * @return {@link AddressInfoVO}
     */
    AddressInfoVO toAddressInfoVO(Address address);
}
