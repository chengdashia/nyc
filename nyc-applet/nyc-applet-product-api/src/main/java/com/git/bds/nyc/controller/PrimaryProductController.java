package com.git.bds.nyc.controller;

import cn.hutool.core.util.IdUtil;
import com.git.bds.nyc.controller.vo.PrimaryProductInfoVO;
import com.git.bds.nyc.convert.product.ProductCovert;
import com.git.bds.nyc.framework.redis.util.RedisUtils;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.product.model.domain.PrimaryProduct;
import com.git.bds.nyc.product.service.PrimaryProductService;
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
public class PrimaryProductController {

    private final PrimaryProductService productService;

    private final RedisUtils redisUtils;

    @ApiOperation("首页商品数据")
    @GetMapping("/indexProduct")
    public R<Object> homePageProductsByPage(
            @Valid PageParam pageParam
    ){
        List<PrimaryProduct> productList = productService.homePageProductsByPage(pageParam);
        return R.ok(ProductCovert.INSTANCE.toPrimaryProductVO(productList));
    }

    @ApiOperation("商品的详细数据集")
    @PostMapping("/getProductInfo/{id}")
    @ApiImplicitParam(name = "id", value = "商品id", required = true, dataTypeClass = Long.class)
    public R<PrimaryProductInfoVO> getProductInfo(
            @PathVariable("id") Long id
    ){
        PrimaryProduct product = productService.getProductInfo(id);
        return R.ok(ProductCovert.INSTANCE.toPrimaryProductInfoVo(product));
    }

    @ApiOperation("测试redis 填充")
    @PostMapping("/redisInsert/{id}")
    @ApiImplicitParam(name = "id", value = "商品id", required = true, dataTypeClass = Long.class)
    public R<String> redisInsert(
            @PathVariable("id") Long id
    ){
        redisUtils.set(id.toString(),new PrimaryProduct().setId(IdUtil.getSnowflakeNextId()));
        return R.ok();
    }

    @ApiOperation("测试redis 获取")
    @PostMapping("/redisGet/{id}")
    @ApiImplicitParam(name = "id", value = "商品id", required = true, dataTypeClass = Long.class)
    public R<Object> redisGet(
            @PathVariable("id") Long id
    ){

        return R.ok(redisUtils.get(id.toString()));
    }


}
