package com.git.bds.nyc.controller;

import com.git.bds.nyc.domain.dto.WxUserInfoDTO;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public R<Boolean> login(@RequestBody WxUserInfoDTO userInfoDTO) throws WxErrorException {
        return R.ok(userService.login(userInfoDTO));
    }





}
