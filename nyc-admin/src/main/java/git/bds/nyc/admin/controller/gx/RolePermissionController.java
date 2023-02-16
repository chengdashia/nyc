package git.bds.nyc.admin.controller.gx;

import git.bds.nyc.admin.convert.AdminConvert;
import git.bds.nyc.admin.model.vo.UserVO;
import git.bds.nyc.admin.service.admin.SysAdminService;
import git.bds.nyc.page.PageParam;
import git.bds.nyc.page.PageResult;
import git.bds.nyc.result.R;
import git.bds.nyc.user.model.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 成大事
 * @since 2023/1/4 15:20
 */
@Api(tags = "角色权限管理")
@Validated
@RestController
@RequestMapping("/admin/role-permission")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RolePermissionController {

    private final SysAdminService adminService;

    /**
     * getRolesByPage
     *
     * @param pageParam 页面参数
     * @return {@link R}<{@link PageResult}<{@link UserVO}>>
     */
    @ApiOperation("获取角色列表 分页")
    @PostMapping("/getRolesByPage")
    public R<PageResult<UserVO>> getRolesByPage(
            @Validated @RequestBody PageParam pageParam
    ){
        PageResult<UserDTO> page = adminService.getRolesByPage(pageParam);
        List<UserVO> userVOList = AdminConvert.INSTANCE.toUserVOList(page.getList());
        return R.ok(new PageResult<>(userVOList,page.getTotal()));
    }
}

