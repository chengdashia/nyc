package com.git.bds.nyc.user.controller.user;

import cn.dev33.satoken.stp.StpUtil;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.user.model.domain.User;
import com.git.bds.nyc.user.model.dto.UserViewDTO;
import com.git.bds.nyc.user.model.dto.WxUserInfoDTO;
import com.git.bds.nyc.user.model.vo.LoginVO;
import com.git.bds.nyc.user.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
@Validated
@RestController
@RequestMapping("/applet/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {

    private final UserService userService;

    /**
     * 登录
     *
     * @param userInfoDTO 用户信息dto
     * @return {@link R}<{@link LoginVO}>
     * @throws WxErrorException wx错误异常
     */
    @ApiOperation("微信登录")
    @PostMapping("/login")
    public R<LoginVO> login(@RequestBody WxUserInfoDTO userInfoDTO) throws WxErrorException {
        return R.ok(userService.login(userInfoDTO));
    }

    /**
     * 修改用户登录信息
     *
     * @param userViewDTO user wx info dto
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation(value = "更新用户头像和昵称")
    @PostMapping("/modifyUserViewInfo")
    public R<Boolean> modifyUserViewInfo(@Validated @RequestBody UserViewDTO userViewDTO){
        return R.decide(userService.modifyUserViewInfo(userViewDTO));
    }

    @ApiOperation("测试  token")
    @GetMapping("/loginTest")
    public Object loginTest(
            Long id
    ){
        User user = userService.getById(id);
        StpUtil.login(user.getId());
        return StpUtil.getTokenInfo();
    }














}
