package com.git.bds.nyc.corp.service;

import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;

/**
 * @author 成大事
 * @since 2022/12/29 20:41
 */
public interface CorpService {
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
     * 按id删除产品
     *
     * @param id   身份证件
     * @param type 类型
     * @return {@link Boolean}
     */
    Boolean deleteProductById(Long id, int type);
}
