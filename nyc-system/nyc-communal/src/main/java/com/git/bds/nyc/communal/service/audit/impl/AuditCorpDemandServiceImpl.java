package com.git.bds.nyc.communal.service.audit.impl;


import com.git.bds.nyc.communal.mapper.mp.audit.AuditCorpDemandMapper;
import com.git.bds.nyc.communal.model.domain.audit.AuditCorpDemand;
import com.git.bds.nyc.communal.service.audit.AuditCorpDemandService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;


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

}
