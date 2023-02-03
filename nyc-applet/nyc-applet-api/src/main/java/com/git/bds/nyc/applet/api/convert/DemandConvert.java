package com.git.bds.nyc.applet.api.convert;

import com.git.bds.nyc.applet.api.model.vo.demand.DemandInfoVO;
import com.git.bds.nyc.applet.api.model.vo.demand.DemandVO;
import com.git.bds.nyc.demand.model.dto.DemandDTO;
import com.git.bds.nyc.demand.model.dto.DemandInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 成大事
 * @since 2022/10/31 16:12
 */
@Mapper
public interface DemandConvert {

    DemandConvert INSTANCE = Mappers.getMapper(DemandConvert.class);


    /**
     * 要求vo
     *
     * @param demandDTOList 需求数据列表
     * @return {@link List}<{@link DemandVO}>
     */
    List<DemandVO> toDemandVO(List<DemandDTO> demandDTOList);

    /**
     * 请求信息vo
     *
     * @param demandInfoDTO 需求信息数据
     * @return {@link DemandInfoVO}
     */
    DemandInfoVO toDemandInfoVO(DemandInfoDTO demandInfoDTO);
}
