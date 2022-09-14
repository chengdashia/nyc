package com.git.bds.nyc.controller;

import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import com.git.bds.nyc.product.service.primary.farmer.FarmerPrimaryProductService;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.valid.ValidGroup;
import io.swagger.annotations.Api;
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
@Api(tags = "农户 初级农产品")
@Slf4j
@Validated
@RestController
@RequestMapping("/primaryProduct")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PrimaryProductController {

    private final FarmerPrimaryProductService productService;


    @PostMapping("/addProduct")
    public R<Object> addProduct(
           @Validated({ValidGroup.PreSale.class}) @RequestBody PrimaryProductDTO productDTO
    ){
        log.info("productDTO:  "+productDTO);
        productService.releaseProduct(productDTO);
        return R.ok();
    }



}
