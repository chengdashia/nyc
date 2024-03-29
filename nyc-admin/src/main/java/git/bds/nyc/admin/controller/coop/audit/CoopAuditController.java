package git.bds.nyc.admin.controller.coop.audit;


import git.bds.nyc.admin.convert.AuditConvert;
import git.bds.nyc.admin.model.AuditStatusDTO;
import git.bds.nyc.admin.model.vo.AdvertisementVO;
import git.bds.nyc.admin.model.vo.AuditProductVO;
import git.bds.nyc.admin.service.audit.coop.CoopAuditService;
import git.bds.nyc.communal.model.dto.AuditProductDTO;
import git.bds.nyc.page.PageParam;
import git.bds.nyc.page.PageResult;
import git.bds.nyc.result.R;
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
 * @since 2023/1/5 14:51
 */
@Api(tags = "合作社 审核农户发布的 ")
@Slf4j
@Validated
@RestController
@RequestMapping("/admin/coop/audit")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CoopAuditController {

    private final CoopAuditService coopAuditService;

    /**
     * 合作社 分页获取农户发布的 需要审核的初级农产品
     *
     * @param pageParam 页面参数
     * @return {@link R}<{@link PageResult}<{@link AdvertisementVO}>>
     */
    @ApiOperation("合作社 分页获取农户发布的 需要审核的初级农产品")
    @PostMapping("/getPendingAuditProductByPage/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "审核状态(-1：未审核；0：不通过；1：审核通过)", dataTypeClass = Integer.class, paramType = "path", example = "1", required = true)
    })
    public R<PageResult<AuditProductVO>> getPendingAuditProductByPage(
            @Validated @RequestBody PageParam pageParam,
            @PathVariable("type") @NotNull @Min(-1) @Max(1) Integer type
    ) {
        PageResult<AuditProductDTO> page = coopAuditService.getPendingAuditProductByPage(pageParam, type);
        List<AuditProductVO> userVOList = AuditConvert.INSTANCE.toAuditProductVOList(page.getList());
        return R.ok(new PageResult<>(userVOList, page.getTotal()));
    }


    /**
     * 合作社审核农户的初级农产品
     *
     * @param statusDTO 状态dto
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation("合作社 审核农户的初级农产品")
    @PostMapping("/toExamineProduct")
    public R<Boolean> toExamineProduct(
            @Validated @RequestBody AuditStatusDTO statusDTO
    ){
        return R.decide(coopAuditService.toExamineProduct(statusDTO));
    }


    /**
     * 合作社 审核农户需求
     *
     * @param statusDTO 状态dto
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation("合作社 审核农户需求")
    @PostMapping("/toExamineDemand")
    public R<Boolean> toExamineDemand(
            @Validated @RequestBody AuditStatusDTO statusDTO
    ){
        return R.decide(coopAuditService.toExamineDemand(statusDTO));
    }






}