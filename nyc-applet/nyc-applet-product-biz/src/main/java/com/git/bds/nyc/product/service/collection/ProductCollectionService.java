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

    PageResult<ProductCollectionDTO> getProductCollectsByPage(PageParam pageParam, int type);
}
