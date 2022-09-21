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

    List<PrimaryProductVO> toPrimaryProductVO(List<FarmerPrimaryProduct> list);

    PrimaryProductInfoVO toPrimaryProductInfoVo(ProductInfoDTO product);
}
