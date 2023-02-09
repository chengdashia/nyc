package com.git.bds.nyc.user.service.farmer;

import com.git.bds.nyc.product.model.dto.ProductDTO;

/**
 * @author 成大事
 * @since 2022/12/29 21:01
 */
public interface FarmerService {

    /**
     * 发布 在售产品
     *
     * @param productDTO 产品dto
     * @return {@link Boolean}
     */
    Boolean releaseOnSellProduct(ProductDTO productDTO);


    /**
     * 发布预售产品
     *
     * @param productDTO 产品dto
     * @return {@link Boolean}
     */
    Boolean releasePreSellProduct(ProductDTO productDTO);
}
