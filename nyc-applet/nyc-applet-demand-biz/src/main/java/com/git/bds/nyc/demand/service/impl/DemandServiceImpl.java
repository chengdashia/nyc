package com.git.bds.nyc.demand.service.impl;

import com.git.bds.nyc.demand.model.domain.Demand;
import com.git.bds.nyc.mapper.DemandMapper;
import com.git.bds.nyc.demand.service.DemandService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 需求表 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-10-31 15:59:15
 */
@Service
public class DemandServiceImpl extends MPJBaseServiceImpl<DemandMapper, Demand> implements DemandService {

}
