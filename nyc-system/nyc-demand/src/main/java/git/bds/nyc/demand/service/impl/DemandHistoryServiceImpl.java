package git.bds.nyc.demand.service.impl;

import git.bds.nyc.demand.mapper.mp.DemandHistoryMapper;
import git.bds.nyc.demand.model.domain.DemandHistory;
import git.bds.nyc.demand.service.DemandHistoryService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 需求的浏览记录表 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-10-31 15:59:15
 */
@Service
public class DemandHistoryServiceImpl extends MPJBaseServiceImpl<DemandHistoryMapper, DemandHistory> implements DemandHistoryService {

}
