package git.bds.nyc.admin.service.audit.coop;

import git.bds.nyc.admin.model.AuditStatusDTO;
import git.bds.nyc.communal.model.dto.AuditProductDTO;
import git.bds.nyc.page.PageParam;
import git.bds.nyc.page.PageResult;

/**
 * @author 成大事
 * @since 2023/1/5 15:46
 */
public interface CoopAuditService {

    /**
     * 按页面获取挂起审核产品
     *
     * @param pageParam 页面参数
     * @param type      类型
     * @return {@link PageResult}<{@link AuditProductDTO}>
     */
    PageResult<AuditProductDTO> getPendingAuditProductByPage(PageParam pageParam, Integer type);

    /**
     * 审核产品
     *
     * @param statusDTO 状态dto
     * @return {@link Boolean}
     */
    Boolean toExamineProduct(AuditStatusDTO statusDTO);

    /**
     * 审核需求
     *
     * @param statusDTO 状态dto
     * @return {@link Boolean}
     */
    Boolean toExamineDemand(AuditStatusDTO statusDTO);
}
