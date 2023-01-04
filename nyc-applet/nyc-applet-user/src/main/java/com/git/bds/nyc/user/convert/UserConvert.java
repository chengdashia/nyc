package com.git.bds.nyc.user.convert;


import com.git.bds.nyc.communal.model.dto.ShoppingAddressDTO;
import com.git.bds.nyc.user.model.domain.User;
import com.git.bds.nyc.user.model.vo.ShoppingAddressVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 成大事
 * @since 2022/9/14 20:16
 */
@Mapper
public interface UserConvert {
   UserConvert INSTANCE =  Mappers.getMapper(UserConvert.class);


    /**
     * 用户
     *
     * @param openid     openid
     * @param screenName 昵称
     * @param avatar     头像
     * @return {@link User}
     */
    @Mapping(source = "openid",target = "openid")
    @Mapping(source = "screenName",target = "screenName")
    @Mapping(source = "avatar",target = "avatar")
    User toUser(String openid,String screenName,String avatar);

 /**
  * 购物狂
  *
  * @param list 列表
  * @return {@link List}<{@link ShoppingAddressVO}>
  */
 List<ShoppingAddressVO> toShoppingVOList(List<ShoppingAddressDTO> list);
}
