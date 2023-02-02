package com.git.bds.nyc.product.service;

import com.git.bds.nyc.product.model.dto.ProductInfoDTO;

/**
 * @author 成大事
 * @since 2023/2/2 18:44
 */
public interface ProductService {

    /**
     * 获取产品信息
     *
     * @param id   商品id
     * @param type 类型
     * @return {@link ProductInfoDTO}
     */
    ProductInfoDTO getProductInfo(Long id, int type);

    /**
     * 获取卖家电话
     *
     * @param id   身份证件
     * @param type 类型
     * @return {@link String}
     */
    String getSellerTel(Long id, int type);
}
