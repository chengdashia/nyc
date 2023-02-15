package com.git.bds.nyc.communal.service.advertisement;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.communal.mapper.mp.AdvertisementMapper;
import com.git.bds.nyc.communal.model.domain.Advertisement;
import com.git.bds.nyc.enums.AdvertisementType;
import com.git.bds.nyc.framework.file.minio.MinioConfig;
import com.git.bds.nyc.framework.file.minio.MinioUtil;
import com.git.bds.nyc.framework.redis.constant.RedisConstants;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
     * 管理员端分页获取广告
     *
     * @param pageParam 页面参数
     * @return {@link PageResult}<{@link Advertisement}>
     */
    @Override
    public PageResult<Advertisement> getAdvertisementsByPage(PageParam pageParam) {
        Page<Advertisement> page = this.baseMapper.selectPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                new QueryWrapper<Advertisement>()
                        .select(Advertisement.class,i -> !Advertisement.UPDATE_TIME.equals(i.getColumn())));
        log.info(""+page);
        return new PageResult<>(page.getRecords(),page.getTotal());
    }

    /**
     * 小程序端获取广告
     *
     * @return {@link List}<{@link Advertisement}>
     */
    @Override
    @Cacheable(value = RedisConstants.REDIS_ADVERTISEMENT_KEY,unless = "#result == null ")
    public List<Advertisement> getAdvertisements() {
        return this.baseMapper.selectList(new QueryWrapper<Advertisement>()
                .select(Advertisement.class,i -> !Advertisement.UPDATE_TIME.equals(i.getColumn()))
                .eq(Advertisement.STATUS, AdvertisementType.ABLE.getValue()));
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
        Advertisement advertisement = new Advertisement()
                .setPictureUrl(pictureUrl)
                .setTitle(title)
                .setStatus(AdvertisementType.ABLE.getValue());
        return this.baseMapper.insert(advertisement) > 0;
    }

    /**
     * 按id修改广告
     *
     * @param id    广告id
     * @param file  文件
     * @param title 标题
     * @return {@link Boolean}
     * @throws Exception 例外
     */
    @Override
    public Boolean modifyAdvertisementById(Long id,String title,MultipartFile file) throws Exception {
        Advertisement advertisement = this.baseMapper.selectOne(new LambdaQueryWrapper<Advertisement>().eq(Advertisement::getId, id));
        //删除原来的图片
        minioUtil.removeFile(minioConfig.getBucketName(),advertisement.getPictureUrl());
        //更新新的图片
        String pictureUrl = minioUtil.uploadAdvertisementPicture(minioConfig.getBucketName(), file);
        advertisement.setPictureUrl(pictureUrl);
        advertisement.setTitle(title);
        return this.baseMapper.updateById(advertisement) > 0;
    }

    /**
     * 按id删除广告
     *
     * @param id 广告id
     * @return {@link Boolean}
     */
    @Override
    public Boolean delAdvertisementById(Long id) {
        Advertisement advertisement = this.baseMapper.selectOne(new LambdaQueryWrapper<Advertisement>()
                        .select(Advertisement::getPictureUrl)
                .eq(Advertisement::getId, id));
        if(this.baseMapper.deleteById(id) > 0){
            return minioUtil.removeFile(minioConfig.getBucketName(),advertisement.getPictureUrl());
        }
        return false;
    }

    /**
     * 按id修改广告状态
     *
     * @param id     广告id
     * @param status 状态
     * @return {@link Boolean}
     */
    @Override
    public Boolean modifyAdvertisementStatusById(Long id, Integer status) {
        return this.baseMapper.update(null,new LambdaUpdateWrapper<Advertisement>()
                .set(Advertisement::getStatus,status)
                .eq(Advertisement::getId,id)) > 0;
    }


}
