package com.git.bds.nyc.communal.convert;

import com.git.bds.nyc.communal.model.domain.ShoppingAddress;
import com.git.bds.nyc.communal.model.dto.AddressAddDTO;
import com.git.bds.nyc.communal.model.dto.AddressModifyDTO;
import com.git.bds.nyc.communal.model.dto.ShoppingAddressDTO;
import com.git.bds.nyc.communal.model.vo.AddressInfoVO;
import com.git.bds.nyc.communal.model.vo.ShoppingAddressVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 成大事
 * @since 2022/11/26 19:29
 */
@Mapper
public interface AddressConvert {

    AddressConvert INSTANCE = Mappers.getMapper(AddressConvert.class);

    /**
     * 至购物地址
     *
     * @param addressAddDTO 地址添加数据
     * @param userId        用户id
     * @return {@link ShoppingAddress}
     */
    @Mapping(source = "userId",target = "userId")
    ShoppingAddress toShoppingAddress(AddressAddDTO addressAddDTO, long userId);


    /**
     * 至购物地址
     *
     * @param userId           用户id
     * @param addressModifyDTO 地址修改dto
     * @return {@link ShoppingAddress}
     */
    @Mapping(source = "userId",target = "userId")
    ShoppingAddress toShoppingAddress(AddressModifyDTO addressModifyDTO, long userId);


    /**
     * 购物狂
     *
     * @param list 列表
     * @return {@link List}<{@link ShoppingAddressVO}>
     */
    List<ShoppingAddressVO> toShoppingVOList(List<ShoppingAddressDTO> list);


    /**
     * 到地址info vo
     *
     * @param address 住址
     * @return {@link AddressInfoVO}
     */
    AddressInfoVO toAddressInfoVO(ShoppingAddress address);
}
