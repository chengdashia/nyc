package com.git.bds.nyc.convert;

import com.git.bds.nyc.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author 成大事
 * @since 2022/9/14 20:16
 */
@Mapper
public interface UserConvert {
   UserConvert INSTANCE =  Mappers.getMapper(UserConvert.class);


    /**
     * 给用户
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
}
