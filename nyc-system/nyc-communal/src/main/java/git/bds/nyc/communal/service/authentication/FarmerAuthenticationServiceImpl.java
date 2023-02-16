package git.bds.nyc.communal.service.authentication;


import git.bds.nyc.communal.mapper.mp.authentication.FarmerAuthenticationMapper;
import git.bds.nyc.communal.model.domain.authentication.FarmerAuthentication;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 个人认证表 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2023-01-11 10:06:59
 */
@Service
public class FarmerAuthenticationServiceImpl extends MPJBaseServiceImpl<FarmerAuthenticationMapper, FarmerAuthentication> implements FarmerAuthenticationService {

}
