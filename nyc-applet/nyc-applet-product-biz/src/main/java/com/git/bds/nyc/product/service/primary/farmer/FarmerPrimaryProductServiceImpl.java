package com.git.bds.nyc.product.service.primary.farmer;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.enums.CollectionType;
import com.git.bds.nyc.enums.ProductSellType;
import com.git.bds.nyc.enums.ProductType;
import com.git.bds.nyc.framework.file.minio.MinioConfig;
import com.git.bds.nyc.framework.file.minio.MinioUtil;
import com.git.bds.nyc.framework.redis.constant.RedisConstants;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.convert.PrimaryProductConvert;
import com.git.bds.nyc.product.mapper.ProductCollectionMapper;
import com.git.bds.nyc.product.mapper.ProductPictureMapper;
import com.git.bds.nyc.product.mapper.primary.farmer.FarmerPrimaryProductMapper;
import com.git.bds.nyc.product.model.domain.*;
import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import com.git.bds.nyc.product.model.dto.PrimaryProductModifyDTO;
import com.git.bds.nyc.product.model.dto.PrimaryProductSelfDTO;
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
import java.util.Objects;
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
public class FarmerPrimaryProductServiceImpl extends MPJBaseServiceImpl<FarmerPrimaryProductMapper, FarmerPrimaryProduct> implements FarmerPrimaryProductService {

    @Resource
    private ProductPictureMapper productPictureMapper;

