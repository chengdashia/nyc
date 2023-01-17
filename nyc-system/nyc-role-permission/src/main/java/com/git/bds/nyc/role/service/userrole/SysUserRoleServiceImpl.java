package com.git.bds.nyc.role.service.userrole;

import com.git.bds.nyc.constant.Constants;
import com.git.bds.nyc.role.domain.SysRole;
import com.git.bds.nyc.role.domain.SysUserRole;
import com.git.bds.nyc.role.domain.dto.RoleDTO;
import com.git.bds.nyc.role.mapper.mp.SysRoleMapper;
import com.git.bds.nyc.role.mapper.mp.SysUserRoleMapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-08-17 20:46:59
 */
@Slf4j
@Service
public class SysUserRoleServiceImpl extends MPJBaseServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Resource
    private SysRoleMapper sysRoleDao;

    /**
     * 获取角色列表
     *
     * @param loginId 登录id
     * @return 角色列表
     */
    @Override
    public List<String> getRoleList(Object loginId) {
        return sysRoleDao.selectJoinList(RoleDTO.class, new MPJLambdaWrapper<SysRole>()
                .selectAs(SysRole::getRoleName, Constants.ROLE)
                .leftJoin(SysUserRole.class, SysUserRole::getRoleId, SysRole::getId)
                .eq(SysUserRole::getUserId, loginId)).stream().map(RoleDTO::getRole).collect(Collectors.toList());
    }

    ///**
    // * 获取List列表中的Map对象属性的值
    // *
    // * @param list         List<Map<String, Object>>
    // * @param mapValueName Map对象属性名
    // * @return List<T>
    // */
    //@SuppressWarnings("unchecked")
    //public static <T> List<T> getMapValues(List<Map<String, Object>> list, String mapValueName) throws Exception {
    //
    //    log.info("mapValueName = " + mapValueName);
    //
    //    List<T> valueList = new ArrayList<>();
    //
    //    for (Map<String, Object> map : list) {
    //        valueList.add((T) map.get(mapValueName));
    //    }
    //    return valueList;
    //}


}
