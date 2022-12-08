package com.git.bds.nyc.corp.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.git.bds.nyc.corp.convert.CorpProductConvert;
import com.git.bds.nyc.corp.model.vo.CorpProductVO;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.model.domain.ProductCollection;
import com.git.bds.nyc.product.model.domain.ProductHistory;
import com.git.bds.nyc.product.model.dto.ProductCollectAndHistoryDTO;
import com.git.bds.nyc.product.service.collection.ProductCollectionService;
import com.git.bds.nyc.product.service.history.ProductHistoryService;
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
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 成大事
 * @since 2022/10/26 10:43
 */
@Api(tags = "公司产品的 浏览记录和收藏记录")
@Validated
@RestController
@RequestMapping("/corpHistoryCollection")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CorpHistoryCollectionController {

    private final ProductHistoryService productHistoryService;

    private final ProductCollectionService collectionService;

    @ApiOperation(value = "查看收藏的农产品",notes = "产品分农户初级农产品(0) 公司初级农产品(1) 公司加工农产品(2)")
    @PostMapping("/getProductCollectsByPage/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", dataTypeClass = Integer.class, paramType = "path", example = "1", required = true)
    })
    public R<PageResult<CorpProductVO>> getProductCollectsByPage(
            @RequestBody PageParam pageParam,
            @PathVariable("type") @Min(0) @Max(2) int type
    ){
        PageResult<ProductCollectAndHistoryDTO> page = collectionService.getProductCollectsByPage(pageParam, type);
        List<CorpProductVO> productCollectionVOList = CorpProductConvert.INSTANCE.toProductVO(page.getList());
        return R.ok(new PageResult<>(productCollectionVOList,page.getTotal()));
    }

    @ApiOperation(value = "查看产品的浏览记录",notes = "产品分农户初级农产品(0) 公司初级农产品(1) 公司加工农产品(2)")
    @PostMapping("/getProductHistoryByPage/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", dataTypeClass = Integer.class, paramType = "path", example = "1", required = true)
    })
    public R<PageResult<CorpProductVO>> getProductHistoryByPage(
            @RequestBody PageParam pageParam,
            @PathVariable("type") @Min(0) @Max(2) int type
    ){
        PageResult<ProductCollectAndHistoryDTO> page = productHistoryService.getProductHistoryByPage(pageParam, type);
        List<CorpProductVO> productCollectionVOList = CorpProductConvert.INSTANCE.toProductVO(page.getList());
        return R.ok(new PageResult<>(productCollectionVOList,page.getTotal()));
    }


    @ApiOperation("删除浏览记录")
    @PostMapping("/delBrowsingRecord")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataTypeClass = Long.class, paramType = "query", example = "112345646545", required = true)
    })
    public R<Boolean> delBrowsingRecord(
            @RequestParam("id") @NotNull Long productId
    ){
        return R.decide(productHistoryService.remove(new LambdaQueryWrapper<ProductHistory>()
                .eq(ProductHistory::getId,productId)
                .eq(ProductHistory::getUserId, StpUtil.getLoginIdAsLong())));
    }

    @ApiOperation(value = "产品  添加收藏",notes = "产品分农户初级农产品(0) 公司初级农产品(1) 公司加工农产品(2)")
    @PostMapping("/addProductCollection")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品id", dataTypeClass = Long.class, paramType = "query", example = "112345646545", required = true),
            @ApiImplicitParam(name = "type", value = "类型", dataTypeClass = Integer.class, paramType = "query", example = "0", required = true)
    })
    public R<Boolean> addProductCollection(
            @RequestParam("id") @NotNull Long id,
            @RequestParam("type") @Min(0) @Max(2) int type
    ){
        return R.decide(collectionService.addProductCollection(id,type));
    }


    @ApiOperation("产品  取消收藏")
    @PostMapping("/cancelProductCollection")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "产品的id", dataTypeClass = Long.class, paramType = "query", example = "112345646545", required = true)
    })
    public R<Boolean> cancelProductCollection(
            @RequestParam("productId") @NotNull Long productId
    ){
        return R.decide(collectionService.remove(new LambdaQueryWrapper<ProductCollection>()
                .eq(ProductCollection::getProductId,productId)
                .eq(ProductCollection::getUserId, StpUtil.getLoginIdAsLong())));
    }


}
