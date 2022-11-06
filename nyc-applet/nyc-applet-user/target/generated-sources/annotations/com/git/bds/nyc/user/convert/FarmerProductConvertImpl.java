package com.git.bds.nyc.user.convert;

import com.git.bds.nyc.product.model.dto.PrimaryProductSelfDTO;
import com.git.bds.nyc.product.model.dto.ProductCollectionDTO;
import com.git.bds.nyc.user.domain.FarmerSelfPrimaryProductVO;
import com.git.bds.nyc.user.domain.vo.FarmerProductCollectionVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-06T12:07:16+0800",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class FarmerProductConvertImpl implements FarmerProductConvert {

    @Override
    public List<FarmerSelfPrimaryProductVO> toFarmerSelfPrimaryProductVO(List<PrimaryProductSelfDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<FarmerSelfPrimaryProductVO> list1 = new ArrayList<FarmerSelfPrimaryProductVO>( list.size() );
        for ( PrimaryProductSelfDTO primaryProductSelfDTO : list ) {
            list1.add( primaryProductSelfDTOToFarmerSelfPrimaryProductVO( primaryProductSelfDTO ) );
        }

        return list1;
    }

    @Override
    public List<FarmerProductCollectionVO> toFarmerProductCollectionVO(List<ProductCollectionDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<FarmerProductCollectionVO> list1 = new ArrayList<FarmerProductCollectionVO>( list.size() );
        for ( ProductCollectionDTO productCollectionDTO : list ) {
            list1.add( productCollectionDTOToFarmerProductCollectionVO( productCollectionDTO ) );
        }

        return list1;
    }

    protected FarmerSelfPrimaryProductVO primaryProductSelfDTOToFarmerSelfPrimaryProductVO(PrimaryProductSelfDTO primaryProductSelfDTO) {
        if ( primaryProductSelfDTO == null ) {
            return null;
        }

        FarmerSelfPrimaryProductVO farmerSelfPrimaryProductVO = new FarmerSelfPrimaryProductVO();

        farmerSelfPrimaryProductVO.setId( primaryProductSelfDTO.getId() );
        farmerSelfPrimaryProductVO.setProductSpecies( primaryProductSelfDTO.getProductSpecies() );
        farmerSelfPrimaryProductVO.setProductVariety( primaryProductSelfDTO.getProductVariety() );
        farmerSelfPrimaryProductVO.setProductWeight( primaryProductSelfDTO.getProductWeight() );
        farmerSelfPrimaryProductVO.setProductPrice( primaryProductSelfDTO.getProductPrice() );
        farmerSelfPrimaryProductVO.setProductCover( primaryProductSelfDTO.getProductCover() );
        farmerSelfPrimaryProductVO.setCreateTime( primaryProductSelfDTO.getCreateTime() );

        return farmerSelfPrimaryProductVO;
    }

    protected FarmerProductCollectionVO productCollectionDTOToFarmerProductCollectionVO(ProductCollectionDTO productCollectionDTO) {
        if ( productCollectionDTO == null ) {
            return null;
        }

        FarmerProductCollectionVO farmerProductCollectionVO = new FarmerProductCollectionVO();

        farmerProductCollectionVO.setProductId( productCollectionDTO.getProductId() );
        farmerProductCollectionVO.setType( productCollectionDTO.getType() );
        farmerProductCollectionVO.setPrice( productCollectionDTO.getPrice() );
        farmerProductCollectionVO.setSpecies( productCollectionDTO.getSpecies() );
        farmerProductCollectionVO.setVarieties( productCollectionDTO.getVarieties() );
        farmerProductCollectionVO.setCollectionTime( productCollectionDTO.getCollectionTime() );

        return farmerProductCollectionVO;
    }
}
