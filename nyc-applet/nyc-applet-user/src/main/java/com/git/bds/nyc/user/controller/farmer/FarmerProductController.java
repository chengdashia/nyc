package com.git.bds.nyc.user.controller.farmer;

import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import com.git.bds.nyc.product.model.dto.PrimaryProductModifyDTO;
import com.git.bds.nyc.product.model.dto.PrimaryProductSelfDTO;
import com.git.bds.nyc.product.service.primary.farmer.FarmerPrimaryProductService;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.user.convert.FarmerProductConvert;
import com.git.bds.nyc.user.model.vo.FarmerSelfPrimaryProductVO;
import com.git.bds.nyc.user.service.farmer.FarmerService;
import com.git.bds.nyc.user.valid.ValidGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
 * @since 2022/9/14 18:46
 */
@Api(tags = "农户 农产品")
@Slf4j
@Validated
@RestController
@RequestMapping("/farmer/product")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FarmerProductController {

    private final FarmerPrimaryProductService productService;

    private final FarmerService farmerService;


    /**
     * 农户发布 在售初级产品
     *
     * @param productDTO 产品dto
     * @return {@link R}<{@link Object}>
     */
    @PostMapping("/releaseOnSellProduct")
    @ApiOperation("发布初级在售农产品")
    public R<Boolean> releaseOnSellProduct(
           @Validated({ValidGroup.OnSell.class}) @RequestBody PrimaryProductDTO productDTO
    ){
        return R.decide(farmerService.releaseOnSellProduct(productDTO));
    }

    /**
     * 发布预售产品
     *
     * @param productDTO 产品dto
     * @return {@link R}<{@link Boolean}>
     */
    @PostMapping("/releasePreSellProduct")
    @ApiOperation("发布初级预售农产品")
    public R<Boolean> releasePreSellProduct(
            @Validated({ValidGroup.PreSale.class}) @RequestBody PrimaryProductDTO productDTO
    ){
        return R.decide(farmerService.releasePreSellProduct(productDTO));
    }

    /**
     * 修改产品信息
     *
     * @param productDTO 产品dto
     * @return {@link R}<{@link Boolean}>
     */
    @PostMapping("/modifyProductInfo")
    @ApiOperation("修改农产品信息")
    public R<Boolean> modifyProductInfo(
            @Validated({com.git.bds.nyc.product.valid.ValidGroup.All.class}) @RequestBody PrimaryProductModifyDTO productDTO
    ){
        return R.decide(productService.modifyProductInfo(productDTO));
    }

    /**
     * del乘积
     *
     * @param id 身份证件
     * @return {@link R}<{@link Boolean}>
     */
    @PostMapping("/delProduct")
    @ApiOperation("删除农产品")
    @ApiImplicitParam(name = "id", value = "产品id", required = true, example = "1", dataTypeClass = Long.class)
    public R<Boolean> delProduct(
            @NotNull @RequestParam("id") Long id
    ){
        return R.decide(productService.delProductById(id));
    }

    /**
     * 逐页销售产品
     *
     * @param pageParam 页面参数
     * @return {@link R}<{@link PageResult}<{@link FarmerSelfPrimaryProductVO}>>
     */
    @PostMapping("/getOnSellProductByPage")
    @ApiOperation("农户获取发布的在售的初级产品 分页")
    public R<PageResult<FarmerSelfPrimaryProductVO>> getOnSellProductByPage(
            @Validated PageParam pageParam
    ){
        PageResult<PrimaryProductSelfDTO> onSellProductByPage = productService.getOnSellProductByPage(pageParam);
        List<FarmerSelfPrimaryProductVO> selfPrimaryProductVOList = FarmerProductConvert.INSTANCE.toFarmerSelfPrimaryProductVO(onSellProductByPage.getList());
        return R.ok(new PageResult<>(selfPrimaryProductVOList,onSellProductByPage.getTotal()));
    }

    /**
     * 按页面获取预售产品
     *
     * @param pageParam 页面参数
     * @return {@link R}<{@link PageResult}<{@link FarmerSelfPrimaryProductVO}>>
     */
    @PostMapping("/getPreSellProductByPage")
    @ApiOperation("个人获取发布的预售的初级产品 分页")
    public R<PageResult<FarmerSelfPrimaryProductVO>> getPreSellProductByPage(
            @Validated PageParam pageParam
    ){
        PageResult<PrimaryProductSelfDTO> onSellProductByPage = productService.getPreSellProductByPage(pageParam);
        List<FarmerSelfPrimaryProductVO> selfPrimaryProductVOList = FarmerProductConvert.INSTANCE.toFarmerSelfPrimaryProductVO(onSellProductByPage.getList());
        return R.ok(new PageResult<>(selfPrimaryProductVOList,onSellProductByPage.getTotal()));
    }



}
