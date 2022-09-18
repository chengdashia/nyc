package com.git.bds.nyc.user.controller;

import com.git.bds.nyc.user.domain.dto.WxUserInfoDTO;
import com.git.bds.nyc.user.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
    public String login(@RequestBody WxUserInfoDTO userInfoDTO) throws WxErrorException {
        return userService.login(userInfoDTO);
    }

    @ApiOperation("测试")
    @GetMapping("/test")
    public String test(){
        return "hello world";
    }





}
