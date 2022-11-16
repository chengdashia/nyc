package com.git.bds.nyc.demand.convert;

import com.git.bds.nyc.demand.controller.vo.DemandInfoVO;
import com.git.bds.nyc.demand.controller.vo.DemandVO;
import com.git.bds.nyc.demand.model.dto.DemandDTO;
import com.git.bds.nyc.demand.model.dto.DemandInfoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-16T19:28:12+0800",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_311 (Oracle Corporation)"
)
public class DemandConvertImpl implements DemandConvert {

    @Override
    public List<DemandVO> toDemandVO(List<DemandDTO> demandDTOList) {
        if ( demandDTOList == null ) {
            return null;
        }

        List<DemandVO> list = new ArrayList<DemandVO>( demandDTOList.size() );
        for ( DemandDTO demandDTO : demandDTOList ) {
            list.add( demandDTOToDemandVO( demandDTO ) );
        }

        return list;
    }

    @Override
    public DemandInfoVO toDemandInfoVO(DemandInfoDTO demandInfoDTO) {
        if ( demandInfoDTO == null ) {
            return null;
        }

        DemandInfoVO demandInfoVO = new DemandInfoVO();

        demandInfoVO.setId( demandInfoDTO.getId() );
        demandInfoVO.setDemandSpecies( demandInfoDTO.getDemandSpecies() );
        demandInfoVO.setDemandVariety( demandInfoDTO.getDemandVariety() );
        demandInfoVO.setDemandWeight( demandInfoDTO.getDemandWeight() );
        demandInfoVO.setDemandPrice( demandInfoDTO.getDemandPrice() );
        demandInfoVO.setDetailedAddress( demandInfoDTO.getDetailedAddress() );
        demandInfoVO.setDemandCover( demandInfoDTO.getDemandCover() );
        demandInfoVO.setDemandRemark( demandInfoDTO.getDemandRemark() );
        demandInfoVO.setCreateTime( demandInfoDTO.getCreateTime() );
        demandInfoVO.setExpirationDate( demandInfoDTO.getExpirationDate() );

        return demandInfoVO;
    }

    protected DemandVO demandDTOToDemandVO(DemandDTO demandDTO) {
        if ( demandDTO == null ) {
            return null;
        }

        DemandVO demandVO = new DemandVO();

        demandVO.setId( demandDTO.getId() );
        demandVO.setDemandSpecies( demandDTO.getDemandSpecies() );
        demandVO.setDemandVariety( demandDTO.getDemandVariety() );
        demandVO.setDemandWeight( demandDTO.getDemandWeight() );
        demandVO.setDemandPrice( demandDTO.getDemandPrice() );
        demandVO.setDetailedAddress( demandDTO.getDetailedAddress() );
        demandVO.setDemandCover( demandDTO.getDemandCover() );

        return demandVO;
    }
}
