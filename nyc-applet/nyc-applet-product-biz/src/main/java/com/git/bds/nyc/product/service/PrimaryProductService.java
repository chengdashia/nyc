package com.git.bds.nyc.product.service;

import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.product.model.domain.PrimaryProduct;
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
public interface PrimaryProductService extends MPJBaseService<PrimaryProduct> {

    /**
     * 主页产品分页
     *
     * @param pageParam 页面参数
     * @return
     */
    List<PrimaryProduct> homePageProductsByPage(PageParam pageParam);


    /**
     * 获取产品信息
     *
     * @param id 商品id
     * @return {@link PrimaryProduct}
     */
    PrimaryProduct getProductInfo(Long id);
}
