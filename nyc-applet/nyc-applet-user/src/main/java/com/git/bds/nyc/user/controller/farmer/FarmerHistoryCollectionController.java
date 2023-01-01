package com.git.bds.nyc.user.controller.farmer;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.model.domain.ProductCollection;
import com.git.bds.nyc.product.model.domain.ProductHistory;
import com.git.bds.nyc.product.model.dto.ProductCollectAndHistoryDTO;
import com.git.bds.nyc.product.service.collection.ProductCollectionService;
import com.git.bds.nyc.product.service.history.ProductHistoryService;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.user.convert.FarmerProductConvert;
import com.git.bds.nyc.user.domain.vo.FarmerProductVO;
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

    /**
     * 按页面获取产品集合
     *
     * @param pageParam 页面参数
     * @param type      类型
     * @return {@link R}<{@link PageResult}<{@link FarmerProductVO}>>
     */
    @ApiOperation(value = "查看产品的收藏是 记录",notes = "产品分农户初级农产品(0) 公司初级农产品(1) 公司加工农产品(2)")
    @PostMapping("/getProductCollectsByPage/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", dataTypeClass = Integer.class, paramType = "path", example = "1", required = true)
    })
    public R<PageResult<FarmerProductVO>> getProductCollectsByPage(
            @RequestBody PageParam pageParam,
            @PathVariable("type") @Min(0) @Max(2) int type
    ){
        PageResult<ProductCollectAndHistoryDTO> page = collectionService.getProductCollectsByPage(pageParam, type);
        List<FarmerProductVO> productCollectionVOList = FarmerProductConvert.INSTANCE.toProductVO(page.getList());
        return R.ok(new PageResult<>(productCollectionVOList,page.getTotal()));
    }

    /**
     * 按页面获取产品历史记录
     *
     * @param pageParam 页面参数
     * @param type      类型
     * @return {@link R}<{@link PageResult}<{@link FarmerProductVO}>>
     */
    @ApiOperation(value = "查看产品的浏览记录",notes = "产品分农户初级农产品(0) 公司初级农产品(1) 公司加工农产品(2)")
    @PostMapping("/getProductHistoryByPage/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", dataTypeClass = Integer.class, paramType = "path", example = "1", required = true)
    })
    public R<PageResult<FarmerProductVO>> getProductHistoryByPage(
            @RequestBody PageParam pageParam,
            @PathVariable("type") @Min(0) @Max(2) int type
    ){
        PageResult<ProductCollectAndHistoryDTO> page = productHistoryService.getProductHistoryByPage(pageParam, type);
        List<FarmerProductVO> productCollectionVOList = FarmerProductConvert.INSTANCE.toProductVO(page.getList());
        return R.ok(new PageResult<>(productCollectionVOList,page.getTotal()));
    }


    /**
     * del浏览记录
     *
     * @param productId 产品id
     * @return {@link R}<{@link Boolean}>
     */
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

    /**
     * 添加产品集合
     *
     * @param id   身份证件
     * @param type 类型
     * @return {@link R}<{@link Boolean}>
     */
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


    /**
     * 取消产品集合
     *
     * @param productId 产品id
     * @return {@link R}<{@link Boolean}>
     */
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
