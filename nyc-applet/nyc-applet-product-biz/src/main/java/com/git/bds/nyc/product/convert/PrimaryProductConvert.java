package com.git.bds.nyc.product.convert;

import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author 成大事
 * @since 2022/9/14 21:28
 */
@Mapper
public interface PrimaryProductConvert {

    PrimaryProductConvert INSTANCE = Mappers.getMapper(PrimaryProductConvert.class);


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

}