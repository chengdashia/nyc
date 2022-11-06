package com.git.bds.nyc.product.convert;

import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import com.git.bds.nyc.product.model.dto.PrimaryProductModifyDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-06T12:07:14+0800",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class PrimaryProductConvertImpl implements PrimaryProductConvert {

    @Override
    public FarmerPrimaryProduct toFarmerPrimaryProduct(Long productId, Long userId, String coverImg, PrimaryProductDTO productDTO) {
        if ( productId == null && userId == null && coverImg == null && productDTO == null ) {
            return null;
        }

        FarmerPrimaryProduct farmerPrimaryProduct = new FarmerPrimaryProduct();

        if ( productDTO != null ) {
            farmerPrimaryProduct.setContactInfoId( productDTO.getContactInfoId() );
            farmerPrimaryProduct.setProductSpecies( productDTO.getProductSpecies() );
            farmerPrimaryProduct.setProductVariety( productDTO.getProductVariety() );
            farmerPrimaryProduct.setProductWeight( productDTO.getProductWeight() );
            farmerPrimaryProduct.setProductPrice( productDTO.getProductPrice() );
            farmerPrimaryProduct.setProductProductionArea( productDTO.getProductProductionArea() );
            farmerPrimaryProduct.setProductRemark( productDTO.getProductRemark() );
            farmerPrimaryProduct.setProductStatus( productDTO.getProductStatus() );
            farmerPrimaryProduct.setMarketTime( productDTO.getMarketTime() );
        }
        farmerPrimaryProduct.setId( productId );
        farmerPrimaryProduct.setUserId( userId );
        farmerPrimaryProduct.setProductCover( coverImg );

        return farmerPrimaryProduct;
    }

    @Override
    public FarmerPrimaryProduct toFarmerPrimaryProductForUpdate(Long userId, String coverImg, PrimaryProductModifyDTO productDTO) {
        if ( userId == null && coverImg == null && productDTO == null ) {
            return null;
        }

        FarmerPrimaryProduct farmerPrimaryProduct = new FarmerPrimaryProduct();

        if ( productDTO != null ) {
            farmerPrimaryProduct.setId( productDTO.getId() );
            farmerPrimaryProduct.setContactInfoId( productDTO.getContactInfoId() );
            farmerPrimaryProduct.setProductSpecies( productDTO.getProductSpecies() );
            farmerPrimaryProduct.setProductVariety( productDTO.getProductVariety() );
            farmerPrimaryProduct.setProductWeight( productDTO.getProductWeight() );
            farmerPrimaryProduct.setProductPrice( productDTO.getProductPrice() );
            farmerPrimaryProduct.setProductProductionArea( productDTO.getProductProductionArea() );
            farmerPrimaryProduct.setProductRemark( productDTO.getProductRemark() );
            farmerPrimaryProduct.setProductStatus( productDTO.getProductStatus() );
            farmerPrimaryProduct.setMarketTime( productDTO.getMarketTime() );
        }
        farmerPrimaryProduct.setUserId( userId );
        farmerPrimaryProduct.setProductCover( coverImg );

        return farmerPrimaryProduct;
    }

    @Override
    public CorpPrimaryProduct toCorpPrimaryProduct(Long productId, Long userId, String coverImg, PrimaryProductDTO productDTO) {
        if ( productId == null && userId == null && coverImg == null && productDTO == null ) {
            return null;
        }

        CorpPrimaryProduct corpPrimaryProduct = new CorpPrimaryProduct();

        if ( productDTO != null ) {
            corpPrimaryProduct.setContactInfoId( productDTO.getContactInfoId() );
            corpPrimaryProduct.setProductSpecies( productDTO.getProductSpecies() );
            corpPrimaryProduct.setProductVariety( productDTO.getProductVariety() );
            corpPrimaryProduct.setProductWeight( productDTO.getProductWeight() );
            corpPrimaryProduct.setProductPrice( productDTO.getProductPrice() );
            corpPrimaryProduct.setProductProductionArea( productDTO.getProductProductionArea() );
            corpPrimaryProduct.setProductRemark( productDTO.getProductRemark() );
            corpPrimaryProduct.setProductStatus( productDTO.getProductStatus() );
            corpPrimaryProduct.setMarketTime( productDTO.getMarketTime() );
        }
        corpPrimaryProduct.setId( productId );
        corpPrimaryProduct.setUserId( userId );
        corpPrimaryProduct.setProductCover( coverImg );

        return corpPrimaryProduct;
    }

    @Override
    public CorpPrimaryProduct toCorpPrimaryProductForUpdate(Long userId, String coverImg, PrimaryProductModifyDTO productDTO) {
        if ( userId == null && coverImg == null && productDTO == null ) {
            return null;
        }

        CorpPrimaryProduct corpPrimaryProduct = new CorpPrimaryProduct();

        if ( productDTO != null ) {
            corpPrimaryProduct.setId( productDTO.getId() );
            corpPrimaryProduct.setContactInfoId( productDTO.getContactInfoId() );
            corpPrimaryProduct.setProductSpecies( productDTO.getProductSpecies() );
            corpPrimaryProduct.setProductVariety( productDTO.getProductVariety() );
            corpPrimaryProduct.setProductWeight( productDTO.getProductWeight() );
            corpPrimaryProduct.setProductPrice( productDTO.getProductPrice() );
            corpPrimaryProduct.setProductProductionArea( productDTO.getProductProductionArea() );
            corpPrimaryProduct.setProductRemark( productDTO.getProductRemark() );
            corpPrimaryProduct.setProductStatus( productDTO.getProductStatus() );
            corpPrimaryProduct.setMarketTime( productDTO.getMarketTime() );
        }
        corpPrimaryProduct.setUserId( userId );
        corpPrimaryProduct.setProductCover( coverImg );

        return corpPrimaryProduct;
    }
}
