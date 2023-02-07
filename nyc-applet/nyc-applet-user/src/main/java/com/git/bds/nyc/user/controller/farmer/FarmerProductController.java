package com.git.bds.nyc.user.controller.farmer;

import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import com.git.bds.nyc.product.model.dto.PrimaryProductModifyDTO;
import com.git.bds.nyc.product.model.dto.PrimaryProductSelfDTO;
import com.git.bds.nyc.product.model.dto.ProductAuditDTO;
import com.git.bds.nyc.product.service.primary.farmer.FarmerPrimaryProductService;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.user.convert.FarmerProductConvert;
import com.git.bds.nyc.user.model.vo.FarmerAuditPrimaryProductVO;
import com.git.bds.nyc.user.model.vo.FarmerReleasePrimaryProductVO;
import com.git.bds.nyc.user.service.farmer.FarmerService;
import com.git.bds.nyc.user.valid.ValidGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    private final FarmerPrimaryProductService productService;

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
           @Validated({ValidGroup.OnSell.class}) @RequestBody PrimaryProductDTO productDTO
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
            @Validated({ValidGroup.PreSale.class}) @RequestBody PrimaryProductDTO productDTO
    ){
        return R.decide(farmerService.releasePreSellProduct(productDTO));
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
            @Validated({com.git.bds.nyc.product.valid.ValidGroup.All.class}) @RequestBody PrimaryProductModifyDTO productDTO
    ){
        return R.decide(productService.modifyProductInfo(productDTO));
    }

    /**
     * del乘积
     *
     * @param id 身份证件
     * @return {@link R}<{@link Boolean}>
     */
    @PostMapping("/delProduct")
    @ApiOperation("删除农产品")
    @ApiImplicitParam(name = "id", value = "产品id", required = true, example = "1", dataTypeClass = Long.class)
    public R<Boolean> delProduct(
            @NotNull @RequestParam("id") Long id
    ){
        return R.decide(productService.delProductById(id));
    }


    /**
     * 农户获取发布的初级产品（包括在售、预售和审核中） 分页
     *
     * @param pageParam 页面参数
     * @param type      类型
     * @return {@link R}<{@link PageResult}<{@link FarmerReleasePrimaryProductVO}>>
     */
    @PostMapping("/getReleaseProductByPage/{type}")
    @ApiOperation("农户获取发布的初级产品（包括在售、预售） 分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型(0:在售,1:预售)", dataTypeClass = Integer.class, paramType = "path", example = "1", required = true)
    })
    public R<PageResult<FarmerReleasePrimaryProductVO>> getReleaseProductByPage(
            @Validated PageParam pageParam,
            @PathVariable @Min(0) @Max(1) int type
    ){
        PageResult<PrimaryProductSelfDTO> page = productService.getReleaseProductByPage(pageParam,type);
        List<FarmerReleasePrimaryProductVO> farmerReleasePrimaryProductVOList = FarmerProductConvert.INSTANCE.toFarmerSelfPrimaryProductVO(page.getList());
        return R.ok(new PageResult<>(farmerReleasePrimaryProductVOList,page.getTotal()));
    }

    /**
     * 农户获取发布的初级产品审核中的产品 分页
     *
     * @param pageParam 页面参数
     * @return {@link R}<{@link PageResult}<{@link FarmerReleasePrimaryProductVO}>>
     */
    @PostMapping("/getUnauditedProductByPage/{type}")
    @ApiOperation("农户获取发布的初级产品审核中的产品 分页")
    public R<PageResult<FarmerAuditPrimaryProductVO>> getUnauditedProductByPage(
            @Validated PageParam pageParam
    ){
        PageResult<ProductAuditDTO> page = productService.getUnauditedProductByPage(pageParam);
        List<FarmerAuditPrimaryProductVO> farmerReleasePrimaryProductVOList = FarmerProductConvert.INSTANCE.toFarmerAuditPrimaryProductVO(page.getList());
        return R.ok(new PageResult<>(farmerReleasePrimaryProductVOList,page.getTotal()));
    }




}
