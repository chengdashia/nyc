package git.bds.nyc.config.saToken;

import cn.dev33.satoken.stp.StpInterface;
import git.bds.nyc.role.service.rolepermission.SysRolePermissionService;
import git.bds.nyc.role.service.userrole.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义权限验证接口扩展
 *
 * @author 成大事
 * @since 2022/3/31 22:23
 * 保证此类被SpringBoot扫描，完成Sa-Token的自定义权限验证扩展
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class StpInterfaceImpl implements StpInterface {

    /** 用户角色 */
    private final SysUserRoleService userRoleService;

    /** 角色权限 */
    private final SysRolePermissionService rolePermissionService;


    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return rolePermissionService.getPermissionList(loginId);
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return userRoleService.getRoleList(loginId);
    }
}
