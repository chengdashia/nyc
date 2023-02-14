package com.git.bds.nyc.applet.api.controller.personal;

import com.git.bds.nyc.applet.api.convert.MineConvert;
import com.git.bds.nyc.applet.api.model.dto.NumberOfReleaseDTO;
import com.git.bds.nyc.applet.api.model.vo.NumberOfReleaseVO;
import com.git.bds.nyc.applet.api.model.vo.product.ProductInfoVO;
import com.git.bds.nyc.applet.api.service.MineService;
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

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author 成大事
 * @since 2023/2/7 20:41
 */
@Api(tags = "发布管理接口")
@Slf4j
@Validated
@RestController
@RequestMapping("/applet/release")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReleaseController {

    private final MineService mineService;

    @ApiOperation("获取发布的数量")
    @GetMapping("/getNumberOfReleases/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型(0：初级、1:加工)", dataTypeClass = Integer.class, paramType = "path", example = "0", required = true)
    })
    public R<NumberOfReleaseVO> getNumberOfReleases(
            @PathVariable("type") @Min(0) @Max(1) int type
    ){
        NumberOfReleaseDTO numberOfReleaseDTO = mineService.getNumberOfReleases(type);
        NumberOfReleaseVO numberOfReleaseVO = MineConvert.INSTANCE.toNumberOfReleaseVO(numberOfReleaseDTO);
        return R.ok(numberOfReleaseVO);
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
            @PathVariable("type") @Min(0) @Max(1) int type,
            @PathVariable("status") @Min(-1) @Max(1) int status
    ){
        return R.ok(mineService.delReleaseProductById(id,type,status));
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
            @PathVariable("type") @Min(0) @Max(1) int type
    ){
        return R.ok(mineService.releaseProductNowById(id,type));
    }
}
