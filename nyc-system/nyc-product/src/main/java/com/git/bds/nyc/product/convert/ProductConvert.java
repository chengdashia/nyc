package com.git.bds.nyc.product.convert;

import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.domain.CorpProcessingProduct;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.dto.ProductDTO;
import com.git.bds.nyc.product.model.dto.ProductModifyDTO;
import com.git.bds.nyc.product.model.es.ProductEs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author 成大事
 * @since 2022/9/14 21:28
 */
@Mapper
public interface ProductConvert {

    ProductConvert INSTANCE = Mappers.getMapper(ProductConvert.class);


    /**
     * 农民初级产品
     *
     * @param userId     用户id
     * @param coverImg   封面img
     * @param productDTO 产品dto
     * @return {@link FarmerPrimaryProduct}
     */
    @Mappings({
            @Mapping(source = "userId",target = "userId"),
            @Mapping(source = "coverImg",target = "productCover")
    })
    FarmerPrimaryProduct toFarmerPrimaryProduct(Long userId, String coverImg, ProductDTO productDTO);

    /**
     * 农民初级产品 修改的时候
     * @param userId     用户id
     * @param coverImg   封面img
     * @param productDTO 产品dto
     * @return {@link FarmerPrimaryProduct}
     */
    @Mappings({
            @Mapping(source = "userId",target = "userId"),
            @Mapping(source = "coverImg",target = "productCover")
    })
    FarmerPrimaryProduct toFarmerPrimaryProductForUpdate(Long userId, String coverImg, ProductModifyDTO productDTO);

    /**
     * 公司初级产品
     *
     * @param userId     用户id
     * @param coverImg   封面img
     * @param productDTO 产品dto
     * @return {@link FarmerPrimaryProduct}
     */
    @Mappings({
            @Mapping(source = "userId",target = "userId"),
            @Mapping(source = "coverImg",target = "productCover")
    })
    CorpPrimaryProduct toCorpPrimaryProduct(Long userId, String coverImg, ProductDTO productDTO);


    /**
     * 公司初级产品 修改的时候
     * @param userId     用户id
     * @param coverImg   封面img
     * @param productDTO 产品dto
     * @return {@link FarmerPrimaryProduct}
     */
    @Mappings({
            @Mapping(source = "userId",target = "userId"),
            @Mapping(source = "coverImg",target = "productCover")
    })
    CorpPrimaryProduct toCorpPrimaryProductForUpdate(Long userId, String coverImg, ProductModifyDTO productDTO);


    /**
     * 公司初级产品
     *
     * @param productId  产品id
     * @param userId     用户id
     * @param coverImg   封面img
     * @param productDTO 产品dto
     * @return {@link FarmerPrimaryProduct}
     */
    @Mappings({
            @Mapping(source = "productId",target = "id"),
            @Mapping(source = "userId",target = "userId"),
            @Mapping(source = "coverImg",target = "productCover")
    })
    CorpProcessingProduct toCorpProcessingProduct(Long productId, Long userId, String coverImg, ProductDTO productDTO);


    /**
     * 公司初级产品 修改的时候
     * @param userId     用户id
     * @param coverImg   封面img
     * @param productDTO 产品dto
     * @return {@link FarmerPrimaryProduct}
     */
    @Mappings({
            @Mapping(source = "userId",target = "userId"),
            @Mapping(source = "coverImg",target = "productCover")
    })
    CorpProcessingProduct toCorpProcessingProductForUpdate(Long userId, String coverImg, ProductModifyDTO productDTO);


    /**
     * 到产品es
     *
     * @param farmerPrimaryProduct 农民初级产品
     * @param type 类型
     * @return {@link ProductEs}
     */
    @Mapping(source = "type",target = "type")
    @Mapping(expression = "java(farmerPrimaryProduct.getProductSpecies() + '-' +farmerPrimaryProduct.getProductVariety())",target = "productName")
    ProductEs toProductEs(FarmerPrimaryProduct farmerPrimaryProduct,int type);

    /**
     * 到产品es
     *
     * @param corpPrimaryProduct 公司初级产品
     * @param type 类型
     * @return {@link ProductEs}
     */
    @Mapping(source = "type",target = "type")
    @Mapping(expression = "java(corpPrimaryProduct.getProductSpecies() + '-' +corpPrimaryProduct.getProductVariety())",target = "productName")
    ProductEs toProductEs(CorpPrimaryProduct corpPrimaryProduct,int type);

    /**
     * 到产品es
     *
     * @param corpProcessingProduct 公司加工产品
     * @param type 类型
     * @return {@link ProductEs}
     */
    @Mapping(source = "type",target = "type")
    @Mapping(expression = "java(corpProcessingProduct.getProductSpecies() + '-' +corpProcessingProduct.getProductVariety())",target = "productName")
    ProductEs toProductEs(CorpProcessingProduct corpProcessingProduct,int type);


}
