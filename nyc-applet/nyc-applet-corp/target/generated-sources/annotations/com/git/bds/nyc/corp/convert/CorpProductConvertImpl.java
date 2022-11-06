package com.git.bds.nyc.corp.convert;

import com.git.bds.nyc.corp.model.vo.CorpProductCollectionVO;
import com.git.bds.nyc.corp.model.vo.CorpSelfPrimaryProductVO;
import com.git.bds.nyc.product.model.dto.PrimaryProductSelfDTO;
import com.git.bds.nyc.product.model.dto.ProductCollectionDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-06T12:07:22+0800",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class CorpProductConvertImpl implements CorpProductConvert {

    @Override
    public List<CorpSelfPrimaryProductVO> toCorpSelfPrimaryProductVO(List<PrimaryProductSelfDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<CorpSelfPrimaryProductVO> list1 = new ArrayList<CorpSelfPrimaryProductVO>( list.size() );
        for ( PrimaryProductSelfDTO primaryProductSelfDTO : list ) {
            list1.add( primaryProductSelfDTOToCorpSelfPrimaryProductVO( primaryProductSelfDTO ) );
        }

        return list1;
    }

    @Override
    public List<CorpProductCollectionVO> toProductCollectionVO(List<ProductCollectionDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<CorpProductCollectionVO> list1 = new ArrayList<CorpProductCollectionVO>( list.size() );
        for ( ProductCollectionDTO productCollectionDTO : list ) {
            list1.add( productCollectionDTOToCorpProductCollectionVO( productCollectionDTO ) );
        }

        return list1;
    }

    protected CorpSelfPrimaryProductVO primaryProductSelfDTOToCorpSelfPrimaryProductVO(PrimaryProductSelfDTO primaryProductSelfDTO) {
        if ( primaryProductSelfDTO == null ) {
            return null;
        }

        CorpSelfPrimaryProductVO corpSelfPrimaryProductVO = new CorpSelfPrimaryProductVO();

        corpSelfPrimaryProductVO.setId( primaryProductSelfDTO.getId() );
        corpSelfPrimaryProductVO.setProductSpecies( primaryProductSelfDTO.getProductSpecies() );
        corpSelfPrimaryProductVO.setProductVariety( primaryProductSelfDTO.getProductVariety() );
        corpSelfPrimaryProductVO.setProductWeight( primaryProductSelfDTO.getProductWeight() );
        corpSelfPrimaryProductVO.setProductPrice( primaryProductSelfDTO.getProductPrice() );
        corpSelfPrimaryProductVO.setProductCover( primaryProductSelfDTO.getProductCover() );
        corpSelfPrimaryProductVO.setCreateTime( primaryProductSelfDTO.getCreateTime() );

        return corpSelfPrimaryProductVO;
    }

    protected CorpProductCollectionVO productCollectionDTOToCorpProductCollectionVO(ProductCollectionDTO productCollectionDTO) {
        if ( productCollectionDTO == null ) {
            return null;
        }

        CorpProductCollectionVO corpProductCollectionVO = new CorpProductCollectionVO();

        corpProductCollectionVO.setProductId( productCollectionDTO.getProductId() );
        corpProductCollectionVO.setType( productCollectionDTO.getType() );
        corpProductCollectionVO.setPrice( productCollectionDTO.getPrice() );
        corpProductCollectionVO.setSpecies( productCollectionDTO.getSpecies() );
        corpProductCollectionVO.setVarieties( productCollectionDTO.getVarieties() );
        corpProductCollectionVO.setCoverImg( productCollectionDTO.getCoverImg() );
        corpProductCollectionVO.setCollectionTime( productCollectionDTO.getCollectionTime() );

        return corpProductCollectionVO;
    }
}
