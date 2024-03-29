package git.bds.nyc.communal.service.audit.impl;


import git.bds.nyc.communal.mapper.mp.audit.AuditCorpDemandMapper;
import git.bds.nyc.communal.model.domain.audit.AuditCorpDemand;
import git.bds.nyc.communal.service.audit.AuditCorpDemandService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 审核公司发布的需求 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-11-23 10:43:48
 */
@Service
public class AuditCorpDemandServiceImpl extends MPJBaseServiceImpl<AuditCorpDemandMapper, AuditCorpDemand> implements AuditCorpDemandService {


    /**
     * 添加审核
     *
     * @param id        身份证件
     * @param demandId 产品id
     * @return {@link Boolean}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addAudit(long id, long demandId) {
        AuditCorpDemand auditCorpDemand = new AuditCorpDemand().setUserId(id).setDemandId(demandId);
        return this.baseMapper.insert(auditCorpDemand) > 0;
    }
}
