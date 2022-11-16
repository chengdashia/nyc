package com.git.bds.nyc.user.convert;

import com.git.bds.nyc.common.model.dto.ShoppingAddressDTO;
import com.git.bds.nyc.user.domain.User;
import com.git.bds.nyc.user.domain.vo.ShoppingAddressVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-16T19:28:17+0800",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_311 (Oracle Corporation)"
)
public class UserConvertImpl implements UserConvert {

    @Override
    public User toUser(String openid, String screenName, String avatar) {
        if ( openid == null && screenName == null && avatar == null ) {
            return null;
        }

        User user = new User();

        user.setOpenid( openid );
        user.setScreenName( screenName );
        user.setAvatar( avatar );

        return user;
    }

    @Override
    public List<ShoppingAddressVO> toShoppingVOList(List<ShoppingAddressDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ShoppingAddressVO> list1 = new ArrayList<ShoppingAddressVO>( list.size() );
        for ( ShoppingAddressDTO shoppingAddressDTO : list ) {
            list1.add( shoppingAddressDTOToShoppingAddressVO( shoppingAddressDTO ) );
        }

        return list1;
    }

    protected ShoppingAddressVO shoppingAddressDTOToShoppingAddressVO(ShoppingAddressDTO shoppingAddressDTO) {
        if ( shoppingAddressDTO == null ) {
            return null;
        }

        ShoppingAddressVO shoppingAddressVO = new ShoppingAddressVO();

        shoppingAddressVO.setId( shoppingAddressDTO.getId() );
        shoppingAddressVO.setConsignee( shoppingAddressDTO.getConsignee() );
        shoppingAddressVO.setPhone( shoppingAddressDTO.getPhone() );
        shoppingAddressVO.setLocation( shoppingAddressDTO.getLocation() );
        shoppingAddressVO.setDetailedAddress( shoppingAddressDTO.getDetailedAddress() );
        shoppingAddressVO.setIsDefault( shoppingAddressDTO.getIsDefault() );

        return shoppingAddressVO;
    }
}
