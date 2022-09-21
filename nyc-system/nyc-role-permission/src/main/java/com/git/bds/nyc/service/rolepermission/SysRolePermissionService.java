package com.git.bds.nyc.service.rolepermission;

import com.git.bds.nyc.domain.SysRolePermission;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 成大事
 * @since 2022-08-17 20:46:59
 */
public interface SysRolePermissionService extends MPJBaseService<SysRolePermission> {

    /**
     * 获取权限列表
     *
     * @param loginId 登录id
     * @return {@link List}<{@link String}>
     */
    List<String> getPermissionList(Object loginId);
}
