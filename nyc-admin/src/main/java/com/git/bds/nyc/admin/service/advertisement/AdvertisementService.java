package com.git.bds.nyc.admin.service.advertisement;


import com.git.bds.nyc.admin.model.domain.Advertisement;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.user.model.dto.UserDTO;
import com.github.yulichang.base.MPJBaseService;
import org.springframework.web.multipart.MultipartFile;

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
     * 分页获取广告
     *
     * @param pageParam 页面参数
     * @return {@link PageResult}<{@link UserDTO}>
     */
    PageResult<Advertisement> getAdvertisementsByPage(PageParam pageParam);


    /**
     * 发布广告
     *
     * @param file 文件
     * @return {@link Boolean}
     */
    Boolean releaseAdvertisement(MultipartFile file) throws Exception;

    /**
     * 按id修改广告
     *
     * @param advertisementId 广告id
     * @param file            文件
     * @return {@link Boolean}
     */
    Boolean modifyAdvertisementById(Long advertisementId, MultipartFile file) throws Exception;

    /**
     * 按id删除广告
     *
     * @param advertisementId 广告id
     * @return {@link Boolean}
     */
    Boolean delAdvertisementById(Long advertisementId);
}
