package com.git.bds.nyc.applet.api.convert;

import com.git.bds.nyc.applet.api.model.vo.product.AuditPrimaryProductVO;
import com.git.bds.nyc.applet.api.model.vo.product.ProductInfoVO;
import com.git.bds.nyc.applet.api.model.vo.product.ReleaseOnSellPrimaryProductVO;
import com.git.bds.nyc.applet.api.model.vo.product.ReleasePreSellPrimaryProductVO;
import com.git.bds.nyc.product.model.dto.ProductInfoDTO;
import com.git.bds.nyc.product.model.dto.ProductReleaseDTO;
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
     * 到主要产品信息vo
     *
     * @param product 产品
     * @return {@link ProductInfoVO}
     */
    ProductInfoVO toPrimaryProductInfoVO(ProductInfoDTO product);



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
