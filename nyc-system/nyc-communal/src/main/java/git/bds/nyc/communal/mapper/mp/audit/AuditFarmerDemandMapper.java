package git.bds.nyc.communal.mapper.mp.audit;


import git.bds.nyc.communal.model.domain.audit.AuditFarmerDemand;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 审核个人发布的需求 Mapper 接口
 * </p>
 *
 * @author 成大事
 * @since 2022-11-23 10:43:48
 */
@Mapper
public interface AuditFarmerDemandMapper extends MPJBaseMapper<AuditFarmerDemand> {

}
