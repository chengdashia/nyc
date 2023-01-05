package com.git.bds.nyc.admin.service.audit;

import com.git.bds.nyc.admin.model.AuditStatusDTO;
import com.git.bds.nyc.communal.model.dto.AuditProductDTO;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;

/**
 * @author 成大事
 * @since 2023/1/5 16:03
 */
public interface AuditCorpService {
    /**
     * 按页面获取挂起审核产品
     *
     * @param pageParam 页面参数
     * @param type      类型
     * @return {@link PageResult}<{@link AuditProductDTO}>
     */
    PageResult<AuditProductDTO> getPendingAuditProductByPage(PageParam pageParam, Integer type);

    /**
     * 审核初级产品
     *
     * @param statusDTO 状态dto
     * @return {@link Boolean}
     */
    Boolean toExamineCorpPrimaryProduct(AuditStatusDTO statusDTO);


    /**
     * 供销社审核公司发布的加工农产品
     *
     * @param statusDTO 状态dto
     * @return {@link Boolean}
     */
    Boolean toExamineCorpProcessingProduct(AuditStatusDTO statusDTO);

    /**
     * 审核需求
     *
     * @param statusDTO 状态dto
     * @return {@link Boolean}
     */
    Boolean toExamineDemand(AuditStatusDTO statusDTO);

}
