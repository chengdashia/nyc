package git.bds.nyc.admin.controller.gx;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import git.bds.nyc.admin.convert.CoopManageConvert;
import git.bds.nyc.admin.model.domain.CoopInfo;
import git.bds.nyc.admin.model.dto.CoopUserDTO;
import git.bds.nyc.admin.model.vo.AdvertisementVO;
import git.bds.nyc.admin.model.vo.CoopUserVO;
import git.bds.nyc.admin.service.coop.CoopInfoService;
import git.bds.nyc.admin.service.coop.CoopUserService;
import git.bds.nyc.page.PageParam;
import git.bds.nyc.page.PageResult;
import git.bds.nyc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 成大事
 * @since 2023/1/7 14:03
 */
@Api(tags = "供销社管理合作社")
@Validated
@RestController
@RequestMapping("/admin/coop")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CoopManageController {

    private final CoopInfoService coopInfoService;

    private final CoopUserService coopUserService;


    /**
     * 分页获取下属合作社列表
     *
     * @param pageParam 页面参数
     * @return {@link R}<{@link PageResult}<{@link AdvertisementVO}>>
     */
    @ApiOperation("获取下属合作社列表 分页")
    @PostMapping("/getCoopListByPage")
    public R<PageResult<CoopInfo>> getCoopListByPage(
            @Validated @RequestBody PageParam pageParam
    ){
        Page<CoopInfo> page = coopInfoService.page(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()), null);
        return R.ok(new PageResult<>(page.getRecords(),page.getTotal()));
    }

    /**
     * 根据合作社id获取下属的农户
     *
     * @param id 合作社id
     * @return {@link R}<{@link Boolean}>
     */
    @SneakyThrows
    @ApiOperation("根据合作社id获取下属的农户")
    @PostMapping("/getSubordinateFarmerByCoopId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "合作社id", dataTypeClass = Long.class, paramType = "query", example = "1", required = true)
    })
    public R<PageResult<CoopUserVO>> getSubordinateFarmerByCoopId(
            @Validated @RequestBody PageParam pageParam,
            @RequestParam("id") @NotNull Long id
    ){
        PageResult<CoopUserDTO> page = coopUserService.getSubordinateFarmerByCoopId(pageParam,id);
        List<CoopUserVO> coopUserVOList = CoopManageConvert.INSTANCE.toCoopUserVO(page.getList());
        return R.ok(new PageResult<>(coopUserVOList,page.getTotal()));
    }


}
