package com.git.bds.nyc.corp.controller;

import com.git.bds.nyc.corp.CorpProductConvert;
import com.git.bds.nyc.corp.model.vo.CorpSelfPrimaryProductVO;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import com.git.bds.nyc.product.model.dto.PrimaryProductModifyDTO;
import com.git.bds.nyc.product.model.dto.PrimaryProductSelfDTO;
import com.git.bds.nyc.product.service.primary.corp.CorpPrimaryProductService;
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

import java.util.List;


/**
 * <p>
 * 初级农产品表 前端控制器
 * </p>
 *
 * @author chnnc
 * @since 2022-10-15 18:50:50
 */
@Api(tags = "公司初级农产品")
@Validated
@RestController
@RequestMapping("/corpPrimaryProduct")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CorpPrimaryProductController {

    private final CorpPrimaryProductService corpPrimaryProductService;

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
        return R.decide(corpPrimaryProductService.releaseOnSellProduct(productDTO));
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
        return R.decide(corpPrimaryProductService.releasePreSellProduct(productDTO));
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
            @Validated({ValidGroup.All.class}) @RequestBody PrimaryProductModifyDTO productDTO
    ){
        return R.decide(corpPrimaryProductService.modifyProductInfo(productDTO));
    }


    /**
     * 删除初级农产品
     *
     * @param id 初级农产品id
     * @return {@link = R<Boolean>}
     */
    @PutMapping("/delete/{id}")
    @ApiOperation("根据id删除初级农产品数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id主键", dataTypeClass = Long.class, paramType = "path", example = "123456", required = true)
    })
    public R<Boolean> delete(@PathVariable(value = "id") Long id) {
        return R.decide(corpPrimaryProductService.removeById(id));
    }

    @PostMapping("/getOnSellProductByPage")
    @ApiOperation("公司获取发布的在售的初级产品 分页")
    public R<PageResult<CorpSelfPrimaryProductVO>> getOnSellProductByPage(
            @Validated PageParam pageParam
    ){
        PageResult<PrimaryProductSelfDTO> onSellProductByPage = corpPrimaryProductService.getOnSellProductByPage(pageParam);
        List<CorpSelfPrimaryProductVO> selfPrimaryProductVOList = CorpProductConvert.INSTANCE.toCorpSelfPrimaryProductVO(onSellProductByPage.getList());
        return R.ok(new PageResult<>(selfPrimaryProductVOList,onSellProductByPage.getTotal()));
    }

    @PostMapping("/getPreSellProductByPage")
    @ApiOperation("公司获取发布的预售的初级产品 分页")
    public R<PageResult<CorpSelfPrimaryProductVO>> getPreSellProductByPage(
            @Validated PageParam pageParam
    ){
        PageResult<PrimaryProductSelfDTO> onSellProductByPage = corpPrimaryProductService.getPreSellProductByPage(pageParam);
        List<CorpSelfPrimaryProductVO> selfPrimaryProductVOList = CorpProductConvert.INSTANCE.toCorpSelfPrimaryProductVO(onSellProductByPage.getList());
        return R.ok(new PageResult<>(selfPrimaryProductVOList,onSellProductByPage.getTotal()));
    }


}
