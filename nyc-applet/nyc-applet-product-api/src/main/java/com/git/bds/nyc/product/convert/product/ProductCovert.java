package com.git.bds.nyc.product.convert.product;

import com.git.bds.nyc.product.controller.vo.PrimaryProductInfoVO;
import com.git.bds.nyc.product.controller.vo.PrimaryProductVO;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.dto.ProductInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 成大事
 * @since 2022/9/5 18:50
 */
@Mapper
public interface ProductCovert {

    ProductCovert INSTANCE = Mappers.getMapper(ProductCovert.class);

    /**
     * 至初级产品vo
     *
     * @param list 列表
     * @return {@link List}<{@link PrimaryProductVO}>
     */
    List<PrimaryProductVO> toPrimaryProductVO(List<FarmerPrimaryProduct> list);

    /**
     * 到主要产品信息vo
     *
     * @param product 产品
     * @return {@link PrimaryProductInfoVO}
     */
    PrimaryProductInfoVO toPrimaryProductInfoVO(ProductInfoDTO product);
}
