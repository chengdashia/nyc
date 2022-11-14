package com.git.bds.nyc.user.controller;

import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.model.dto.ProductCollectionDTO;
import com.git.bds.nyc.product.service.collection.ProductCollectionService;
import com.git.bds.nyc.product.service.history.ProductHistoryService;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.user.convert.FarmerProductConvert;
import com.git.bds.nyc.user.domain.vo.ProductVO;
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
 * @author 成大事
 * @since 2022/10/26 14:23
 */
@Api(tags = "农户的 浏览记录和收藏记录")
@Validated
@RestController
@RequestMapping("/farmerHistoryCollection")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FarmerHistoryCollectionController {

    private final ProductHistoryService productHistoryService;
    private final ProductCollectionService collectionService;

    @ApiOperation(value = "查看产品的收藏记录",notes = "产品分农户初级农产品(0) 公司初级农产品(1) 公司加工农产品(2)")
    @PostMapping("/getProductCollectsByPage/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", dataTypeClass = Integer.class, paramType = "path", example = "1", required = true)
    })
    public R<PageResult<ProductVO>> getProductCollectsByPage(
            @RequestBody PageParam pageParam,
            @PathVariable("type") @Min(0) @Max(2) int type
    ){
        PageResult<ProductCollectionDTO> page = collectionService.getProductCollectsByPage(pageParam, type);
        List<ProductVO> productCollectionVOList = FarmerProductConvert.INSTANCE.toProductVO(page.getList());
        return R.ok(new PageResult<>(productCollectionVOList,page.getTotal()));
    }

    @ApiOperation(value = "查看产品的浏览记录",notes = "产品分农户初级农产品(0) 公司初级农产品(1) 公司加工农产品(2)")
    @PostMapping("/getProductHistoryByPage/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", dataTypeClass = Integer.class, paramType = "path", example = "1", required = true)
    })
    public R<PageResult<ProductVO>> getProductHistoryByPage(
            @RequestBody PageParam pageParam,
            @PathVariable("type") @Min(0) @Max(2) int type
    ){
        PageResult<ProductCollectionDTO> page = productHistoryService.getProductHistoryByPage(pageParam, type);
        List<ProductVO> productCollectionVOList = FarmerProductConvert.INSTANCE.toProductVO(page.getList());
        return R.ok(new PageResult<>(productCollectionVOList,page.getTotal()));
    }

    @ApiOperation(value = "产品  添加收藏",notes = "产品分农户初级农产品(0) 公司初级农产品(1) 公司加工农产品(2)")
    @PostMapping("/addProductCollection")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品id", dataTypeClass = Long.class, paramType = "query", example = "112345646545", required = true),
            @ApiImplicitParam(name = "type", value = "类型", dataTypeClass = Integer.class, paramType = "query", example = "0", required = true)
    })
    public R<Boolean> addProductCollection(
            @RequestParam("id") Long id,
            @RequestParam("type") int type
    ){
        return R.decide(collectionService.addProductCollection(id,type));
    }


    @ApiOperation(value = "产品  取消收藏",notes = "产品分农户初级农产品(0) 公司初级农产品(1) 公司加工农产品(2)")
    @PostMapping("/cancelProductCollection")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收藏的id", dataTypeClass = Long.class, paramType = "query", example = "112345646545", required = true)
    })
    public R<Boolean> cancelProductCollection(
            @RequestParam("id") Long id
    ){
        return R.decide(collectionService.removeById(id));
    }


}
