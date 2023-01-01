package com.git.bds.nyc.user.controller.farmer;

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
import com.git.bds.nyc.user.domain.vo.ShoppingAddressVO;
import com.git.bds.nyc.user.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 成大事
 * @since 2022/12/30 13:30
 */
@Api(tags = "农户个人信息的接口")
@Slf4j
@Validated
@RestController
@RequestMapping("/wx/farmer")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FarmerInfoController {

    private final UserService userService;

    private final ShoppingAddressService shoppingAddressService;

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
        ShoppingAddress shoppingAddress = AddressConvert.INSTANCE.toShoppingAddress(addressAddDTO, StpUtil.getLoginIdAsLong());
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


    @ApiOperation(value = "更新用户头像和昵称")
    @PostMapping("/modifyUserWxInfo")
    public R<Boolean> modifyUserWxInfo(@Validated @RequestBody UserWxInfoDTO userWxInfoDTO){
        return R.decide(userService.update(new UpdateWrapper<User>()
                .set(User.AVATAR,userWxInfoDTO.getAvatarUrl())
                .set(User.USER_SCREEN_NAME,userWxInfoDTO.getNickName())
                .eq(User.USER_ID,StpUtil.getLoginIdAsLong())));
    }

}
