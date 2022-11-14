package com.git.bds.nyc.product.service.history;


import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.model.domain.ProductHistory;
import com.git.bds.nyc.product.model.dto.ProductCollectionDTO;
import com.github.yulichang.base.MPJBaseService;

/**
 * <p>
 * 产品浏览记录表 服务类
 * </p>
 *
 * @author 成大事
 * @since 2022-10-26 10:35:30
 */
public interface ProductHistoryService extends MPJBaseService<ProductHistory> {

    /**
     * 按页面获取产品历史记录
     *
     * @param pageParam 页面参数
     * @param type      类型
     * @return {@link PageResult}<{@link ProductCollectionDTO}>
     */
    PageResult<ProductCollectionDTO> getProductHistoryByPage(PageParam pageParam, int type);
}
