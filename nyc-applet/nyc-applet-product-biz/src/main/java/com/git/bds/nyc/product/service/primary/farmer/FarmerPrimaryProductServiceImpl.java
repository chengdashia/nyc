package com.git.bds.nyc.product.service.primary.farmer;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.framework.file.minio.MinioConfig;
import com.git.bds.nyc.framework.file.minio.MinioUtil;
import com.git.bds.nyc.framework.redis.constant.RedisConstants;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.product.convert.PrimaryProductConvert;
import com.git.bds.nyc.product.dao.primary.farmer.FarmerPrimaryProductDao;
import com.git.bds.nyc.product.dao.productpicture.ProductPictureDao;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.domain.ProductPicture;
import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import com.git.bds.nyc.product.model.dto.ProductInfoDTO;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 初级农产品表 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-09-02 22:02:03
 */
@Slf4j
@Service
public class FarmerPrimaryProductServiceImpl extends MPJBaseServiceImpl<FarmerPrimaryProductDao, FarmerPrimaryProduct> implements FarmerPrimaryProductService {

    @Resource
    private ProductPictureDao productPictureDao;

    @Resource
    private MinioUtil minioUtil;

    @Resource
    private MinioConfig minioConfig;

    /**
     * 主页产品按页面
     *
     * @param pageParam 页面参数
     * @return {@link List}<{@link FarmerPrimaryProduct}>
     */
    @Override
    public List<FarmerPrimaryProduct> homePageProductsByPage(PageParam pageParam) {
        return this.baseMapper.selectPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize(), false),
                new LambdaQueryWrapper<FarmerPrimaryProduct>()
                        .select(FarmerPrimaryProduct::getId
                                , FarmerPrimaryProduct::getProductSpecies
                                , FarmerPrimaryProduct::getProductVariety
                                , FarmerPrimaryProduct::getProductWeight
                                , FarmerPrimaryProduct::getProductPrice
                                , FarmerPrimaryProduct::getProductProductionArea
                                , FarmerPrimaryProduct::getProductCover)
                        .orderByDesc(FarmerPrimaryProduct::getCreateTime)).getRecords();

    }

    /**
     * 其中condition是对入参进行判断，符合条件的缓存，不符合的不缓存。
     * 其中unless是对出参进行判断，符合条件的不缓存，不符合的缓存。
     */
    @Override
    @Cacheable(value = RedisConstants.REDIS_PRODUCT_KEY,key="#id",unless = "#result == null ")
    public ProductInfoDTO getProductInfo(Long id) {
        List<ProductInfoDTO> productInfoDTOList = this.baseMapper.selectJoinList(ProductInfoDTO.class, new MPJLambdaWrapper<>()
                .select(FarmerPrimaryProduct::getId,
                        FarmerPrimaryProduct::getProductCover,
                        FarmerPrimaryProduct::getProductRemark,
                        FarmerPrimaryProduct::getCreateTime,
                        FarmerPrimaryProduct::getProductProductionArea,
                        FarmerPrimaryProduct::getProductSpecies,
                        FarmerPrimaryProduct::getProductWeight,
                        FarmerPrimaryProduct::getProductVariety,
                        FarmerPrimaryProduct::getProductPrice,
                        FarmerPrimaryProduct::getContactInfoId,
                        FarmerPrimaryProduct::getProductStatus)
                .select(ProductPicture::getPictureUrl)
                .leftJoin(ProductPicture.class, ProductPicture::getProductId, FarmerPrimaryProduct::getId)
                .eq(FarmerPrimaryProduct::getId, id));
        List<String> pictureList = productInfoDTOList.stream().map(ProductInfoDTO::getPictureUrl).collect(Collectors.toList());
        ProductInfoDTO productInfoDTO = productInfoDTOList.get(0);
        productInfoDTO.setImgList(pictureList);
        return productInfoDTO;
    }


    /**
     * 发售产品
     *
     * @param productDTO 产品dto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void releaseOnSellProduct(PrimaryProductDTO productDTO) {
        long id = StpUtil.getLoginIdAsLong();
        long productId = IdUtil.getSnowflakeNextId();
        List<String> productImgList = productDTO.getProductImgList();
        String coverImg = productImgList.get(0);
        FarmerPrimaryProduct product = PrimaryProductConvert.INSTANCE.toFarmerPrimaryProduct(productId,id, coverImg,productDTO);
        this.baseMapper.insert(product);

        for (String img : productImgList) {
            ProductPicture productPicture = new ProductPicture().setProductId(productId).setPictureUrl(img);
            productPictureDao.insert(productPicture);
        }
        log.info("product:  "+product);
    }

    /**
     * 发布预售产品
     *
     * @param productDTO 产品dto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void releasePreSellProduct(PrimaryProductDTO productDTO) {
        long id = StpUtil.getLoginIdAsLong();
        long productId = IdUtil.getSnowflakeNextId();
        List<String> productImgList = productDTO.getProductImgList();
        String coverImg = productImgList.get(0);
        FarmerPrimaryProduct product = PrimaryProductConvert.INSTANCE.toFarmerPrimaryProduct(productId,id, coverImg,productDTO);
        this.baseMapper.insert(product);

        for (String img : productImgList) {
            ProductPicture productPicture = new ProductPicture().setProductId(productId).setPictureUrl(img);
            productPictureDao.insert(productPicture);
        }
        log.info("product:  "+product);
    }

    /**
     * del产品按id
     *
     * @param id 身份证件
     * @return {@link Boolean}
     */
    @Override
    @CacheEvict(value = RedisConstants.REDIS_PRODUCT_KEY,key="#id",condition = "#result == true ")
    public Boolean delProductById(Long id) {
        List<ProductPicture> productPictures = productPictureDao.selectList(new LambdaQueryWrapper<ProductPicture>()
                .select(ProductPicture::getPictureUrl,ProductPicture::getId)
                .eq(ProductPicture::getProductId, id));
        List<Long> imgIdList = productPictures.stream().map(ProductPicture::getId).collect(Collectors.toList());
        List<String> imgUrlList = productPictures.stream().map(ProductPicture::getPictureUrl).collect(Collectors.toList());
        minioUtil.removeFiles(minioConfig.getBucketName(),imgUrlList);
        productPictureDao.deleteBatchIds(imgIdList);
        return this.baseMapper.deleteById(id) == 1;
    }
}
