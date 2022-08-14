package com.git.bds.nyc.service;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.git.bds.nyc.domain.User;
import com.git.bds.nyc.domain.dto.WxUserInfo;
import com.github.yulichang.base.MPJBaseService;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 成大事
 * @since 2022-08-14 19:28:00
 */
public interface UserService extends MPJBaseService<User> {

    /**
     * 微信授权登录
     * @param code  微信的code
     * @return WxMaJscode2SessionResult
     */
    WxMaJscode2SessionResult login(String code) throws WxErrorException;

    /**
     * 获取用户信息
     * @param userInfo  包含一些加密的信息
     * @return  WxMaUserInfo
     */
    WxMaUserInfo getUserInfo(WxUserInfo userInfo);
}
