package com.git.bds.nyc.applet.api.convert;

import com.git.bds.nyc.applet.api.model.vo.product.*;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.dto.ProductInfoDTO;
import com.git.bds.nyc.product.model.dto.ProductReleaseDTO;
import com.git.bds.nyc.product.model.es.ProductEs;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 成大事
 * @since 2022/9/5 18:50
 */
@Mapper
public interface ProductConvert {

    ProductConvert INSTANCE = Mappers.getMapper(ProductConvert.class);

    /**
     * 至初级产品vo
     *
     * @param list 列表
     * @return {@link List}<{@link ProductVO}>
     */
    List<ProductVO> toPrimaryProductVO(List<FarmerPrimaryProduct> list);

    /**
     * 到主要产品信息vo
     *
     * @param product 产品
     * @return {@link ProductInfoVO}
     */
    ProductInfoVO toPrimaryProductInfoVO(ProductInfoDTO product);

    /**
     * 到产品es
     *
     * @param product 产品
     * @return {@link List}<{@link ProductEs}>
     */
    List<ProductEs> toProductEs(List<FarmerPrimaryProduct> product);



    /**
     * 农民发布销售初级产品vo
     *
     * @param list 列表
     * @return {@link List}<{@link ReleaseOnSellPrimaryProductVO}>
     */
    List<ReleaseOnSellPrimaryProductVO> toReleaseOnSellPrimaryProductVO(List<ProductReleaseDTO> list);


    /**
     * 向农民发布预售初级产品vo
     *
     * @param list 列表
     * @return {@link List}<{@link ReleasePreSellPrimaryProductVO}>
     */
    List<ReleasePreSellPrimaryProductVO> toReleasePreSellPrimaryProductVO(List<ProductReleaseDTO> list);


    /**
     * 向农民审计预售初级产品vo
     *
     * @param list 列表
     * @return {@link List}<{@link AuditPrimaryProductVO}>
     */
    List<AuditPrimaryProductVO> toAuditPreSellPrimaryProductVO(List<ProductReleaseDTO> list);


}
