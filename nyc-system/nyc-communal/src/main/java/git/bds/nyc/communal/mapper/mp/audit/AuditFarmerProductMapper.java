package git.bds.nyc.communal.mapper.mp.audit;

import git.bds.nyc.communal.model.domain.audit.AuditFarmerProduct;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

;

/**
 * <p>
 * 审核公司发布的农产品(包括初级和加工的商品) Mapper 接口
 * </p>
 *
 * @author 成大事
 * @since 2022-11-23 10:43:48
 */
@Mapper
public interface AuditFarmerProductMapper extends MPJBaseMapper<AuditFarmerProduct> {

}
