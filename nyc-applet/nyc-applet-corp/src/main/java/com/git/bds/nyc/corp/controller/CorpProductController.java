package com.git.bds.nyc.corp.controller;

import com.git.bds.nyc.corp.service.CorpService;
import com.git.bds.nyc.product.model.dto.ProductModifyDTO;
import com.git.bds.nyc.product.service.processing.CorpProcessingProductService;
import com.git.bds.nyc.product.valid.ValidGroup;
import com.git.bds.nyc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


/**
 * <p>
 * 初级农产品表 前端控制器
 * </p>
 *
 * @author chnnc
 * @since 2022-10-15 18:50:50
 */
@Api(tags = "公司 关于农产品的接口")
@Validated
@RestController
@RequestMapping("/corpProduct")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CorpProductController {

    private final CorpProcessingProductService corpProcessingProductService;

    private final CorpService corpService;


    /**
     * 修改公司发布的加工农产品详细信息
     *
     * @param productDTO 产品dto
     * @return {@link R}<{@link Boolean}>
     */
    @PostMapping("/modifyProcessingProductInfo")
    @ApiOperation("修改公司发布的初级农产品详细信息")
    public R<Boolean> modifyProcessingProductInfo(
            @Validated({ValidGroup.All.class}) @RequestBody ProductModifyDTO productDTO
    ){
        return R.decide(corpProcessingProductService.modifyProductInfo(productDTO));
    }
    /**
     * 删除 农产品
     *
     * @param id 农产品id
     * @return {@link = R<Boolean>}
     */
    @DeleteMapping("/delete/{type}/{id}")
    @ApiOperation("根据id删除农产品数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id主键", dataTypeClass = Long.class, paramType = "path", example = "123456", required = true),
            @ApiImplicitParam(name = "type", value = "id主键", dataTypeClass = Integer.class, paramType = "path", example = "1", required = true)
    })
    public R<Boolean> delete(
            @PathVariable(value = "id") Long id,
            @PathVariable(value = "type") @Min(1) @Max(2) int type
    ) {
        return R.decide(corpService.deleteProductById(id,type));
    }



}
