package com.git.bds.nyc.product.convert;

import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-15T18:53:40+0800",
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
}
