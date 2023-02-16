package git.bds.nyc.admin.mapper.mp;

import git.bds.nyc.admin.model.domain.SysAdmin;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 管理员表，用来登录web端。后台管理 Mapper 接口
 * </p>
 *
 * @author 成大事
 * @since 2022-11-14 16:53:41
 */
@Mapper
public interface SysAdminMapper extends MPJBaseMapper<SysAdmin> {

}
