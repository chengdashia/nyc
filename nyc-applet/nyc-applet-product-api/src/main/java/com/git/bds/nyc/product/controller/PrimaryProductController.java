package com.git.bds.nyc.product.controller;

import cn.easyes.core.biz.PageInfo;
import cn.easyes.core.conditions.LambdaEsQueryWrapper;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.controller.vo.PrimaryProductInfoVO;
import com.git.bds.nyc.product.controller.vo.PrimaryProductVO;
import com.git.bds.nyc.product.convert.product.ProductCovert;
import com.git.bds.nyc.product.mapper.es.ProductEsMapper;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.dto.ProductInfoDTO;
import com.git.bds.nyc.product.model.es.ProductEs;
import com.git.bds.nyc.product.service.primary.farmer.FarmerPrimaryProductService;
import com.git.bds.nyc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
public class PrimaryProductController {

    private final FarmerPrimaryProductService productService;
    
    private ProductEsMapper productEsMapper;


    @ApiOperation("首页商品数据")
    @GetMapping("/indexProduct")
    public R<List<PrimaryProductVO>> homePageProductsByPage(
            @Valid PageParam pageParam
    ){
        List<FarmerPrimaryProduct> productList = productService.homePageProductsByPage(pageParam);
        return R.ok(ProductCovert.INSTANCE.toPrimaryProductVO(productList));
    }

    @ApiOperation("首页商品数据 通过Es获取")
    @GetMapping("/indexProductByEs")
    public R<PageResult<ProductEs>> homePageProductsByPageByEs(
            @Valid PageParam pageParam
    ){
        LambdaEsQueryWrapper<ProductEs> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.matchAllQuery();
        wrapper.orderByDesc(ProductEs::getCreateTime);
        PageInfo<ProductEs> productEsPageInfo = productEsMapper.pageQuery(wrapper, pageParam.getPageNo().intValue(), pageParam.getPageSize().intValue());
        return R.ok(new PageResult<>(productEsPageInfo.getList(),(long) productEsPageInfo.getPageSize()));
    }


    @ApiOperation("商品的详细数据集")
    @PostMapping("/getProductInfo/{id}/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品id", dataTypeClass = Long.class, paramType = "path", example = "112345646545", required = true),
            @ApiImplicitParam(name = "type", value = "类型", dataTypeClass = Integer.class, paramType = "path", example = "0", required = true)
    })
    public R<PrimaryProductInfoVO> getProductInfo(
            @PathVariable("id") Long id,
            @PathVariable("type") int type
            ){
        ProductInfoDTO product = productService.getProductInfo(id,type);
        return R.ok(ProductCovert.INSTANCE.toPrimaryProductInfoVO(product));
    }


}
