package com.git.bds.nyc.applet.api.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.git.bds.nyc.communal.model.vo.AddressInfoVO;
import com.git.bds.nyc.communal.model.vo.ShoppingAddressVO;
import com.git.bds.nyc.applet.api.service.address.AddressService;
import com.git.bds.nyc.communal.convert.AddressConvert;
import com.git.bds.nyc.communal.model.domain.ShoppingAddress;
import com.git.bds.nyc.communal.model.dto.AddressAddDTO;
import com.git.bds.nyc.communal.model.dto.AddressModifyDTO;
import com.git.bds.nyc.communal.model.dto.ShoppingAddressDTO;
import com.git.bds.nyc.communal.service.address.ShoppingAddressService;
import com.git.bds.nyc.enums.DefaultType;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 成大事
 * @since 2023/2/13 16:28
 */
@Api(tags = "地址管理接口")
@Slf4j
@Validated
@RestController
@RequestMapping("/applet/address")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddressController {

    private final ShoppingAddressService shoppingAddressService;

    private final AddressService addressService;

    /**
     * 获取自助购物地址
     *
     * @return {@link R}<{@link List}<{@link ShoppingAddressVO}>>
     */
    @ApiOperation(value = "我的 获取收货地址")
    @PostMapping("/getSelfShoppingAddress")
    public R<List<ShoppingAddressVO>> getSelfShoppingAddress(){
        List<ShoppingAddressDTO> addressDTOList = addressService.getSelfShoppingAddress();
        return R.ok(AddressConvert.INSTANCE.toShoppingVOList(addressDTOList));
    }

    /**
     * 添加自购地址
     *
     * @param addressAddDTO 地址添加数据
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation(value = "添加收货地址")
    @PostMapping("/addSelfShoppingAddress")
    public R<Boolean> addSelfShoppingAddress(
            @Validated @RequestBody AddressAddDTO addressAddDTO
    ){
        ShoppingAddress shoppingAddress = AddressConvert.INSTANCE.toShoppingAddress(addressAddDTO, StpUtil.getLoginIdAsLong());
        return R.decide(shoppingAddressService.save(shoppingAddress));
    }

    /**
     * 修改自购地址
     *
     * @param addressModifyDTO 地址修改dto
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation(value = "修改 收货地址信息")
    @PostMapping("/modifySelfShoppingAddress")
    public R<Boolean> modifySelfShoppingAddress(
            @Validated @RequestBody AddressModifyDTO addressModifyDTO
    ){
        ShoppingAddress shoppingAddress = AddressConvert.INSTANCE.toShoppingAddress(addressModifyDTO,StpUtil.getLoginIdAsLong());
        return R.decide(shoppingAddressService.updateById(shoppingAddress));
    }

    /**
     * 修改默认自助购物地址
     *
     * @param id 身份证件
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation(value = "修改 默认收货地址")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/modifyDefaultSelfShoppingAddress")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收货地址id", dataTypeClass = Long.class, paramType = "query", example = "1", required = true)
    })
    public R<Boolean> modifyDefaultSelfShoppingAddress(
            @RequestParam("id") @NotNull Long id
    ){
        long userId = StpUtil.getLoginIdAsLong();
        ShoppingAddress one = shoppingAddressService.getOne(new LambdaQueryWrapper<ShoppingAddress>()
                .eq(ShoppingAddress::getId, id)
                .eq(ShoppingAddress::getUserId, userId));
        if(one == null){
            throw new BusinessException(ResultCode.NOT_EXIST.getCode(),ResultCode.NOT_EXIST.getMessage());
        }
        //将原来默认的置于非默认
        shoppingAddressService.update(new LambdaUpdateWrapper<ShoppingAddress>()
                .set(ShoppingAddress::getIsDefault, DefaultType.NOT_DEFAULT.getValue())
                .eq(ShoppingAddress::getUserId, userId)
                .eq(ShoppingAddress::getIsDefault,DefaultType.IS_DEFAULT.getValue()));

        //将新选择的置为默认
        boolean update = shoppingAddressService.update(new LambdaUpdateWrapper<ShoppingAddress>()
                .set(ShoppingAddress::getIsDefault, DefaultType.IS_DEFAULT.getValue())
                .eq(ShoppingAddress::getUserId, userId)
                .eq(ShoppingAddress::getId, id));
        return R.decide(update);
    }


    /**
     * 根据id查看地址信息
     *
     * @param id 地址id
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation(value = "删除 收货地址")
    @PostMapping("/getAddressInfoById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收货地址id", dataTypeClass = Long.class, paramType = "query", example = "1", required = true)
    })
    public R<AddressInfoVO> getAddressInfoById(@RequestParam("id") @NotNull Long id){
        ShoppingAddress address = shoppingAddressService.getAddressInfoById(id);
        AddressInfoVO addressInfoVO = AddressConvert.INSTANCE.toAddressInfoVO(address);
        return R.ok(addressInfoVO);
    }

    /**
     * del自购地址
     *
     * @param id 地址id
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation(value = "根据地址id删除 收货地址")
    @PostMapping("/delAddressById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收货地址id", dataTypeClass = Long.class, paramType = "query", example = "1", required = true)
    })
    public R<Boolean> delAddressById(@RequestParam("id") @NotNull Long id){
        return R.decide(shoppingAddressService.remove(new LambdaQueryWrapper<ShoppingAddress>()
                .eq(ShoppingAddress::getId,id)
                .eq(ShoppingAddress::getUserId,StpUtil.getLoginIdAsLong())));
    }
}
