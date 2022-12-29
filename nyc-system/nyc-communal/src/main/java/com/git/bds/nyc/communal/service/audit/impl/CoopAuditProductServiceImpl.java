package com.git.bds.nyc.communal.service.audit.impl;


import com.git.bds.nyc.communal.mapper.mp.audit.CoopAuditProductMapper;
import com.git.bds.nyc.communal.model.domain.audit.CoopAuditProduct;
import com.git.bds.nyc.communal.service.audit.CoopAuditProductService;
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

    /**
     * 添加审核
     *
     * @param id        身份证件
     * @param productId 产品id
     * @return {@link Boolean}
     */
    @Override
    public Boolean addAudit(long id, long productId) {
        CoopAuditProduct coopAuditProduct = new CoopAuditProduct().setUserId(id).setProductId(productId);
        return this.baseMapper.insert(coopAuditProduct) > 0;
    }
}
