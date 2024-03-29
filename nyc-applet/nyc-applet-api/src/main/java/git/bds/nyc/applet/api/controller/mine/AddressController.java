package git.bds.nyc.applet.api.controller.mine;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import git.bds.nyc.applet.api.convert.ApiAddressConvert;
import git.bds.nyc.applet.api.model.vo.address.AddressInfoVO;
import git.bds.nyc.applet.api.model.vo.address.AddressVO;
import git.bds.nyc.communal.convert.AddressConvert;
import git.bds.nyc.communal.model.domain.Address;
import git.bds.nyc.communal.model.dto.AddressAddDTO;
import git.bds.nyc.communal.model.dto.AddressDTO;
import git.bds.nyc.communal.model.dto.AddressModifyDTO;
import git.bds.nyc.communal.service.address.AddressService;
import git.bds.nyc.result.R;
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
@RequestMapping("/applet/mine/address")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddressController {

    private final AddressService addressService;



    /**
     * 获取我的收货地址
     *
     * @return {@link R}<{@link List}<{@link AddressVO}>>
     */
    @ApiOperation(value = "获取我的收货地址")
    @PostMapping("/getMyAddress")
    public R<List<AddressVO>> getMyAddress(){
        List<AddressDTO> addressDTOList = addressService.getMyAddress();
        return R.ok(ApiAddressConvert.INSTANCE.toVOList(addressDTOList));
    }

    /**
     * 添加收货地址
     *
     * @param addressAddDTO 添加数据DTO
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation(value = "添加收货地址")
    @PostMapping("/addAddress")
    public R<Boolean> addAddress(
            @Validated @RequestBody AddressAddDTO addressAddDTO
    ){
        return R.decide(addressService.addAddress(addressAddDTO));
    }

    /**
     * 修改自购地址
     *
     * @param addressModifyDTO 地址修改dto
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation(value = "修改 收货地址信息")
    @PostMapping("/modifyAddress")
    public R<Boolean> modifyAddress(
            @Validated @RequestBody AddressModifyDTO addressModifyDTO
    ){
        Address shoppingAddress = AddressConvert.INSTANCE.toAddress(addressModifyDTO,StpUtil.getLoginIdAsLong());
        return R.decide(addressService.updateById(shoppingAddress));
    }

    /**
     * 修改默认自助购物地址
     *
     * @param id 身份证件
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation(value = "修改默认自助购物地址")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/modifyDefaultAddress")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收货地址id", dataTypeClass = Long.class, paramType = "query", example = "1", required = true)
    })
    public R<Boolean> modifyDefaultAddress(
            @RequestParam("id") @NotNull Long id
    ){
        return R.decide(addressService.modifyDefaultAddress(id));
    }


    /**
     * 根据地址id查看地址信息
     *
     * @param id 地址id
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation(value = "根据地址id 查看 地址信息")
    @PostMapping("/getAddressInfoById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收货地址id", dataTypeClass = Long.class, paramType = "query", example = "1", required = true)
    })
    public R<AddressInfoVO> getAddressInfoById(@RequestParam("id") @NotNull Long id){
        Address address = addressService.getAddressInfoById(id);
        AddressInfoVO addressInfoVO = ApiAddressConvert.INSTANCE.toAddressInfoVO(address);
        return R.ok(addressInfoVO);
    }

    /**
     * 根据地址id 删除 收货地址
     *
     * @param id 地址id
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation(value = "根据地址id 删除 收货地址")
    @PostMapping("/delAddressById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收货地址id", dataTypeClass = Long.class, paramType = "query", example = "1", required = true)
    })
    public R<Boolean> delAddressById(@RequestParam("id") @NotNull Long id){
        return R.decide(addressService.remove(new LambdaQueryWrapper<Address>()
                .eq(Address::getId,id)
                .eq(Address::getUserId,StpUtil.getLoginIdAsLong())));
    }
}
