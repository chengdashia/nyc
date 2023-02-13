package com.git.bds.nyc.user.controller.farmer;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.git.bds.nyc.communal.service.address.ShoppingAddressService;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.user.model.domain.User;
import com.git.bds.nyc.user.model.dto.UserWxInfoDTO;
import com.git.bds.nyc.user.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 成大事
 * @since 2022/12/30 13:30
 */
@Api(tags = "农户个人信息的接口")
@Slf4j
@Validated
@RestController
@RequestMapping("/farmer/info")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FarmerInfoController {

    private final UserService userService;


    private final ShoppingAddressService shoppingAddressService;



    /**
     * 修改用户wx信息
     *
     * @param userWxInfoDTO user wx info dto
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation(value = "更新用户头像和昵称")
    @PostMapping("/modifyUserWxInfo")
    public R<Boolean> modifyUserWxInfo(@Validated @RequestBody UserWxInfoDTO userWxInfoDTO){
        return R.decide(userService.update(new UpdateWrapper<User>()
                .set(User.AVATAR,userWxInfoDTO.getAvatarUrl())
                .set(User.USER_SCREEN_NAME,userWxInfoDTO.getNickName())
                .eq(User.USER_ID,StpUtil.getLoginIdAsLong())));
    }

}
