package com.git.bds.nyc.convert.product;

import com.git.bds.nyc.controller.vo.PrimaryProductInfoVO;
import com.git.bds.nyc.controller.vo.PrimaryProductVO;
import com.git.bds.nyc.product.model.domain.PrimaryProduct;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-07T12:01:31+0800",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_311 (Oracle Corporation)"
)
public class ProductCovertImpl implements ProductCovert {

    @Override
    public List<PrimaryProductVO> toPrimaryProductVO(List<PrimaryProduct> list) {
        if ( list == null ) {
            return null;
        }

        List<PrimaryProductVO> list1 = new ArrayList<PrimaryProductVO>( list.size() );
        for ( PrimaryProduct primaryProduct : list ) {
            list1.add( primaryProductToPrimaryProductVO( primaryProduct ) );
        }

        return list1;
    }

    @Override
    public PrimaryProductInfoVO toPrimaryProductInfoVo(PrimaryProduct product) {
        if ( product == null ) {
            return null;
        }

        PrimaryProductInfoVO primaryProductInfoVO = new PrimaryProductInfoVO();

        primaryProductInfoVO.setId( product.getId() );
        primaryProductInfoVO.setContactInfoId( product.getContactInfoId() );
        primaryProductInfoVO.setProductSpecies( product.getProductSpecies() );
        primaryProductInfoVO.setProductVariety( product.getProductVariety() );
        primaryProductInfoVO.setProductWeight( product.getProductWeight() );
        primaryProductInfoVO.setProductPrice( product.getProductPrice() );
        primaryProductInfoVO.setProductProductionArea( product.getProductProductionArea() );
        primaryProductInfoVO.setProductCover( product.getProductCover() );
        primaryProductInfoVO.setProductRemark( product.getProductRemark() );
        primaryProductInfoVO.setProductStatus( product.getProductStatus() );
        primaryProductInfoVO.setCreateTime( product.getCreateTime() );

        return primaryProductInfoVO;
    }

    protected PrimaryProductVO primaryProductToPrimaryProductVO(PrimaryProduct primaryProduct) {
        if ( primaryProduct == null ) {
            return null;
        }

        PrimaryProductVO primaryProductVO = new PrimaryProductVO();

        primaryProductVO.setId( primaryProduct.getId() );
        primaryProductVO.setProductSpecies( primaryProduct.getProductSpecies() );
        primaryProductVO.setProductVariety( primaryProduct.getProductVariety() );
        primaryProductVO.setProductWeight( primaryProduct.getProductWeight() );
        primaryProductVO.setProductPrice( primaryProduct.getProductPrice() );
        primaryProductVO.setProductProductionArea( primaryProduct.getProductProductionArea() );
        primaryProductVO.setProductCover( primaryProduct.getProductCover() );

        return primaryProductVO;
    }
}
