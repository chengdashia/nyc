package git.bds.nyc.demand.mapper.mp;


import git.bds.nyc.demand.model.domain.DemandHistory;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 需求的浏览记录表 Mapper 接口
 * </p>
 *
 * @author 成大事
 * @since 2022-10-31 15:59:15
 */
@Mapper
public interface DemandHistoryMapper extends MPJBaseMapper<DemandHistory> {

}
