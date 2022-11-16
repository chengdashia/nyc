package com.git.bds.nyc.product.convert;

import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import com.git.bds.nyc.product.model.dto.PrimaryProductModifyDTO;
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
    FarmerPrimaryProduct toFarmerPrimaryProduct(Long productId,Long userId,String coverImg,PrimaryProductDTO productDTO);

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
    FarmerPrimaryProduct toFarmerPrimaryProductForUpdate(Long userId, String coverImg, PrimaryProductModifyDTO productDTO);

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
    CorpPrimaryProduct toCorpPrimaryProduct(Long productId, Long userId, String coverImg, PrimaryProductDTO productDTO);


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
    CorpPrimaryProduct toCorpPrimaryProductForUpdate(Long userId, String coverImg, PrimaryProductModifyDTO productDTO);

}
