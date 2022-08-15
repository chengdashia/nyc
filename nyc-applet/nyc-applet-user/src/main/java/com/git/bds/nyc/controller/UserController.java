package com.git.bds.nyc.controller;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.git.bds.nyc.domain.dto.WxUserInfo;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @ApiImplicitParam(name = "code", value = "编号", required = true, dataTypeClass = String.class)
    public R<WxMaJscode2SessionResult> login(
            @RequestParam("code") String code
    ) throws WxErrorException {
        return R.ok(userService.login(code));
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @PostMapping("/getUserInfo")
    public R<WxMaUserInfo> getUserInfo(@RequestBody WxUserInfo userInfo) {
        return R.ok(userService.getUserInfo(userInfo));
    }




}
