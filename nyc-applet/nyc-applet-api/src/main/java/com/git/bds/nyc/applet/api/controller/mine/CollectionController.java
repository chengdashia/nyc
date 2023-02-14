package com.git.bds.nyc.applet.api.controller.mine;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.git.bds.nyc.applet.api.convert.CollectionConvert;
import com.git.bds.nyc.applet.api.model.vo.CollectionRecordVO;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.model.domain.ProductCollection;
import com.git.bds.nyc.product.model.dto.ProductCollectAndHistoryDTO;
import com.git.bds.nyc.product.service.collection.ProductCollectionService;
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
 * @since 2023/2/14 20:37
 */
@Api(tags = "收藏记录接口管理")
@Validated
@RestController
@RequestMapping("/applet/mine/collection")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CollectionController {

    private final ProductCollectionService collectionService;


    /**
     * 根据类型分页查看产品的收藏记录
     *
     * @param pageParam 页面参数
     * @param type      类型 产品分农户初级农产品(0) 公司初级农产品(1) 公司加工农产品(2)
     * @return {@link R}<{@link PageResult}<{@link CollectionRecordVO}>>
     */
    @ApiOperation(value = "根据类型分页查看产品的收藏记录",notes = "产品分农户初级农产品(0) 公司初级农产品(1) 公司加工农产品(2)")
    @PostMapping("/getCollectionRecordsPageByType/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", dataTypeClass = Integer.class, paramType = "path", example = "1", required = true)
    })
    public R<PageResult<CollectionRecordVO>> getCollectionRecordsPageByType(
            @RequestBody PageParam pageParam,
            @PathVariable("type") @Min(0) @Max(2) int type
    ){
        PageResult<ProductCollectAndHistoryDTO> page = collectionService.getCollectionRecordsPageByType(pageParam, type);
        List<CollectionRecordVO> collectionRecordVOList = CollectionConvert.INSTANCE.toCollectionRecordVO(page.getList());
        return R.ok(new PageResult<>(collectionRecordVOList,page.getTotal()));
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
     * 根据产品id取消收藏
     *
     * @param productId 产品id
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation("根据产品id取消收藏")
    @PostMapping("/cancelCollectionById")
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
