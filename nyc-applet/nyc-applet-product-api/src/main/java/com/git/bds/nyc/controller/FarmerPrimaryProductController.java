package com.git.bds.nyc.controller;

import com.git.bds.nyc.controller.vo.PrimaryProductInfoVO;
import com.git.bds.nyc.convert.product.ProductCovert;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.dto.ProductInfoDTO;
import com.git.bds.nyc.product.service.primary.farmer.FarmerPrimaryProductService;
import com.git.bds.nyc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 成大事
 * @since 2022-09-02 22:02:03
 */
@Api(tags = "初始产品")
@Slf4j
@Validated
@RestController
@RequestMapping("/primaryProduct")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FarmerPrimaryProductController {

    private final FarmerPrimaryProductService productService;


    @ApiOperation("首页商品数据")
    @GetMapping("/indexProduct")
    public R<Object> homePageProductsByPage(
            @Valid PageParam pageParam
    ){
        List<FarmerPrimaryProduct> productList = productService.homePageProductsByPage(pageParam);
        return R.ok(ProductCovert.INSTANCE.toPrimaryProductVO(productList));
    }

    @ApiOperation("商品的详细数据集")
    @PostMapping("/getProductInfo/{id}")
    @ApiImplicitParam(name = "id", value = "商品id", required = true, dataTypeClass = Long.class)
    public R<PrimaryProductInfoVO> getProductInfo(
            @PathVariable("id") Long id
    ){
        ProductInfoDTO product = productService.getProductInfo(id);
        return R.ok(ProductCovert.INSTANCE.toPrimaryProductInfoVo(product));
    }


}
