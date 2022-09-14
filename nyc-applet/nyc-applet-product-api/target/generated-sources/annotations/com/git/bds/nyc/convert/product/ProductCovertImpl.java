package com.git.bds.nyc.convert.product;

import com.git.bds.nyc.controller.vo.PrimaryProductInfoVO;
import com.git.bds.nyc.controller.vo.PrimaryProductVO;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.dto.ProductInfoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-14T22:11:05+0800",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_311 (Oracle Corporation)"
)
public class ProductCovertImpl implements ProductCovert {

    @Override
    public List<PrimaryProductVO> toPrimaryProductVO(List<FarmerPrimaryProduct> list) {
        if ( list == null ) {
            return null;
        }

        List<PrimaryProductVO> list1 = new ArrayList<PrimaryProductVO>( list.size() );
        for ( FarmerPrimaryProduct farmerPrimaryProduct : list ) {
            list1.add( farmerPrimaryProductToPrimaryProductVO( farmerPrimaryProduct ) );
        }

        return list1;
    }

    @Override
    public PrimaryProductInfoVO toPrimaryProductInfoVo(ProductInfoDTO product) {
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
        List<String> list = product.getImgList();
        if ( list != null ) {
            primaryProductInfoVO.setImgList( new ArrayList<String>( list ) );
        }

        return primaryProductInfoVO;
    }

    protected PrimaryProductVO farmerPrimaryProductToPrimaryProductVO(FarmerPrimaryProduct farmerPrimaryProduct) {
        if ( farmerPrimaryProduct == null ) {
            return null;
        }

        PrimaryProductVO primaryProductVO = new PrimaryProductVO();

        primaryProductVO.setId( farmerPrimaryProduct.getId() );
        primaryProductVO.setProductSpecies( farmerPrimaryProduct.getProductSpecies() );
        primaryProductVO.setProductVariety( farmerPrimaryProduct.getProductVariety() );
        primaryProductVO.setProductWeight( farmerPrimaryProduct.getProductWeight() );
        primaryProductVO.setProductPrice( farmerPrimaryProduct.getProductPrice() );
        primaryProductVO.setProductProductionArea( farmerPrimaryProduct.getProductProductionArea() );
        primaryProductVO.setProductCover( farmerPrimaryProduct.getProductCover() );

        return primaryProductVO;
    }
}
