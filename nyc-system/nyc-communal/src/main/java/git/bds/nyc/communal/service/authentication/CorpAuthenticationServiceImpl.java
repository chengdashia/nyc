package git.bds.nyc.communal.service.authentication;


import git.bds.nyc.communal.mapper.mp.authentication.CorpAuthenticationMapper;
import git.bds.nyc.communal.model.domain.authentication.CorpAuthentication;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司认证表 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2023-01-11 10:06:59
 */
@Service
public class CorpAuthenticationServiceImpl extends MPJBaseServiceImpl<CorpAuthenticationMapper, CorpAuthentication> implements CorpAuthenticationService {

}
