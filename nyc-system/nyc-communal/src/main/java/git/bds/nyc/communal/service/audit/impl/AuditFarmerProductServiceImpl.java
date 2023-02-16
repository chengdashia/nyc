package git.bds.nyc.communal.service.audit.impl;


import git.bds.nyc.communal.mapper.mp.audit.AuditFarmerProductMapper;
import git.bds.nyc.communal.model.domain.audit.AuditFarmerProduct;
import git.bds.nyc.communal.service.audit.AuditFarmerProductService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 审核公司发布的农产品(包括初级和加工的商品) 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-11-23 10:43:48
 */
@Service
public class AuditFarmerProductServiceImpl extends MPJBaseServiceImpl<AuditFarmerProductMapper, AuditFarmerProduct> implements AuditFarmerProductService {

}
