package com.git.bds.nyc.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.git.bds.nyc.domain.User;
import com.git.bds.nyc.dao.UserDao;
import com.git.bds.nyc.domain.dto.WxUserInfo;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.result.ResultCode;
import com.git.bds.nyc.service.UserService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.SneakyThrows;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-08-14 19:28:00
 */
@Service
public class UserServiceImpl extends MPJBaseServiceImpl<UserDao, User> implements UserService {

    @Resource
    private WxMaService wxMaService;

    /**
     * 微信授权登录
     * @param code  微信的code
     * @return WxMaJscode2SessionResult
     */
    @Override
    public WxMaJscode2SessionResult login(String code) {
        WxMaJscode2SessionResult session;
        try {
            session = wxMaService.getUserService().getSessionInfo(code);
        } catch (WxErrorException e) {
            throw new BusinessException(ResultCode.WX_ERROR.getCode(),e.getMessage());
        }finally {
            WxMaConfigHolder.remove();//清理ThreadLocal
        }
        String openid = session.getOpenid();
        User user = this.baseMapper.selectOne(new LambdaQueryWrapper<User>()
                .select(User::getId)
                .eq(User::getOpenid, openid));
        //登录
        if(user == null){
            throw new BusinessException(ResultCode.NOT_EXIST.getCode(), ResultCode.NOT_EXIST.getMessage());
        }
        StpUtil.login(user.getId());

        return session;
    }

    @Override
    public WxMaUserInfo getUserInfo(WxUserInfo userInfo) {

        // 用户信息校验
        if (!wxMaService.getUserService().checkUserInfo(userInfo.getSessionKey(), userInfo.getRawData(), userInfo.getSignature())) {
            WxMaConfigHolder.remove();//清理ThreadLocal
            throw new BusinessException("youwu");
        }

        // 解密用户信息
        WxMaUserInfo wxMaUserInfo = wxMaService.getUserService().getUserInfo(userInfo.getSessionKey(), userInfo.getEncryptedData(), userInfo.getIv());
        WxMaConfigHolder.remove();//清理ThreadLocal
        return wxMaUserInfo;
    }
}
