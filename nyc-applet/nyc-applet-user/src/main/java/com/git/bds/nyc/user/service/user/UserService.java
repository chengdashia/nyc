package com.git.bds.nyc.user.service.user;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.git.bds.nyc.common.model.domain.ShoppingAddress;
import com.git.bds.nyc.common.model.dto.ShoppingAddressDTO;
import com.git.bds.nyc.user.domain.User;
import com.git.bds.nyc.user.domain.dto.WxUserInfoDTO;
import com.github.yulichang.base.MPJBaseService;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;

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
     * 登录
     *
     * @param wxUserInfoDTO WX用户信息DTO
     * @return {@link WxMaJscode2SessionResult}
     * @throws WxErrorException wx错误异常
     */
    String login(WxUserInfoDTO wxUserInfoDTO) throws WxErrorException;

    /**
     * 获取自助购物地址
     *
     * @return {@link List}<{@link ShoppingAddress}>
     */
    List<ShoppingAddressDTO> getSelfShoppingAddress();


}
