package com.git.bds.nyc.user.controller;

import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import com.git.bds.nyc.product.service.primary.farmer.FarmerPrimaryProductService;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.user.valid.ValidGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
    public R<Object> releaseOnSellProduct(
           @Validated({ValidGroup.OnSell.class}) @RequestBody PrimaryProductDTO productDTO
    ){
        productService.releaseOnSellProduct(productDTO);
        return R.ok();
    }



    @PostMapping("/releasePreSellProduct")
    public R<Object> releasePreSellProduct(
            @Validated({ValidGroup.PreSale.class}) @RequestBody PrimaryProductDTO productDTO
    ){
        productService.releasePreSellProduct(productDTO);
        return R.ok();
    }

    @PostMapping("/delProduct")
    @ApiImplicitParam(name = "id", value = "产品id", required = true, example = "1", dataTypeClass = Long.class)
    public R<Boolean> delProduct(
            @NotNull @RequestParam("id") Long id
    ){
        productService.delProductById(id);
        return R.ok();
    }



}
