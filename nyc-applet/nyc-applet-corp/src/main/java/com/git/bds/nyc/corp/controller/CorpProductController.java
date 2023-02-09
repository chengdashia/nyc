package com.git.bds.nyc.corp.controller;

import com.git.bds.nyc.corp.convert.CorpProductConvert;
import com.git.bds.nyc.corp.model.vo.CorpAuditProductVO;
import com.git.bds.nyc.corp.model.vo.CorpReleaseOnSellProductVO;
import com.git.bds.nyc.corp.model.vo.CorpReleasePreSellProductVO;
import com.git.bds.nyc.corp.service.CorpService;
import com.git.bds.nyc.enums.ProductStatusType;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.model.dto.ProductDTO;
import com.git.bds.nyc.product.model.dto.ProductModifyDTO;
import com.git.bds.nyc.product.model.dto.ProductReleaseDTO;
import com.git.bds.nyc.product.service.primary.corp.CorpPrimaryProductService;
import com.git.bds.nyc.product.service.processing.CorpProcessingProductService;
import com.git.bds.nyc.product.valid.ValidGroup;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.result.ResultCode;
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
import java.util.List;


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

    private final CorpPrimaryProductService corpPrimaryProductService;
    private final CorpProcessingProductService corpProcessingProductService;

    private final CorpService corpService;

    /**
     * 发布 在售初级产品
     *
     * @param productDTO 产品dto
     * @return {@link R}<{@link Object}>
     */
    @PostMapping("/releaseOnSellProduct")
    @ApiOperation("发布初级在售农产品")
    public R<Boolean> releaseOnSellProduct(
            @Validated({ValidGroup.OnSell.class}) @RequestBody ProductDTO productDTO
    ){
        return R.decide(corpService.releaseOnSellProduct(productDTO));
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
        return R.decide(corpService.releasePreSellProduct(productDTO));
    }

    /**
     * 修改公司发布的初级农产品详细信息
     *
     * @param productDTO 产品dto
     * @return {@link R}<{@link Boolean}>
     */
    @PostMapping("/modifyPrimaryProductInfo")
    @ApiOperation("修改公司发布的初级农产品详细信息")
    public R<Boolean> modifyPrimaryProductInfo(
            @Validated({ValidGroup.All.class}) @RequestBody ProductModifyDTO productDTO
    ){
        return R.decide(corpPrimaryProductService.modifyProductInfo(productDTO));
    }


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


    /**
     * 公司获取发布的初级产品（包括在售、预售和审核中） 分页
     *
     * @param pageParam 页面参数
     * @return {@link R}<{@link PageResult}<{@link CorpReleaseOnSellProductVO}>>
     */
    @PostMapping("/getReleaseProductByPage/{type}")
    @ApiOperation("公司获取发布的初级产品（包括在售、预售和审核中） 分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型(-1:审核中,0：在售,1:预售)", dataTypeClass = Integer.class, paramType = "path", example = "1", required = true)
    })
    public R<Object> getReleaseProductByPage(
            @Validated PageParam pageParam,
            @PathVariable @Min(-1) @Max(1) int type
    ){
        PageResult<ProductReleaseDTO> page;
        if(ProductStatusType.AUDIT.getValue().equals(type)){
            page = corpPrimaryProductService.getUnauditedProductByPage(pageParam);
            List<CorpAuditProductVO> list = CorpProductConvert.INSTANCE.toCorpAuditPrimaryProductVO(page.getList());
            return R.ok(new PageResult<>(list,page.getTotal()));
        }else if(ProductStatusType.ON_SELL.getValue().equals(type)){
            page = corpPrimaryProductService.getReleaseProductByPage(pageParam,type);
            List<CorpReleaseOnSellProductVO> list = CorpProductConvert.INSTANCE.toCorpReleaseOnSellProductVO(page.getList());
            return R.ok(new PageResult<>(list,page.getTotal()));
        }else if(ProductStatusType.PRE_SELL.getValue().equals(type)){
            page = corpPrimaryProductService.getReleaseProductByPage(pageParam,type);
            List<CorpReleasePreSellProductVO> list = CorpProductConvert.INSTANCE.toCorpReleasePreSellProductVO(page.getList());
            return R.ok(new PageResult<>(list,page.getTotal()));
        }else {
            throw new BusinessException(ResultCode.CONSTRAINT_VIOLATION_EXCEPTION.getCode(),ResultCode.CONSTRAINT_VIOLATION_EXCEPTION.getMessage());
        }
    }




}
