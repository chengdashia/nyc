package git.bds.nyc.admin.controller;

import git.bds.nyc.admin.convert.AuditConvert;
import git.bds.nyc.admin.model.vo.AuditDemandVO;
import git.bds.nyc.admin.model.vo.AuditProductInfoVO;
import git.bds.nyc.admin.model.vo.AuditProductVO;
import git.bds.nyc.admin.service.audit.AuditCommonService;
import git.bds.nyc.demand.model.dto.DemandInfoDTO;
import git.bds.nyc.product.model.dto.AuditProductInfoDTO;
import git.bds.nyc.result.R;
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
    @ApiOperation("获取审核产品详细信息")
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


    /**
     * 获取审核产品信息
     *
     * @param id 需求id
     * @return {@link R}<{@link AuditProductVO}>
     */
    @ApiOperation("获取审核需求详细信息")
    @PostMapping("/getAuditDemandInfo/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "需求id", dataTypeClass = Long.class, paramType = "path", example = "112345646545", required = true)
    })
    public R<AuditDemandVO> getAuditDemandInfo(
            @PathVariable("id") Long id
    ){
        DemandInfoDTO auditDemandInfo = auditCommonService.getAuditDemandInfo(id);
        return R.ok(AuditConvert.INSTANCE.toAuditDemandInfoVO(auditDemandInfo));
    }
}
