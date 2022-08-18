package com.git.bds.nyc.service.impl;

import com.git.bds.nyc.dao.SysRolePermissionDao;
import com.git.bds.nyc.domain.SysRolePermission;
import com.git.bds.nyc.service.SysRolePermissionService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-08-17 20:46:59
 */
@Service
public class SysRolePermissionServiceImpl extends MPJBaseServiceImpl<SysRolePermissionDao, SysRolePermission> implements SysRolePermissionService {

    @Resource
    private SysRolePermissionDao sysRolePermissionDao;

    /**
     * 获取权限列表
     *
     * @param loginId 登录id
     * @return {@link List}<{@link String}>
     */
    @Override
    public List<String> getPermissionList(Object loginId) {
        return sysRolePermissionDao.getPermissionList(loginId);
    }
}
