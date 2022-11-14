package com.git.bds.nyc.user.service.user;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.git.bds.nyc.common.model.domain.ShoppingAddress;
import com.git.bds.nyc.common.model.dto.ShoppingAddressDTO;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.user.convert.UserConvert;
import com.git.bds.nyc.user.mapper.user.UserMapper;
import com.git.bds.nyc.user.domain.User;
import com.git.bds.nyc.user.domain.dto.WxUserInfoDTO;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-08-14 19:28:00
 */
@Slf4j
@Service
public class UserServiceImpl extends MPJBaseServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private WxMaService wxMaService;


    /**
     * 登录
     *
     * @param wxUserInfoDTO WX用户信息DTO
     * @return {@link WxMaJscode2SessionResult}
     */
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String login(WxUserInfoDTO wxUserInfoDTO) {
        User user;
        WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(wxUserInfoDTO.getCode());
        String openid = session.getOpenid();
        user = this.baseMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getId)
                .eq(User::getOpenid, openid));
        //登录
        if(user == null){
            if (!wxMaService.getUserService().checkUserInfo(session.getSessionKey(), wxUserInfoDTO.getRawData(), wxUserInfoDTO.getSignature())) {
                WxMaConfigHolder.remove();//清理ThreadLocal
                throw new BusinessException("用户信息有误");
            }
            // 解密用户信息
            WxMaUserInfo wxMaUserInfo = wxMaService.getUserService()
                    .getUserInfo(session.getSessionKey(), wxUserInfoDTO.getEncryptedData(), wxUserInfoDTO.getIv());
            user = UserConvert.INSTANCE.toUser(openid,wxMaUserInfo.getNickName(),wxMaUserInfo.getAvatarUrl());
            this.baseMapper.insert(user);
            WxMaConfigHolder.remove();//清理ThreadLocal
        }else {
            // 登录
            this.baseMapper.updateById(user);
            StpUtil.login(user.getId());
        }
        return StpUtil.getTokenValue();
    }

    @Override
    public List<ShoppingAddressDTO> getSelfShoppingAddress() {
       return this.baseMapper.selectJoinList(ShoppingAddressDTO.class,
               new MPJLambdaWrapper<>()
                       .select(ShoppingAddress::getId,
                               ShoppingAddress::getConsignee,
                               ShoppingAddress::getPhone,
                               ShoppingAddress::getLocation,
                               ShoppingAddress::getDetailedAddress,
                               ShoppingAddress::getIsDefault)
                       .leftJoin(ShoppingAddress.class,ShoppingAddress::getUserId,User::getId)
                       .eq(ShoppingAddress::getUserId,StpUtil.getLoginIdAsLong()));
    }

}
