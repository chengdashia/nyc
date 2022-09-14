package com.git.bds.nyc.product.service.primary.farmer;

import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import com.git.bds.nyc.product.model.dto.ProductInfoDTO;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * <p>
 * 初级农产品表 服务类
 * </p>
 *
 * @author 成大事
 * @since 2022-09-02 22:02:03
 */
public interface FarmerPrimaryProductService extends MPJBaseService<FarmerPrimaryProduct> {

    /**
     * 主页产品分页
     *
     * @param pageParam 页面参数
     * @return
     */
    List<FarmerPrimaryProduct> homePageProductsByPage(PageParam pageParam);


    /**
     * 获取产品信息
     *
     * @param id 商品id
     * @return {@link FarmerPrimaryProduct}
     */
    ProductInfoDTO getProductInfo(Long id);


    /**
     * 发布产品
     *
     * @param productDTO 产品dto
     */
    void releaseProduct(PrimaryProductDTO productDTO);
}