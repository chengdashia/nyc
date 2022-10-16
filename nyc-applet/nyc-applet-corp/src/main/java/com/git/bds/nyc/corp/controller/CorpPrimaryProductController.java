package com.git.bds.nyc.corp.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.corp.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.corp.service.CorpPrimaryProductService;
import com.git.bds.nyc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "初级农产品")
@RestController
@RequestMapping("/corpPrimaryProduct")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CorpPrimaryProductController {

    private final CorpPrimaryProductService corpPrimaryProductService;

    /**
     * 分页、条件查询初级农产品
     *
     * @param current 分页页码
     * @param size    分页长度
     * @param param   查询参数
     * @return {@link = R<List<CorpPrimaryProduct>>}
     */
    @PostMapping("/getPage")
    @ApiOperation("分页、条件查询初级农产品")
    public R<List<CorpPrimaryProduct>> page(
            @Min(value = 1) @RequestParam int current,
            @RequestParam int size,
            CorpPrimaryProduct param
    ) {
        IPage<CorpPrimaryProduct> page = corpPrimaryProductService.findPage(new Page<>(current, size), param);
        if (page.getRecords() == null || page.getTotal() == 0) {
            return R.page(page.getRecords(), page.getTotal());
        }
        return R.page(null, 0L);
    }

    /**
     * 新增初级农产品
     *
     * @param data 初级农产品信息
     * @return {@link = R<Boolean>}
     */
    @PostMapping("/save")
    @ApiOperation("新增初级农产品")
    public R<Boolean> save(@RequestBody CorpPrimaryProduct data) {
        return corpPrimaryProductService.save(data) ? R.ok() : R.error();
    }

    /**
     * 根据id更新初级农产品信息
     *
     * @param id   初级农产品id
     * @param data 初级农产品信息
     * @return {@link = R<Boolean>}
     */
    @PutMapping("/modify/{id}")
    @ApiOperation("更新初级农产品数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id主键", dataType = "java.lang.Long", paramType = "path", example = "123456", required = true)
    })
    public R<Boolean> modify(@PathVariable(value = "id") Long id, @RequestBody CorpPrimaryProduct data) {
        data.setId(id);
        data.setUserId(Long.valueOf(StpUtil.getLoginId().toString()));
        return corpPrimaryProductService.modify(data) ? R.ok() : R.error();
    }

    /**
     * 删除初级农产品
     *
     * @param id 初级农产品id
     * @return {@link = R<Boolean>}
     */
    @PutMapping("/delete/{id}")
    @ApiOperation("根据id删除初级农产品数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id主键", dataType = "java.lang.Long", paramType = "path", example = "123456", required = true)
    })
    public R<Boolean> delete(@PathVariable(value = "id") Long id) {
        return corpPrimaryProductService.delete(id) ? R.ok() : R.error();
    }

}
