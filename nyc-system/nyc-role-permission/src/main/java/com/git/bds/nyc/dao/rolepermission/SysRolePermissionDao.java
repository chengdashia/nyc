package com.git.bds.nyc.dao.rolepermission;

import com.git.bds.nyc.domain.SysRolePermission;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 成大事
 * @since 2022-08-17 20:46:59
 */
@Mapper
public interface SysRolePermissionDao extends MPJBaseMapper<SysRolePermission> {

    /**
     * 获取权限列表
     *
     * @param id 登录id
     * @return {@link List}<{@link String}>
     */
    @Select("SELECT\n" +
            "\tp.permission_name\n" +
            "FROM\n" +
            "\tsys_permission p\n" +
            "\tLEFT JOIN sys_role_permission rp ON rp.permission_id = p.id\n" +
            "\tLEFT JOIN sys_role r ON rp.role_id = r.id \n" +
            "WHERE\n" +
            "\tr.id = (SELECT role_id FROM sys_user_role WHERE user_id = #{id});")
    List<String> getPermissionList(@Param("id") Object id);
}