    @Resource
    private ProductCollectionMapper productCollectionMapper;

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
    @Cacheable(value = RedisConstants.REDIS_PRODUCT_KEY,key="#id  + #type",unless = "#result == null ")
    public ProductInfoDTO getProductInfo(Long id, int type) {
        List<ProductInfoDTO> productInfoDTOList ;
        if(Objects.equals(type, ProductType.FARMER_PRIMARY.getValue())){
            productInfoDTOList = this.baseMapper.selectJoinList(ProductInfoDTO.class, new MPJLambdaWrapper<>()
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
        }else if(Objects.equals(type, ProductType.CORP_PRIMARY.getValue())){
            productInfoDTOList = this.baseMapper.selectJoinList(ProductInfoDTO.class, new MPJLambdaWrapper<>()
                    .select(CorpPrimaryProduct::getId,
                            CorpPrimaryProduct::getProductCover,
                            CorpPrimaryProduct::getProductRemark,
                            CorpPrimaryProduct::getCreateTime,
                            CorpPrimaryProduct::getProductProductionArea,
                            CorpPrimaryProduct::getProductSpecies,
                            CorpPrimaryProduct::getProductWeight,
                            CorpPrimaryProduct::getProductVariety,
                            CorpPrimaryProduct::getProductPrice,
                            CorpPrimaryProduct::getContactInfoId,
                            CorpPrimaryProduct::getProductStatus)
                    .select(ProductPicture::getPictureUrl)
                    .leftJoin(ProductPicture.class, ProductPicture::getProductId, CorpPrimaryProduct::getId)
                    .eq(CorpPrimaryProduct::getId, id));
        }else {
            productInfoDTOList = this.baseMapper.selectJoinList(ProductInfoDTO.class, new MPJLambdaWrapper<>()
                    .select(CorpProcessingProduct::getId,
                            CorpProcessingProduct::getProductCover,
                            CorpProcessingProduct::getProductRemark,
                            CorpProcessingProduct::getCreateTime,
                            CorpProcessingProduct::getProductProductionArea,
                            CorpProcessingProduct::getProductSpecies,
                            CorpProcessingProduct::getProductWeight,
                            CorpProcessingProduct::getProductVariety,
                            CorpProcessingProduct::getProductPrice,
                            CorpProcessingProduct::getContactInfoId,
                            CorpProcessingProduct::getProductStatus)
                    .select(ProductPicture::getPictureUrl)
                    .leftJoin(ProductPicture.class, ProductPicture::getProductId, CorpProcessingProduct::getId)
                    .eq(CorpProcessingProduct::getId, id));
        }
        ProductCollection one = productCollectionMapper.selectOne(new QueryWrapper<ProductCollection>()
                .select(ProductCollection.PRODUCT_ID)
                .eq(ProductCollection.PRODUCT_ID, id)
                .eq(ProductCollection.PRODUCT_TYPE, type));
        List<String> pictureList = productInfoDTOList.stream().map(ProductInfoDTO::getPictureUrl).collect(Collectors.toList());
        ProductInfoDTO productInfoDTO = productInfoDTOList.get(0);
        productInfoDTO.setImgList(pictureList);
        if (one == null){
            productInfoDTO.setIsCollection(CollectionType.IS_COLLECTION.getValue());
        }else {
            productInfoDTO.setIsCollection(CollectionType.NOT_COLLECTION.getValue());
        }
        return productInfoDTO;
    }


    /**
     * 发售在售产品
     *
     * @param productDTO 产品dto
     * @return {@link Boolean}
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean releaseOnSellProduct(PrimaryProductDTO productDTO) {
        long id = StpUtil.getLoginIdAsLong();
        long productId = IdUtil.getSnowflakeNextId();
        List<String> productImgList = productDTO.getProductImgList();
        String coverImg = productImgList.get(0);
        FarmerPrimaryProduct product = PrimaryProductConvert.INSTANCE.toFarmerPrimaryProduct(productId,id, coverImg,productDTO);
        this.baseMapper.insert(product);

        for (String img : productImgList) {
            ProductPicture productPicture = new ProductPicture().setProductId(productId).setPictureUrl(img);
            productPictureMapper.insert(productPicture);
        }
        log.info("product:  "+product);
        return true;
    }

    /**
     * 发布预售产品
     *
     * @param productDTO 产品dto
     * @return {@link Boolean}
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean releasePreSellProduct(PrimaryProductDTO productDTO) {
        long id = StpUtil.getLoginIdAsLong();
        long productId = IdUtil.getSnowflakeNextId();
        List<String> productImgList = productDTO.getProductImgList();
        String coverImg = productImgList.get(0);
        FarmerPrimaryProduct product = PrimaryProductConvert.INSTANCE.toFarmerPrimaryProduct(productId,id, coverImg,productDTO);
        this.baseMapper.insert(product);

        for (String img : productImgList) {
            ProductPicture productPicture = new ProductPicture().setProductId(productId).setPictureUrl(img);
            productPictureMapper.insert(productPicture);
        }
        log.info("product:  "+product);
        return true;
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
        List<ProductPicture> productPictures = productPictureMapper.selectList(new LambdaQueryWrapper<ProductPicture>()
                .select(ProductPicture::getPictureUrl,ProductPicture::getId)
                .eq(ProductPicture::getProductId, id));
        List<Long> imgIdList = productPictures.stream().map(ProductPicture::getId).collect(Collectors.toList());
        List<String> imgUrlList = productPictures.stream().map(ProductPicture::getPictureUrl).collect(Collectors.toList());
        minioUtil.removeFiles(minioConfig.getBucketName(),imgUrlList);
        productPictureMapper.deleteBatchIds(imgIdList);
        return this.baseMapper.deleteById(id) == 1;
    }

    /**
     * 修改产品信息
     *
     * @param productDTO 产品dto
     * @return {@link Boolean}
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean modifyProductInfo(PrimaryProductModifyDTO productDTO) {
        //用户id
        long id = StpUtil.getLoginIdAsLong();
        //商品新的图片列表
        List<String> productImgList = productDTO.getProductImgList();
        //商品id
        Long productId = productDTO.getId();
        //封面
        String coverImg = productImgList.get(0);
        FarmerPrimaryProduct product = PrimaryProductConvert.INSTANCE.toFarmerPrimaryProductForUpdate(id,coverImg,productDTO);
        //更新商品的信息
        this.baseMapper.updateById(product);
        //商品原始的图片的列表
        List<ProductPicture> productPictureList = productPictureMapper.selectList(new LambdaQueryWrapper<ProductPicture>()
                .select(ProductPicture::getId,ProductPicture::getPictureUrl)
                .eq(ProductPicture::getProductId, productId));
        List<String> originImgList = productPictureList.stream().map(ProductPicture::getPictureUrl).collect(Collectors.toList());
        //新添加的商品的图片的列表
        List<String> newImgList = productImgList.stream().filter(item -> !originImgList.contains(item)).collect(Collectors.toList());
        //需要删除的商品图片的列表
        List<String> delImgList = originImgList.stream().filter(item -> !productImgList.contains(item)).collect(Collectors.toList());

        //需要删除的图片的id
        List<Long> delImgIds = productPictureList.stream().filter(item -> delImgList.contains(item.getPictureUrl())).map(ProductPicture::getProductId).collect(Collectors.toList());
        productPictureMapper.deleteBatchIds(delImgIds);
        //minio 将其删除
        minioUtil.removeFiles(minioConfig.getBucketName(),delImgList);

        //新添加的图片的 将其添加
        for (String img : newImgList) {
            ProductPicture productPicture = new ProductPicture().setProductId(productId).setPictureUrl(img);
            productPictureMapper.insert(productPicture);
        }
        return true;
    }

    @Override
    public PageResult<PrimaryProductSelfDTO> getOnSellProductByPage(PageParam pageParam) {
        IPage<PrimaryProductSelfDTO> page = getProductByPage(pageParam, ProductSellType.ON_SELL.getValue());
        return new PageResult<>(page.getRecords(),page.getCurrent());
    }

    @Override
    public PageResult<PrimaryProductSelfDTO> getPreSellProductByPage(PageParam pageParam) {
        IPage<PrimaryProductSelfDTO> page = getProductByPage(pageParam, ProductSellType.PRE_SELL.getValue());
        return new PageResult<>(page.getRecords(),page.getCurrent());
    }

    public IPage<PrimaryProductSelfDTO> getProductByPage(PageParam pageParam, int type){
        return this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                PrimaryProductSelfDTO.class,
                new MPJLambdaWrapper<>()
                        .select(FarmerPrimaryProduct::getId,
                                FarmerPrimaryProduct::getProductSpecies,
                                FarmerPrimaryProduct::getProductVariety,
                                FarmerPrimaryProduct::getProductWeight,
                                FarmerPrimaryProduct::getProductPrice,
                                FarmerPrimaryProduct::getProductCover,
                                FarmerPrimaryProduct::getCreateTime
                        )
                        .eq(FarmerPrimaryProduct::getProductStatus, type)
                        .eq(FarmerPrimaryProduct::getUserId, StpUtil.getLoginIdAsLong())
                        .orderByDesc(FarmerPrimaryProduct::getCreateTime));
    }
}
