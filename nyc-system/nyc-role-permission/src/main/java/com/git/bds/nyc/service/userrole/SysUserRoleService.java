package com.git.bds.nyc.service.userrole;

import com.git.bds.nyc.domain.SysUserRole;
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
public interface SysUserRoleService extends MPJBaseService<SysUserRole> {

    /**
     * 获取角色列表
     *
     * @param loginId 登录id
     * @return
     */
    List<String> getRoleList(Object loginId);
}
