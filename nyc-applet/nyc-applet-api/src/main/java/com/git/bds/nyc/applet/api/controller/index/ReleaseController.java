package com.git.bds.nyc.applet.api.controller.index;

import com.git.bds.nyc.applet.api.service.demand.DemandService;
import com.git.bds.nyc.applet.api.service.release.ReleaseService;
import com.git.bds.nyc.demand.model.dto.DemandAddDTO;
import com.git.bds.nyc.product.model.dto.ProductDTO;
import com.git.bds.nyc.product.valid.ValidGroup;
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

/**
 * @author 成大事
 * @since 2023/2/15 19:00
 */
@Api(tags = "首页发布管理")
@Slf4j
@Validated
@RestController
@RequestMapping("/applet/index/release")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReleaseController {

    private final ReleaseService releaseService;

    private final DemandService demandService;


    /**
     * 发布在售初级产品
     *
     * @param productDTO 产品dto
     * @return {@link R}<{@link Object}>
     */
    @ApiOperation("发布初级在售农产品")
    @PostMapping("/releaseOnSellProduct")
    public R<Boolean> releaseOnSellProduct(
            @Validated({ValidGroup.OnSell.class}) @RequestBody ProductDTO productDTO
    ){
        return R.decide(releaseService.releaseProduct(productDTO));
    }

    /**
     * 发布初级预售产品
     *
     * @param productDTO 产品dto
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation("发布初级预售农产品")
    @PostMapping("/releasePreSellProduct")
    public R<Boolean> releasePreSellProduct(
            @Validated({ValidGroup.PreSale.class}) @RequestBody ProductDTO productDTO
    ){
        return R.decide(releaseService.releaseProduct(productDTO));
    }


    /**
     * 发布需求
     *
     * @param demandAddDTO 按需添加数据
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation("发布需求")
    @PostMapping("/releaseDemand")
    public R<Boolean> releaseDemand(
            @Validated @RequestBody DemandAddDTO demandAddDTO
    ){
        return R.decide(demandService.releaseDemand(demandAddDTO));
    }


    /**
     * 根据需求id删除需求
     *
     * @param id 需求id
     * @return {@link = R<Boolean>}
     */
    @ApiOperation("根据id删除需求数据")
    @DeleteMapping("/delDemandById/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需求id", dataTypeClass = Long.class, paramType = "path", example = "123456", required = true)
    })
    public R<Boolean> delDemandById(@PathVariable(value = "id") Long id) {
        return R.decide(demandService.delDemandById(id));
    }


}
