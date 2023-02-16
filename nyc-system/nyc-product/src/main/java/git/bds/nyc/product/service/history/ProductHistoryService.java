package git.bds.nyc.product.service.history;


import git.bds.nyc.page.PageParam;
import git.bds.nyc.page.PageResult;
import git.bds.nyc.product.model.domain.ProductHistory;
import git.bds.nyc.product.model.dto.ProductCollectAndHistoryDTO;
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
     * @return {@link PageResult}<{@link ProductCollectAndHistoryDTO}>
     */
    PageResult<ProductCollectAndHistoryDTO> getBrowsingRecordPageByType(PageParam pageParam, int type);

    /**
     * 添加浏览历史记录
     *
     * @param userId 登录id
     * @param id     商品id
     * @param type   类型
     * @return {@link Boolean}
     */
    Boolean addBrowsingHistory(long userId, Long id, int type);
}
