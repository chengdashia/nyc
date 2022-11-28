package com.git.bds.nyc.user.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.git.bds.nyc.common.convert.AddressConvert;
import com.git.bds.nyc.common.model.domain.ShoppingAddress;
import com.git.bds.nyc.common.model.dto.AddressAddDTO;
import com.git.bds.nyc.common.model.dto.AddressModifyDTO;
import com.git.bds.nyc.common.model.dto.ShoppingAddressDTO;
import com.git.bds.nyc.common.service.address.ShoppingAddressService;
import com.git.bds.nyc.enums.DefaultType;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.user.convert.UserConvert;
import com.git.bds.nyc.user.domain.User;
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

    private final ShoppingAddressService shoppingAddressService;

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

    @ApiOperation(value = "我的 获取收货地址")
    @PostMapping("/getSelfShoppingAddress")
    public R<List<ShoppingAddressVO>> getSelfShoppingAddress(){
        List<ShoppingAddressDTO> addressDTOList = userService.getSelfShoppingAddress();
        return R.ok(UserConvert.INSTANCE.toShoppingVOList(addressDTOList));
    }

    @ApiOperation(value = "添加收货地址")
    @PostMapping("/addSelfShoppingAddress")
    public R<Boolean> addSelfShoppingAddress(
            @Validated @RequestBody AddressAddDTO addressAddDTO
    ){
        ShoppingAddress shoppingAddress = AddressConvert.INSTANCE.toShoppingAddress(addressAddDTO,StpUtil.getLoginIdAsLong());
        return R.decide(shoppingAddressService.save(shoppingAddress));
    }

    @ApiOperation(value = "修改 收货地址信息")
    @PostMapping("/modifySelfShoppingAddress")
    public R<Boolean> modifySelfShoppingAddress(
            @Validated @RequestBody AddressModifyDTO addressModifyDTO
    ){
        ShoppingAddress shoppingAddress = AddressConvert.INSTANCE.toShoppingAddress(addressModifyDTO,StpUtil.getLoginIdAsLong());
        return R.decide(shoppingAddressService.updateById(shoppingAddress));
    }

    @ApiOperation(value = "修改 默认收货地址")
    @PostMapping("/modifyDefaultSelfShoppingAddress")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收货地址id", dataTypeClass = Long.class, paramType = "query", example = "1", required = true)
    })
    public R<Boolean> modifyDefaultSelfShoppingAddress(
            @RequestParam("id") @NotNull Long id
    ){
        long userId = StpUtil.getLoginIdAsLong();
        //将原来默认的置于非默认
        shoppingAddressService.update(new UpdateWrapper<ShoppingAddress>()
               .set(ShoppingAddress.isDEFAULT, DefaultType.NOT_DEFAULT)
               .eq(ShoppingAddress.USER_ID, userId)
                .eq(ShoppingAddress.isDEFAULT,DefaultType.IS_DEFAULT.getValue()));

        //将新选择的置为默认
        boolean update = shoppingAddressService.update(new UpdateWrapper<ShoppingAddress>()
                .set(ShoppingAddress.isDEFAULT, DefaultType.IS_DEFAULT)
                .eq(ShoppingAddress.USER_ID, userId)
                .eq(ShoppingAddress.ID, id));
        return R.decide(update);
    }


    @ApiOperation(value = "删除 收货地址")
    @PostMapping("/delSelfShoppingAddress")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收货地址id", dataTypeClass = Long.class, paramType = "query", example = "1", required = true)
    })
    public R<Boolean> delSelfShoppingAddress(@RequestParam("id") @NotNull Long id){
        return R.decide(shoppingAddressService.removeById(id));
    }












}
