package com.git.bds.nyc.demand.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.demand.mapper.DemandMapper;
import com.git.bds.nyc.demand.model.domain.Demand;
import com.git.bds.nyc.demand.model.dto.DemandDTO;
import com.git.bds.nyc.demand.model.dto.DemandInfoDTO;
import com.git.bds.nyc.demand.service.DemandService;
import com.git.bds.nyc.page.PageParam;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<DemandDTO> homePageDemandsByPage(PageParam pageParam) {

        return this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),DemandDTO.class,
                new MPJLambdaWrapper<>()
                        .select(Demand::getId
                                , Demand::getDemandSpecies
                                , Demand::getDemandVariety
                                , Demand::getDemandWeight
                                , Demand::getDemandPrice
                                , Demand::getDetailedAddress
                                , Demand::getDemandCover)
                        .orderByDesc(Demand::getCreateTime)).getRecords();
    }

    @Override
    public DemandInfoDTO getDemandInfo(Long id) {
        return this.baseMapper.selectJoinOne(DemandInfoDTO.class,
                new MPJQueryWrapper<Demand>()
                        .select(Demand.class,i->!i.getColumn().equals(Demand.USER_ID))
                        .eq(Demand.ID,id)
        );
    }
}
