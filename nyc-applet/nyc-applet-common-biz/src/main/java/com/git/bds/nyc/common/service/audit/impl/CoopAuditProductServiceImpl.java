package com.git.bds.nyc.common.service.audit.impl;


import com.git.bds.nyc.common.mapper.mp.audit.CoopAuditProductMapper;
import com.git.bds.nyc.common.model.domain.audit.CoopAuditProduct;
import com.git.bds.nyc.common.service.audit.CoopAuditProductService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 合作社审核个人发布的农产品(只有初级农产品) 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-11-23 10:43:48
 */
@Service
public class CoopAuditProductServiceImpl extends MPJBaseServiceImpl<CoopAuditProductMapper, CoopAuditProduct> implements CoopAuditProductService {

}
