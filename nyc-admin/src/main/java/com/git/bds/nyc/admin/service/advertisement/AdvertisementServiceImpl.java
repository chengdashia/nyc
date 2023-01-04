package com.git.bds.nyc.admin.service.advertisement;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.admin.mapper.mp.AdvertisementMapper;
import com.git.bds.nyc.admin.model.domain.Advertisement;
import com.git.bds.nyc.framework.file.minio.MinioConfig;
import com.git.bds.nyc.framework.file.minio.MinioUtil;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.user.model.dto.UserDTO;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2023-01-04 15:47:52
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AdvertisementServiceImpl extends MPJBaseServiceImpl<AdvertisementMapper, Advertisement> implements AdvertisementService {

    private final MinioConfig minioConfig;

    private final MinioUtil minioUtil;

    /**
     * 分页获取广告
     *
     * @param pageParam 页面参数
     * @return {@link PageResult}<{@link UserDTO}>
     */
    @Override
    public PageResult<Advertisement> getAdvertisementsByPage(PageParam pageParam) {
        Page<Advertisement> page = this.baseMapper.selectPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()), null);
        log.info(""+page);
        return new PageResult<>(page.getRecords(),(long) page.getRecords().size());
    }

    /**
     * 发布广告
     *
     * @param file  文件
     * @param title 标题
     * @return {@link Boolean}
     * @throws Exception 例外
     */
    @Override
    public Boolean releaseAdvertisement(String title, MultipartFile file) throws Exception {
        String pictureUrl = minioUtil.uploadAdvertisementPicture(minioConfig.getBucketName(), file);
        Advertisement advertisement = new Advertisement().setPictureUrl(pictureUrl).setTitle(title);
        return this.baseMapper.insert(advertisement) > 0;
    }

    /**
     * 按id修改广告
     *
     * @param advertisementId 广告id
     * @param file            文件
     * @return {@link Boolean}
     */
    @Override
    public Boolean modifyAdvertisementById(Long advertisementId, MultipartFile file) throws Exception {
        Advertisement advertisement = this.baseMapper.selectOne(new LambdaQueryWrapper<Advertisement>().eq(Advertisement::getId, advertisementId));
        //删除原来的图片
        minioUtil.removeFile(minioConfig.getBucketName(),advertisement.getPictureUrl());
        //更新新的图片
        String pictureUrl = minioUtil.uploadAdvertisementPicture(minioConfig.getBucketName(), file);
        advertisement.setPictureUrl(pictureUrl);
        return this.baseMapper.updateById(advertisement) > 0;
    }

    /**
     * 按id删除广告
     *
     * @param advertisementId 广告id
     * @return {@link Boolean}
     */
    @Override
    public Boolean delAdvertisementById(Long advertisementId) {
        Advertisement advertisement = this.baseMapper.selectOne(new LambdaQueryWrapper<Advertisement>()
                        .select(Advertisement::getPictureUrl)
                .eq(Advertisement::getId, advertisementId));
        if(this.baseMapper.deleteById(advertisementId) > 0){
            return minioUtil.removeFile(minioConfig.getBucketName(),advertisement.getPictureUrl());
        }
        return false;
    }
}
