package git.bds.nyc.admin.controller.gx.audit;

import git.bds.nyc.admin.convert.AuditConvert;
import git.bds.nyc.admin.model.AuditStatusDTO;
import git.bds.nyc.admin.model.vo.AdvertisementVO;
import git.bds.nyc.admin.model.vo.AuditProductVO;
import git.bds.nyc.admin.service.audit.AuditFarmerService;
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
 * @since 2023/1/5 14:41
 */
@Api(tags = "经销社 审核农户发布的")
@Slf4j
@Validated
@RestController
@RequestMapping("/admin/audit/farmer")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuditFarmerController {

    private final AuditFarmerService auditFarmerService;

    /**
     * 供销社 分页获取农户发布的 需要审核的商品
     *
     * @param pageParam 页面参数
     * @return {@link R}<{@link PageResult}<{@link AdvertisementVO}>>
     */
    @ApiOperation("供销社 分页获取农户发布的 需要审核的商品")
    @PostMapping("/getPendingAuditProductByPage/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "审核状态(-1：未审核；0：不通过；1：审核通过)", dataTypeClass = Integer.class, paramType = "path", example = "1", required = true)
    })
    public R<PageResult<AuditProductVO>> getPendingAuditProductByPage(
            @Validated @RequestBody PageParam pageParam,
            @PathVariable("type") @NotNull @Min(-1) @Max(1) int type
    ){
        PageResult<AuditProductDTO> page = auditFarmerService.getPendingAuditProductByPage(pageParam,type);
        List<AuditProductVO> userVOList = AuditConvert.INSTANCE.toAuditProductVOList(page.getList());
        return R.ok(new PageResult<>(userVOList,page.getTotal()));
    }

    /**
     * 经销社审核农户发布的初级农产品
     *
     * @param statusDTO 状态dto
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation("经销社审核农户发布的初级农产品")
    @PostMapping("/toExamineFarmerPrimaryProduct")
    public R<Boolean> toExamineFarmerPrimaryProduct(
            @Validated @RequestBody AuditStatusDTO statusDTO
    ){
        return R.decide(auditFarmerService.toExamineFarmerPrimaryProduct(statusDTO));
    }


    /**
     * 经销社删除 农户发布的初级农产品的审核信息
     *
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation("经销社删除 农户发布的初级农产品的审核信息")
    @DeleteMapping("/toDeleteFarmerPrimaryProduct/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "审核的id", dataTypeClass = Long.class, paramType = "path", example = "1", required = true)
    })
    public R<Boolean> toDeleteFarmerPrimaryProduct(
            @PathVariable("id") Long id
    ){
        return R.decide(auditFarmerService.toDeleteFarmerPrimaryProduct(id));
    }


    /**
     * 供销社审核农户发布的需求
     *
     * @param statusDTO 状态dto
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation("供销社审核农户发布的需求")
    @PostMapping("/toExamineDemand")
    public R<Boolean> toExamineDemand(
            @Validated @RequestBody AuditStatusDTO statusDTO
    ){
        return R.decide(auditFarmerService.toExamineDemand(statusDTO));
    }
}
