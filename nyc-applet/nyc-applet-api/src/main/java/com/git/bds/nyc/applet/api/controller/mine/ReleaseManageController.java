package com.git.bds.nyc.applet.api.controller.mine;

import com.git.bds.nyc.applet.api.convert.MineConvert;
import com.git.bds.nyc.applet.api.convert.ProductConvert;
import com.git.bds.nyc.applet.api.model.dto.NumberOfReleaseDTO;
import com.git.bds.nyc.applet.api.model.vo.NumberOfReleaseVO;
import com.git.bds.nyc.applet.api.model.vo.product.AuditPrimaryProductVO;
import com.git.bds.nyc.applet.api.model.vo.product.ProductInfoVO;
import com.git.bds.nyc.applet.api.model.vo.product.ReleaseOnSellPrimaryProductVO;
import com.git.bds.nyc.applet.api.model.vo.product.ReleasePreSellPrimaryProductVO;
import com.git.bds.nyc.applet.api.service.ReleaseService;
import com.git.bds.nyc.enums.ProductStatusType;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.model.dto.ProductModifyDTO;
import com.git.bds.nyc.product.model.dto.ProductReleaseDTO;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.result.ResultCode;
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
import java.util.List;

/**
 * @author 成大事
 * @since 2023/2/7 20:41
 */
@Api(tags = "发布管理接口")
@Slf4j
@Validated
@RestController
@RequestMapping("/applet/mine/release-manage")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReleaseManageController {


    private final ReleaseService releaseService;

    /**
     * 根据类型获取发布的数量
     *
     * @param type 类型
     * @return {@link R}<{@link NumberOfReleaseVO}>
     */
    @ApiOperation("根据类型获取发布的数量")
    @GetMapping("/getNumberOfReleases/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型(0：初级、1:加工)", dataTypeClass = Integer.class, paramType = "path", example = "0", required = true)
    })
    public R<NumberOfReleaseVO> getNumberOfReleases(
            @PathVariable("type") @Min(value = 0,message = "类型错误！！！") @Max(value = 1,message = "类型错误！！！") int type
    ){
        NumberOfReleaseDTO numberOfReleaseDTO = releaseService.getNumberOfReleases(type);
        NumberOfReleaseVO numberOfReleaseVO = MineConvert.INSTANCE.toNumberOfReleaseVO(numberOfReleaseDTO);
        return R.ok(numberOfReleaseVO);
    }


    /**
     * 农户获取发布的初级产品（包括在售、预售和审核中） 分页
     *
     * @param pageParam 页面参数
     * @param type      类型
     * @return {@link R}<{@link PageResult}<{@link Object}>>
     */
    @PostMapping("/getReleaseProductPageByType/{type}")
    @ApiOperation("获取发布的初级产品（包括在售、预售） 分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型(-1:审核,0:在售,1:预售)", dataTypeClass = Integer.class, paramType = "path", example = "1", required = true)
    })
    public R<Object> getReleaseProductPageByType(
            @Validated PageParam pageParam,
            @PathVariable @Min(-1) @Max(1) int type
    ){
        PageResult<ProductReleaseDTO> page;
        if(ProductStatusType.AUDIT.getValue().equals(type)){
            page = releaseService.getUnauditedProductByPage(pageParam);
            List<AuditPrimaryProductVO> list = ProductConvert.INSTANCE.toAuditPreSellPrimaryProductVO(page.getList());
            return R.ok(new PageResult<>(list,page.getTotal()));
        }else if(ProductStatusType.ON_SELL.getValue().equals(type)){
            page = releaseService.getReleaseProductByPage(pageParam,type);
            List<ReleaseOnSellPrimaryProductVO> list = ProductConvert.INSTANCE.toReleaseOnSellPrimaryProductVO(page.getList());
            return R.ok(new PageResult<>(list,page.getTotal()));
        }else if(ProductStatusType.PRE_SELL.getValue().equals(type)){
            page = releaseService.getReleaseProductByPage(pageParam,type);
            List<ReleasePreSellPrimaryProductVO> list = ProductConvert.INSTANCE.toReleasePreSellPrimaryProductVO(page.getList());
            return R.ok(new PageResult<>(list,page.getTotal()));
        }else {
            throw new BusinessException(ResultCode.CONSTRAINT_VIOLATION_EXCEPTION.getCode(),ResultCode.CONSTRAINT_VIOLATION_EXCEPTION.getMessage());
        }

    }


    /**
     * 根据商品id删除发布的商品
     *
     * @param id   产品id
     * @param type 类型 (0：初级、1:加工)
     * @return {@link R}<{@link ProductInfoVO}>
     */
    @ApiOperation("根据商品id删除发布的商品")
    @DeleteMapping("/delReleaseProductById/{type}/{status}/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品id", dataTypeClass = Long.class, paramType = "path", example = "112345646545", required = true),
            @ApiImplicitParam(name = "type", value = "类型(0：初级、1:加工)", dataTypeClass = Integer.class, paramType = "path", example = "0", required = true),
            @ApiImplicitParam(name = "status", value = "状态(-1:审核,0:在售,1:预售)", dataTypeClass = Integer.class, paramType = "path", example = "0", required = true)
    })
    public R<Boolean> delReleaseProductById(
            @PathVariable("id") Long id,
            @PathVariable("type") @Min(value = 0,message = "类型错误！！！") @Max(value = 1,message = "类型错误！！！") int type,
            @PathVariable("status") @Min(value = -1,message = "类型错误！！！") @Max(value = 1,message = "类型错误！！！") int status
    ){
        return R.ok(releaseService.delReleaseProductById(id,type,status));
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
            @Validated({com.git.bds.nyc.product.valid.ValidGroup.All.class}) @RequestBody ProductModifyDTO productDTO
    ){
        return R.decide(releaseService.modifyProductInfo(productDTO));
    }


    /**
     * 根据商品id将预售的商品即刻发布
     *
     * @param id   产品id
     * @param type 类型 (0：初级、1:加工)
     * @return {@link R}<{@link ProductInfoVO}>
     */
    @ApiOperation("根据商品id将预售的商品即刻发布")
    @PostMapping("/releaseProductNowById/{type}/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品id", dataTypeClass = Long.class, paramType = "path", example = "112345646545", required = true),
            @ApiImplicitParam(name = "type", value = "类型(0：初级、1:加工)", dataTypeClass = Integer.class, paramType = "path", example = "0", required = true)
    })
    public R<Boolean> releaseProductNowById(
            @PathVariable("id") Long id,
            @PathVariable("type") @Min(value = 0,message = "类型错误！！！") @Max(value = 1,message = "类型错误！！！") int type
    ){
        return R.ok(releaseService.releaseProductNowById(id,type));
    }
}