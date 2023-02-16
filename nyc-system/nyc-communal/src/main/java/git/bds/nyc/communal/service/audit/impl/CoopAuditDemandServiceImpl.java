package git.bds.nyc.communal.service.audit.impl;


import git.bds.nyc.communal.mapper.mp.audit.CoopAuditDemandMapper;
import git.bds.nyc.communal.model.domain.audit.CoopAuditDemand;
import git.bds.nyc.communal.service.audit.CoopAuditDemandService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 合作社审核个人发布的农产品(只有初级农产品) 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2023-01-05 14:56:27
 */
@Service
public class CoopAuditDemandServiceImpl extends MPJBaseServiceImpl<CoopAuditDemandMapper, CoopAuditDemand> implements CoopAuditDemandService {

}
