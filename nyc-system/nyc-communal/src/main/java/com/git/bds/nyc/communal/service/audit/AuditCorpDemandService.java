package com.git.bds.nyc.communal.service.audit;


import com.git.bds.nyc.communal.model.domain.audit.AuditCorpDemand;
import com.github.yulichang.base.MPJBaseService;

/**
 * <p>
 * 审核公司发布的需求 服务类
 * </p>
 *
 * @author 成大事
 * @since 2022-11-23 10:43:48
 */
public interface AuditCorpDemandService extends MPJBaseService<AuditCorpDemand> {

    /**
     * 添加审核
     *
     * @param id        身份证件
     * @param demandId 需求id
     * @return {@link Boolean}
     */
    Boolean addAudit(long id, long demandId);

}
