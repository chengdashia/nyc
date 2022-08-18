package com.git.bds.nyc.config.saToken;////package com.example.springboot.config.saToken;

import cn.dev33.satoken.stp.StpInterface;
import com.git.bds.nyc.service.SysRolePermissionService;
import com.git.bds.nyc.service.SysUserRoleService;
import com.git.bds.nyc.util.redis.RedisUtils;
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

    private final SysUserRoleService userRoleService;

    private final SysRolePermissionService rolePermissionService;

    private final RedisUtils redisUtil;


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
