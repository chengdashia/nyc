package com.git.bds.nyc;

import com.git.bds.nyc.service.SysRolePermissionService;
import com.git.bds.nyc.service.SysUserRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NycServerApplicationTests {

    @Autowired
    private SysUserRoleService userRoleService;

    @Autowired
    private SysRolePermissionService rolePermissionService;

    @Test
    void contextLoads() {
        //System.out.println("userRoleService.getRoleList(1) = " + userRoleService.getRoleList(1));
        System.out.println("rolePermissionService.getPermissionList(1) = " + rolePermissionService.getPermissionList(1));
    }

}
