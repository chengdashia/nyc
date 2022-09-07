package com.git.bds.nyc.product.service.primaryproduct;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.framework.redis.constant.RedisConstants;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.product.dao.primaryproduct.PrimaryProductDao;
import com.git.bds.nyc.product.model.domain.PrimaryProduct;
import com.git.bds.nyc.product.model.domain.ProductPicture;
import com.git.bds.nyc.product.model.dto.ProductInfoDTO;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
@Service
public class PrimaryProductServiceImpl extends MPJBaseServiceImpl<PrimaryProductDao, PrimaryProduct> implements PrimaryProductService {

    @Override
    public List<PrimaryProduct> homePageProductsByPage(PageParam pageParam) {
        return this.baseMapper.selectPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize(), false),
                new LambdaQueryWrapper<PrimaryProduct>()
                        .select(PrimaryProduct::getId
                                , PrimaryProduct::getProductSpecies
                                , PrimaryProduct::getProductVariety
                                , PrimaryProduct::getProductWeight
                                , PrimaryProduct::getProductPrice
                                , PrimaryProduct::getProductProductionArea
                                , PrimaryProduct::getProductCover)
                        .orderByDesc(PrimaryProduct::getCreateTime)).getRecords();

    }

    /**
     * 其中condition是对入参进行判断，符合条件的缓存，不符合的不缓存。
     * 其中unless是对出参进行判断，符合条件的不缓存，不符合的缓存。
     */
    @Override
    @Cacheable(value = RedisConstants.REDIS_PRODUCT_KEY,key="#id",unless = "#result == null ")
    public ProductInfoDTO getProductInfo(Long id) {
        List<ProductInfoDTO> productInfoDTOList = this.baseMapper.selectJoinList(ProductInfoDTO.class, new MPJLambdaWrapper<>()
                .select(PrimaryProduct::getId,
                        PrimaryProduct::getProductCover,
                        PrimaryProduct::getProductRemark,
                        PrimaryProduct::getCreateTime,
                        PrimaryProduct::getProductProductionArea,
                        PrimaryProduct::getProductSpecies,
                        PrimaryProduct::getProductWeight,
                        PrimaryProduct::getProductVariety,
                        PrimaryProduct::getProductPrice,
                        PrimaryProduct::getContactInfoId,
                        PrimaryProduct::getProductStatus)
                .select(ProductPicture::getPictureUrl)
                .leftJoin(ProductPicture.class, ProductPicture::getProductId, PrimaryProduct::getId)
                .eq(PrimaryProduct::getId, id));
        List<String> pictureList = productInfoDTOList.stream().map(ProductInfoDTO::getPictureUrl).collect(Collectors.toList());
        ProductInfoDTO productInfoDTO = productInfoDTOList.get(0);
        productInfoDTO.setImgList(pictureList);
        return productInfoDTO;
    }
}
