package git.bds.nyc.applet.api.service.releasemanage;

import git.bds.nyc.applet.api.model.dto.NumberOfReleaseDTO;
import git.bds.nyc.page.PageParam;
import git.bds.nyc.page.PageResult;
import git.bds.nyc.product.model.dto.ProductAuditDTO;
import git.bds.nyc.product.model.dto.ProductModifyDTO;
import git.bds.nyc.product.model.dto.ProductReleaseDTO;

/**
 * @author 成大事
 * @since 2023/2/7 20:43
 */
public interface ReleaseManageService {

    /**
     * 获取不同类型的发布个数
     *
     * @param type 类型
     * @return {@link NumberOfReleaseDTO}
     */
    NumberOfReleaseDTO getNumberOfReleases(int type);

    /**
     * 按类型获取发布产品 分页
     *
     * @param pageParam 页面参数
     * @param type      类型
     * @return {@link PageResult}<{@link ProductReleaseDTO}>
     */
    PageResult<ProductReleaseDTO> getReleaseProductByPage(PageParam pageParam, int type);

    /**
     * 按页面获取未经审核产品
     *
     * @param pageParam 页面参数
     * @return {@link PageResult}<{@link ProductAuditDTO}>
     */
    PageResult<ProductReleaseDTO> getUnauditedProductByPage(PageParam pageParam);

    /**
     * 根据id删除发布的产品
     *
     * @param id     商品的id
     * @param type   类型(0：初级、1:加工)
     * @param status 状态(-1:审核,0:在售,1:预售)
     * @return {@link Boolean}
     */
    Boolean delReleaseProductById(Long id, int type, int status);

    /**
     * 按id立即发布产品
     *
     * @param id   身份证件
     * @param type 类型
     * @return {@link Boolean}
     */
    Boolean releaseProductNowById(Long id, int type);


    /**
     * 修改产品信息
     *
     * @param productDTO 产品dto
     * @return {@link Boolean}
     */
    Boolean modifyProductInfo(ProductModifyDTO productDTO);
}
