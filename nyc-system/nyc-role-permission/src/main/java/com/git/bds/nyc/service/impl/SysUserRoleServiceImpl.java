package com.git.bds.nyc.service.impl;

import com.git.bds.nyc.constant.Constants;
import com.git.bds.nyc.dao.SysRoleDao;
import com.git.bds.nyc.dao.SysUserRoleDao;
import com.git.bds.nyc.domain.SysRole;
import com.git.bds.nyc.domain.SysUserRole;
import com.git.bds.nyc.domain.dto.RoleDTO;
import com.git.bds.nyc.service.SysUserRoleService;
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
public class SysUserRoleServiceImpl extends MPJBaseServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {

    @Resource
    private SysRoleDao sysRoleDao;

    /**
     * 获取角色列表
     *
     * @param loginId 登录id
     * @return 角色列表
     */
    @Override
    public List<String> getRoleList(Object loginId) {
        return sysRoleDao.selectJoinList(RoleDTO.class, new MPJLambdaWrapper<>()
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
