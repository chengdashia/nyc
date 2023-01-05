package com.git.bds.nyc.admin.service.audit;

import com.git.bds.nyc.product.model.dto.AuditProductInfoDTO;

/**
 * @author 成大事
 * @since 2023/1/5 16:34
 */
public interface AuditCommonService {
    /**
     * 获取审核产品信息
     *
     * @param id   身份证件
     * @param type 类型
     * @return {@link AuditProductInfoDTO}
     */
    AuditProductInfoDTO getAuditProductInfo(Long id, Integer type);
}
