package git.bds.nyc.communal.mapper.mp.audit;


import git.bds.nyc.communal.model.domain.audit.CoopAuditDemand;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 合作社审核个人发布的农产品(只有初级农产品) Mapper 接口
 * </p>
 *
 * @author 成大事
 * @since 2023-01-05 14:56:27
 */
@Mapper
public interface CoopAuditDemandMapper extends MPJBaseMapper<CoopAuditDemand> {

}
