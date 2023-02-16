package git.bds.nyc.communal.service.audit;


import git.bds.nyc.communal.model.domain.audit.AuditCorpProduct;
import com.github.yulichang.base.MPJBaseService;

/**
 * <p>
 * 审核公司发布的农产品(包括初级和加工的商品) 服务类
 * </p>
 *
 * @author 成大事
 * @since 2022-11-23 10:43:48
 */
public interface AuditCorpProductService extends MPJBaseService<AuditCorpProduct> {


    /**
     * 添加审核
     *
     * @param userId    用户id
     * @param productId 产品id
     * @return {@link Boolean}
     */
    Boolean addAudit(long userId, long productId);
}
