package com.git.bds.nyc.applet.api.controller.mine;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.git.bds.nyc.applet.api.convert.HistoryConvert;
import com.git.bds.nyc.applet.api.model.vo.HistoryRecordVO;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.model.domain.ProductHistory;
import com.git.bds.nyc.product.model.dto.ProductCollectAndHistoryDTO;
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
 * @since 2023/2/14 20:36
 */
@Api(tags = "浏览记录接口管理")
@Validated
@RestController
@RequestMapping("/applet/mine/history")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class HistoryController {

    private final ProductHistoryService historyService;

    /**
     * 根据类型分页查看产品的浏览记录
     *
     * @param pageParam 页面参数
     * @param type      类型 产品分农户初级农产品(0) 公司初级农产品(1) 公司加工农产品(2)
     * @return {@link R}<{@link PageResult}<{@link HistoryRecordVO}>>
     */
    @ApiOperation(value = "根据类型分页查看产品的浏览记录",notes = "产品分农户初级农产品(0) 公司初级农产品(1) 公司加工农产品(2)")
    @PostMapping("/getBrowsingRecordPageByType/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", dataTypeClass = Integer.class, paramType = "path", example = "1", required = true)
    })
    public R<PageResult<HistoryRecordVO>> getBrowsingRecordPageByType(
            @RequestBody PageParam pageParam,
            @PathVariable("type") @Min(0) @Max(2) int type
    ){
        PageResult<ProductCollectAndHistoryDTO> page = historyService.getBrowsingRecordPageByType(pageParam, type);
        List<HistoryRecordVO> productCollectionVOList = HistoryConvert.INSTANCE.toHistoryRecordVO(page.getList());
        return R.ok(new PageResult<>(productCollectionVOList,page.getTotal()));
    }


    /**
     * 根据浏览记录Id删除浏览记录
     *
     * @param id 浏览记录id
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation("根据浏览记录Id删除浏览记录")
    @PostMapping("/delBrowsingRecordById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataTypeClass = Long.class, paramType = "query", example = "112345646545", required = true)
    })
    public R<Boolean> delBrowsingRecord(
            @RequestParam("id") @NotNull Long id
    ){
        return R.decide(historyService.remove(new LambdaQueryWrapper<ProductHistory>()
                .eq(ProductHistory::getId,id)
                .eq(ProductHistory::getUserId, StpUtil.getLoginIdAsLong())));
    }

}
