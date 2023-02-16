package git.bds.nyc.communal.service.advertisement;


import git.bds.nyc.communal.model.domain.Advertisement;
import git.bds.nyc.page.PageParam;
import git.bds.nyc.page.PageResult;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 成大事
 * @since 2023-01-04 15:47:52
 */
public interface AdvertisementService extends MPJBaseService<Advertisement> {

    /**
     * 按页面获取广告
     * 分页获取广告
     *
     * @param pageParam 页面参数
     * @return {@link PageResult}<{@link Advertisement}>
     */
    PageResult<Advertisement> getAdvertisementsByPage(PageParam pageParam);


    /**
     * 发布广告
     *
     * @param title
     * @param file  文件
     * @return {@link Boolean}
     */
    Boolean releaseAdvertisement(String title, MultipartFile file) throws Exception;

    /**
     * 按id修改广告
     *
     * @param id    广告id
     * @param file  文件
     * @param title 标题
     * @return {@link Boolean}
     * @throws Exception 例外
     */
    Boolean modifyAdvertisementById(Long id,String title, MultipartFile file) throws Exception;

    /**
     * 按id删除广告
     *
     * @param id 广告id
     * @return {@link Boolean}
     */
    Boolean delAdvertisementById(Long id);

    /**
     * 按id修改广告状态
     *
     * @param id     广告id
     * @param status 状态
     * @return {@link Boolean}
     */
    Boolean modifyAdvertisementStatusById(Long id, Integer status);

    /**
     * 获取广告
     *
     * @return {@link List}<{@link Advertisement}>
     */
    List<Advertisement> getAdvertisements();
}
