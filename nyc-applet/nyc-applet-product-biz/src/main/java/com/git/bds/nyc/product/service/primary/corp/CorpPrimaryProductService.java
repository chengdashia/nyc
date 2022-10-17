package com.git.bds.nyc.product.service.primary.corp;


import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import com.git.bds.nyc.product.model.dto.PrimaryProductModifyDTO;
import com.github.yulichang.base.MPJBaseService;


/**
 * <p>
 * 初级农产品表 服务类
 * </p>
 *
 * @author chnnc
 * @since 2022-10-15 18:50:50
 */
public interface CorpPrimaryProductService extends MPJBaseService<CorpPrimaryProduct> {

    /**
     * 发售产品
     * 发布 在售产品
     *
     * @param productDTO 产品dto
     * @return {@link Boolean}
     */
    Boolean releaseOnSellProduct(PrimaryProductDTO productDTO);


    /**
     * 发布预售产品
     *
     * @param productDTO 产品dto
     * @return {@link Boolean}
     */
    Boolean releasePreSellProduct(PrimaryProductDTO productDTO);

    /**
     * 修改产品信息
     *
     * @param productDTO 产品dto
     * @return {@link Boolean}
     */
    Boolean modifyProductInfo(PrimaryProductModifyDTO productDTO);
}
