package com.git.bds.nyc.corp.controller;

import com.git.bds.nyc.product.service.primary.corp.CorpPrimaryProductService;
import com.git.bds.nyc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 初级农产品表 前端控制器
 * </p>
 *
 * @author chnnc
 * @since 2022-10-15 18:50:50
 */
@Api(tags = "公司初级农产品")
@Validated
@RestController
@RequestMapping("/corpPrimaryProduct")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CorpPrimaryProductController {

    private final CorpPrimaryProductService corpPrimaryProductService;


    /**
     * 删除初级农产品
     *
     * @param id 初级农产品id
     * @return {@link = R<Boolean>}
     */
    @PutMapping("/delete/{id}")
    @ApiOperation("根据id删除初级农产品数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id主键", dataTypeClass = Long.class, paramType = "path", example = "123456", required = true)
    })
    public R<Boolean> delete(@PathVariable(value = "id") Long id) {
        return R.decide(corpPrimaryProductService.delete(id));
    }

}
