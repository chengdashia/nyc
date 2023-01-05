package com.git.bds.nyc.communal.service.audit;


import com.git.bds.nyc.communal.model.domain.audit.CoopAuditProduct;
import com.github.yulichang.base.MPJBaseService;

/**
 * <p>
 * 合作社审核个人发布的农产品(只有初级农产品) 服务类
 * </p>
 *
 * @author 成大事
 * @since 2022-11-23 10:43:48
 */
public interface CoopAuditProductService extends MPJBaseService<CoopAuditProduct> {

    /**
     * 添加审核
     *
     * @param id        身份证件
     * @param productId 产品id
     * @return {@link Boolean}
     */
    Boolean addAudit(long id, long productId);


}
