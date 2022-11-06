package com.git.bds.nyc.corp.controller;

import com.git.bds.nyc.corp.model.dto.AuditCorpProductUpdateDTO;
import com.git.bds.nyc.corp.service.AuditCorpProductService;
import com.git.bds.nyc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 审核公司发布的农产品(包括初级和加工的商品) 前端控制器
 * </p>
 *
 * @author chnnc
 * @since 2022-10-15 18:50:50
 */
@Api(tags = "审核公司发布的农产品")
@Validated
@RestController
@RequestMapping("/auditCorpProduct")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuditCorpProductController {
    private final AuditCorpProductService auditCorpProductService;

    /**
     * 更新公司农产品状态
     * @param auditCorpProductUpdateDTO 更新数据
     * @return {@link R<Boolean>}
     */

    @PutMapping("/auditCorpProductStatus")
    @ApiOperation("更新公司农产品审核状态")
    public R<Boolean> auditCorpProductStatus(
            @Validated @RequestBody AuditCorpProductUpdateDTO auditCorpProductUpdateDTO
    ){
        return R.decide(auditCorpProductService.updateCropProduct(auditCorpProductUpdateDTO));
    }
    

}
