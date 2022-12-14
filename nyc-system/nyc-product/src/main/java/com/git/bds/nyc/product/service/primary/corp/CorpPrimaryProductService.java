package com.git.bds.nyc.product.service.primary.corp;


import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import com.git.bds.nyc.product.model.dto.PrimaryProductModifyDTO;
import com.git.bds.nyc.product.model.dto.PrimaryProductSelfDTO;
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
     * 修改产品信息
     *
     * @param productDTO 产品dto
     * @return {@link Boolean}
     */
    Boolean modifyProductInfo(PrimaryProductModifyDTO productDTO);

    /**
     * 逐页销售产品
     *
     * @param pageParam 页面参数
     * @return {@link PageResult}<{@link PrimaryProductSelfDTO}>
     */
    PageResult<PrimaryProductSelfDTO> getOnSellProductByPage(PageParam pageParam);

    /**
     * 逐页销售产品
     *
     * @param pageParam 页面参数
     * @return {@link PageResult}<{@link PrimaryProductSelfDTO}>
     */
    PageResult<PrimaryProductSelfDTO> getPreSellProductByPage(PageParam pageParam);
}
