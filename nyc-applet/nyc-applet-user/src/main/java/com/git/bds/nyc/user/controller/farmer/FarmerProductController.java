package com.git.bds.nyc.user.controller.farmer;

import com.git.bds.nyc.product.model.dto.ProductDTO;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.user.service.farmer.FarmerService;
import com.git.bds.nyc.user.valid.ValidGroup;
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
 * @since 2022/9/14 18:46
 */
@Api(tags = "农户 农产品")
@Slf4j
@Validated
@RestController
@RequestMapping("/farmer/product")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FarmerProductController {

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
           @Validated({ValidGroup.OnSell.class}) @RequestBody ProductDTO productDTO
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
            @Validated({ValidGroup.PreSale.class}) @RequestBody ProductDTO productDTO
    ){
        return R.decide(farmerService.releasePreSellProduct(productDTO));
    }



}
