package git.bds.nyc.communal.service.audit.impl;


import git.bds.nyc.communal.mapper.mp.audit.AuditCorpProductMapper;
import git.bds.nyc.communal.model.domain.audit.AuditCorpProduct;
import git.bds.nyc.communal.service.audit.AuditCorpProductService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 审核公司发布的农产品(包括初级和加工的商品) 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-11-23 10:43:48
 */
@Service
public class AuditCorpProductServiceImpl extends MPJBaseServiceImpl<AuditCorpProductMapper, AuditCorpProduct> implements AuditCorpProductService {


    /**
     * 添加审核
     *
     * @param userId    用户id
     * @param productId 产品id
     * @return {@link Boolean}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addAudit(long userId, long productId) {
        AuditCorpProduct auditCorpProduct = new AuditCorpProduct().setUserId(userId).setProductId(productId);
        return this.baseMapper.insert(auditCorpProduct) > 0;
    }
}
