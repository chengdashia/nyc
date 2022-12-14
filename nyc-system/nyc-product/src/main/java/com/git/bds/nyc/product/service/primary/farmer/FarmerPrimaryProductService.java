package com.git.bds.nyc.product.service.primary.farmer;

import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import com.git.bds.nyc.product.model.dto.PrimaryProductModifyDTO;
import com.git.bds.nyc.product.model.dto.PrimaryProductSelfDTO;
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
     * @return {@link List}<{@link FarmerPrimaryProduct}>
     */
    List<FarmerPrimaryProduct> homePageProductsByPage(PageParam pageParam);


    /**
     * 获取产品信息
     *
     * @param id   商品id
     * @param type
     * @return {@link FarmerPrimaryProduct}
     */
    ProductInfoDTO getProductInfo(Long id, int type);




    /**
     * 根据id删除产品
     *
     * @param id 产品id
     * @return {@link Boolean}
     */
    Boolean delProductById(Long id);

    /**
     * 修改产品信息
     *
     * @param productDTO 产品dto
     * @return {@link Boolean}
     */
    Boolean modifyProductInfo(PrimaryProductModifyDTO productDTO);

    /**
     * 按页面销售产品
     *
     * @param pageParam 页面参数
     * @return {@link PageResult}<{@link PrimaryProductSelfDTO}>
     */
    PageResult<PrimaryProductSelfDTO> getOnSellProductByPage(PageParam pageParam);

    /**
     * 按页面获取预售产品
     *
     * @param pageParam 页面参数
     * @return {@link PageResult}<{@link PrimaryProductSelfDTO}>
     */
    PageResult<PrimaryProductSelfDTO> getPreSellProductByPage(PageParam pageParam);
}
