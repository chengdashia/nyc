package git.bds.nyc.communal.mapper.mp.authentication;

import git.bds.nyc.communal.model.domain.authentication.FarmerAuthentication;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 个人认证表 Mapper 接口
 * </p>
 *
 * @author 成大事
 * @since 2023-01-11 10:06:59
 */
@Mapper
public interface FarmerAuthenticationMapper extends MPJBaseMapper<FarmerAuthentication> {

}
