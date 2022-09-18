package com.git.bds.nyc.user.controller;

import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import com.git.bds.nyc.product.service.primary.farmer.FarmerPrimaryProductService;
import com.git.bds.nyc.result.R;
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

/**
 * @author 成大事
 * @since 2022/9/14 18:46
 */
@Api(tags = "农户 初级农产品")
@Slf4j
@Validated
@RestController
@RequestMapping("/farmerPrimaryProduct")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PrimaryProductController {

    private final FarmerPrimaryProductService productService;


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
        return R.decide(productService.releaseOnSellProduct(productDTO));
    }

    @PostMapping("/releasePreSellProduct")
    @ApiOperation("发布初级预售农产品")
    public R<Boolean> releasePreSellProduct(
            @Validated({ValidGroup.PreSale.class}) @RequestBody PrimaryProductDTO productDTO
    ){
        return R.decide(productService.releasePreSellProduct(productDTO));
    }

    @PostMapping("/delProduct")
    @ApiOperation("删除农产品")
    @ApiImplicitParam(name = "id", value = "产品id", required = true, example = "1", dataTypeClass = Long.class)
    public R<Boolean> delProduct(
            @NotNull @RequestParam("id") Long id
    ){
        return R.decide(productService.delProductById(id));
    }



}
