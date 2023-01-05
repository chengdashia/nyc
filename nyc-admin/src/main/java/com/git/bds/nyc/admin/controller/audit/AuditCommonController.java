package com.git.bds.nyc.admin.controller.audit;

import com.git.bds.nyc.admin.convert.AuditConvert;
import com.git.bds.nyc.admin.model.vo.AuditProductInfoVO;
import com.git.bds.nyc.admin.model.vo.AuditProductVO;
import com.git.bds.nyc.admin.service.audit.AuditCommonService;
import com.git.bds.nyc.product.model.dto.AuditProductInfoDTO;
import com.git.bds.nyc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 成大事
 * @since 2023/1/5 16:32
 */
@Api(tags = "审核 通用的")
@Slf4j
@Validated
@RestController
@RequestMapping("/admin/audit/common")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuditCommonController {

    private final AuditCommonService auditCommonService;

    /**
     * 获取审核产品信息
     *
     * @param id 产品id
     * @return {@link R}<{@link AuditProductVO}>
     */
    @ApiOperation("获取审核产品信息")
    @PostMapping("/getAuditProductInfo/{id}/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品id", dataTypeClass = Long.class, paramType = "path", example = "112345646545", required = true),
            @ApiImplicitParam(name = "type", value = "类型", dataTypeClass = Integer.class, paramType = "path", example = "0", required = true)
    })
    public R<AuditProductInfoVO> getAuditProductInfo(
            @PathVariable("id") Long id,
            @PathVariable("type") Integer type
    ){
        AuditProductInfoDTO auditProductInfoDTO = auditCommonService.getAuditProductInfo(id,type);
        return R.ok(AuditConvert.INSTANCE.toAuditProductInfoVO(auditProductInfoDTO));
    }
}
