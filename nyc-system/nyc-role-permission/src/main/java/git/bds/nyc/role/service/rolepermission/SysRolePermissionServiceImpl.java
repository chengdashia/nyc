package git.bds.nyc.role.service.rolepermission;

import git.bds.nyc.role.mapper.mp.SysRolePermissionMapper;
import git.bds.nyc.role.domain.SysRolePermission;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class SysRolePermissionServiceImpl extends MPJBaseServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {

    @Resource
    private SysRolePermissionMapper sysRolePermissionDao;

    /**
     * 获取权限列表
     *
     * @param loginId 登录id
     * @return {@link List}<{@link String}>
     */
    @Override
    @Transactional
    public List<String> getPermissionList(Object loginId) {
        return sysRolePermissionDao.getPermissionList(loginId);
    }
}
