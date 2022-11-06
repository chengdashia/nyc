package com.git.bds.nyc.demand.convert;

import com.git.bds.nyc.demand.model.domain.Demand;
import com.git.bds.nyc.demand.model.dto.DemandAddDTO;
import com.git.bds.nyc.demand.model.dto.DemandModifyDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-06T12:07:19+0800",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class DemandCovertImpl implements DemandCovert {

    @Override
    public Demand toDemandForAdd(DemandAddDTO demandAddDTO, Long userId, Long demandId) {
        if ( demandAddDTO == null && userId == null && demandId == null ) {
            return null;
        }

        Demand demand = new Demand();

        if ( demandAddDTO != null ) {
            demand.setDemandSpecies( demandAddDTO.getDemandSpecies() );
            demand.setDemandVariety( demandAddDTO.getDemandVariety() );
            demand.setDemandWeight( demandAddDTO.getDemandWeight() );
            demand.setDemandPrice( demandAddDTO.getDemandPrice() );
            demand.setDetailedAddress( demandAddDTO.getDetailedAddress() );
            demand.setDemandCover( demandAddDTO.getDemandCover() );
            demand.setDemandRemark( demandAddDTO.getDemandRemark() );
            demand.setExpirationDate( demandAddDTO.getExpirationDate() );
        }
        demand.setUserId( userId );
        demand.setId( demandId );

        return demand;
    }

    @Override
    public Demand toDemandForModify(DemandModifyDTO demandModifyDTO, Long userId) {
        if ( demandModifyDTO == null && userId == null ) {
            return null;
        }

        Demand demand = new Demand();

        if ( demandModifyDTO != null ) {
            demand.setId( demandModifyDTO.getId() );
            demand.setDemandSpecies( demandModifyDTO.getDemandSpecies() );
            demand.setDemandVariety( demandModifyDTO.getDemandVariety() );
            demand.setDemandWeight( demandModifyDTO.getDemandWeight() );
            demand.setDemandPrice( demandModifyDTO.getDemandPrice() );
            demand.setDetailedAddress( demandModifyDTO.getDetailedAddress() );
            demand.setDemandCover( demandModifyDTO.getDemandCover() );
            demand.setDemandRemark( demandModifyDTO.getDemandRemark() );
            demand.setExpirationDate( demandModifyDTO.getExpirationDate() );
        }
        demand.setUserId( userId );

        return demand;
    }
}
