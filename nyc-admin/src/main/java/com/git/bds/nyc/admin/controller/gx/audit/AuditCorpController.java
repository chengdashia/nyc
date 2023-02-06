package com.git.bds.nyc.admin.controller.gx.audit;

import com.git.bds.nyc.admin.convert.AuditConvert;
import com.git.bds.nyc.admin.model.AuditStatusDTO;
import com.git.bds.nyc.admin.model.vo.AdvertisementVO;
import com.git.bds.nyc.admin.model.vo.AuditProductVO;
import com.git.bds.nyc.admin.service.audit.AuditCorpService;
import com.git.bds.nyc.communal.model.dto.AuditProductDTO;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 成大事
 * @since 2023/1/5 14:41
 */
@Api(tags = "经销社 审核公司发布的 ")
@Slf4j
@Validated
@RestController
@RequestMapping("/admin/audit/corp")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuditCorpController {

    private final AuditCorpService auditCorpService;

    /**
     * 供销社 分页获取公司发布的 需要审核的商品
     *
     * @param pageParam 页面参数
     * @return {@link R}<{@link PageResult}<{@link AdvertisementVO}>>
     */
    @ApiOperation("供销社 分页获取公司发布的 需要审核的商品")
    @PostMapping("/getPendingAuditProductByPage/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "审核状态(-1：未审核；0：不通过；1：审核通过)", dataTypeClass = Integer.class, paramType = "path", example = "1", required = true)
    })
    public R<PageResult<AuditProductVO>> getPendingAuditProductByPage(
            @Validated @RequestBody PageParam pageParam,
            @PathVariable("type") @NotNull @Min(-1) @Max(1) Integer type
    ){
        log.info("/???????");
        PageResult<AuditProductDTO> page = auditCorpService.getPendingAuditProductByPage(pageParam,type);
        List<AuditProductVO> userVOList = AuditConvert.INSTANCE.toAuditProductVOList(page.getList());
        return R.ok(new PageResult<>(userVOList,page.getTotal()));
    }

    /**
     * 供销社审核公司发布的初级农产品
     *
     * @param statusDTO 状态dto
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation("供销社审核公司发布的初级农产品")
    @PostMapping("/toExamineCorpPrimaryProduct")
    public R<Boolean> toExamineCorpPrimaryProduct(
            @Validated @RequestBody AuditStatusDTO statusDTO
    ){
        return R.decide(auditCorpService.toExamineCorpPrimaryProduct(statusDTO));
    }

    /**
     * 供销社审核公司发布的加工农产品
     *
     * @param statusDTO 状态dto
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation("供销社审核公司发布的加工农产品")
    @PostMapping("/toExamineCorpProcessingProduct")
    public R<Boolean> toExamineCorpProcessingProduct(
            @Validated @RequestBody AuditStatusDTO statusDTO
    ){
        return R.decide(auditCorpService.toExamineCorpProcessingProduct(statusDTO));
    }


    /**
     * 供销社审核公司发布的需求
     *
     * @param statusDTO 状态dto
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation("供销社审核公司发布的需求")
    @PostMapping("/toExamineCorpDemand")
    public R<Boolean> toExamineCorpDemand(
            @Validated @RequestBody AuditStatusDTO statusDTO
    ){
        return R.decide(auditCorpService.toExamineCorpDemand(statusDTO));
    }
}
