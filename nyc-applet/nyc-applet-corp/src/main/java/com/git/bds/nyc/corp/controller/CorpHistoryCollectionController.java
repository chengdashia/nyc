package com.git.bds.nyc.corp.controller;

import com.git.bds.nyc.corp.convert.CorpProductConvert;
import com.git.bds.nyc.corp.model.vo.CorpProductCollectionVO;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.model.dto.ProductCollectionDTO;
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

    private final ProductHistoryService historyService;

    private final ProductCollectionService collectionService;

    @ApiOperation("查看收藏的初级农产品")
    @PostMapping("/getProductCollectsByPage/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", dataTypeClass = Integer.class, paramType = "path", example = "1", required = true)
    })
    public R<PageResult<CorpProductCollectionVO>> getProductCollectsByPage(
            @RequestBody PageParam pageParam,
            @PathVariable("type") @Min(0) @Max(2) int type
    ){
        PageResult<ProductCollectionDTO> page = collectionService.getProductCollectsByPage(pageParam, type);
        List<CorpProductCollectionVO> productCollectionVOList = CorpProductConvert.INSTANCE.toProductCollectionVO(page.getList());
        return R.ok(new PageResult<>(productCollectionVOList,page.getTotal()));
    }


}
