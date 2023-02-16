package com.git.bds.nyc.demand.convert;

import com.git.bds.nyc.demand.model.domain.CorpDemand;
import com.git.bds.nyc.demand.model.dto.DemandAddDTO;
import com.git.bds.nyc.demand.model.dto.DemandModifyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author 成大事
 * @since 2022/11/2 10:44
 */
@Mapper
public interface DemandCovert {

    DemandCovert INSTANCE = Mappers.getMapper(DemandCovert.class);

    /**
     * 添加需求
     *
     * @param demandAddDTO 需求操作数据
     * @param userId           用户id
     * @return {@link CorpDemand}
     */
    @Mapping(source = "userId",target = "userId")
    CorpDemand toDemandForAdd(DemandAddDTO demandAddDTO, Long userId);

    /**
     * 要求修改
     *
     * @param userId          用户id
     * @param demandModifyDTO 需求修改数据
     * @return {@link CorpDemand}
     */
    @Mapping(source = "userId",target = "userId")
    CorpDemand toDemandForModify(DemandModifyDTO demandModifyDTO, Long userId);
}
