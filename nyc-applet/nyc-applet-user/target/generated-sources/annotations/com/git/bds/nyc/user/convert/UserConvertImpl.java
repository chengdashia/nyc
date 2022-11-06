package com.git.bds.nyc.user.convert;

import com.git.bds.nyc.user.domain.User;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-06T12:07:16+0800",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
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
}
