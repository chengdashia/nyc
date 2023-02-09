package com.git.bds.nyc.product.service.processing;


import com.git.bds.nyc.product.model.domain.CorpProcessingProduct;
import com.git.bds.nyc.product.model.dto.ProductModifyDTO;
import com.github.yulichang.base.MPJBaseService;

/**
 * <p>
 * 加工产品表 服务类
 * </p>
 *
 * @author 成大事
 * @since 2022-10-26 11:43:50
 */
public interface CorpProcessingProductService extends MPJBaseService<CorpProcessingProduct> {

    /**
     * 修改产品信息
     *
     * @param productDTO 产品dto
     * @return {@link Boolean}
     */
    Boolean modifyProductInfo(ProductModifyDTO productDTO);
}
