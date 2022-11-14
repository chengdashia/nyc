package com.git.bds.nyc.product.service.collection;


import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.model.domain.ProductCollection;
import com.git.bds.nyc.product.model.dto.ProductCollectionDTO;
import com.github.yulichang.base.MPJBaseService;

/**
 * <p>
 * 产品收藏表 服务类
 * </p>
 *
 * @author 成大事
 * @since 2022-10-26 10:35:29
 */
public interface ProductCollectionService extends MPJBaseService<ProductCollection> {

    /**
     * 按页面获取产品集合
     *
     * @param pageParam 页面参数
     * @param type      类型
     * @return {@link PageResult}<{@link ProductCollectionDTO}>
     */
    PageResult<ProductCollectionDTO> getProductCollectsByPage(PageParam pageParam, int type);

    /**
     * 产品添加收藏
     *
     * @param id   产品id
     * @param type 类型
     * @return {@link Boolean}
     */
    Boolean addProductCollection(Long id, int type);
}
