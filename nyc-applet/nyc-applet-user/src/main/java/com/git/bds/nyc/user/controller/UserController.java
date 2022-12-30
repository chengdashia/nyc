package com.git.bds.nyc.user.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import com.git.bds.nyc.communal.convert.AddressConvert;
import com.git.bds.nyc.communal.model.domain.ShoppingAddress;
import com.git.bds.nyc.communal.model.dto.AddressAddDTO;
import com.git.bds.nyc.communal.model.dto.AddressModifyDTO;
import com.git.bds.nyc.communal.model.dto.ShoppingAddressDTO;
import com.git.bds.nyc.communal.service.address.ShoppingAddressService;
import com.git.bds.nyc.enums.DefaultType;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.user.convert.UserConvert;
import com.git.bds.nyc.user.domain.User;
import com.git.bds.nyc.user.domain.dto.UserWxInfoDTO;
import com.git.bds.nyc.user.domain.dto.WxUserInfoDTO;
import com.git.bds.nyc.user.domain.vo.ShoppingAddressVO;
import com.git.bds.nyc.user.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 成大事
 * @since 2022-08-14 19:28:00
 */
@Api(tags = "用户")
@Slf4j
@Validated
@RestController
@RequestMapping("/wx/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {

    private final UserService userService;

    @ApiOperation("微信登录")
    @PostMapping("/login")
    public String login(@RequestBody WxUserInfoDTO userInfoDTO) throws WxErrorException {
        return userService.login(userInfoDTO);
    }

    @ApiOperation("测试  token")
    @GetMapping("/loginTest")
    public Object loginTest(){
        User user = userService.getById(22);
        StpUtil.login(user.getId());
        return StpUtil.getTokenInfo();
    }














}
