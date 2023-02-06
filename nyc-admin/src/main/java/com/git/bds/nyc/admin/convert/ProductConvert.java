package com.git.bds.nyc.admin.convert;

import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.domain.CorpProcessingProduct;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.es.ProductEs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author 成大事
 * @since 2023/1/6 12:41
 */
@Mapper
public interface ProductConvert {

    ProductConvert INSTANCE = Mappers.getMapper(ProductConvert.class);


    /**
     * 到产品es
     *
     * @param farmerPrimaryProduct 农民初级产品
     * @param type                 类型
     * @return {@link ProductEs}
     */
    @Mapping(source = "type",target = "type")
    @Mapping(expression = "java(farmerPrimaryProduct.getProductSpecies() + '-' +farmerPrimaryProduct.getProductVariety())",target = "productName")
    ProductEs toProductEs(FarmerPrimaryProduct farmerPrimaryProduct,int type);


    /**
     * 到产品es
     *
     * @param type               类型
     * @param corpPrimaryProduct 公司初级产品
     * @return {@link ProductEs}
     */
    @Mapping(source = "type",target = "type")
    ProductEs toProductEs(CorpPrimaryProduct corpPrimaryProduct, int type);


    /**
     * 到产品es
     *
     * @param type                  类型
     * @param corpProcessingProduct 公司加工产品
     * @return {@link ProductEs}
     */
    @Mapping(source = "type",target = "type")
    ProductEs toProductEs(CorpProcessingProduct corpProcessingProduct, int type);
}
