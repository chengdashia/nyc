package com.git.bds.nyc.product.service.primaryproduct.personal;

import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.product.model.domain.PersonalPrimaryProduct;
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
public interface PersonalPrimaryProductService extends MPJBaseService<PersonalPrimaryProduct> {

    /**
     * 主页产品分页
     *
     * @param pageParam 页面参数
     * @return
     */
    List<PersonalPrimaryProduct> homePageProductsByPage(PageParam pageParam);


    /**
     * 获取产品信息
     *
     * @param id 商品id
     * @return {@link PersonalPrimaryProduct}
     */
    ProductInfoDTO getProductInfo(Long id);
}
