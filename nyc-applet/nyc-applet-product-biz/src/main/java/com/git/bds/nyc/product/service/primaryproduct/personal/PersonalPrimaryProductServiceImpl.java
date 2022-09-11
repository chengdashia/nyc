package com.git.bds.nyc.product.service.primaryproduct.personal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.framework.redis.constant.RedisConstants;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.product.dao.primaryproduct.personal.PersonalPrimaryProductDao;
import com.git.bds.nyc.product.model.domain.PersonalPrimaryProduct;
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
public class PersonalPrimaryProductServiceImpl extends MPJBaseServiceImpl<PersonalPrimaryProductDao, PersonalPrimaryProduct> implements PersonalPrimaryProductService {

    @Override
    public List<PersonalPrimaryProduct> homePageProductsByPage(PageParam pageParam) {
        return this.baseMapper.selectPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize(), false),
                new LambdaQueryWrapper<PersonalPrimaryProduct>()
                        .select(PersonalPrimaryProduct::getId
                                , PersonalPrimaryProduct::getProductSpecies
                                , PersonalPrimaryProduct::getProductVariety
                                , PersonalPrimaryProduct::getProductWeight
                                , PersonalPrimaryProduct::getProductPrice
                                , PersonalPrimaryProduct::getProductProductionArea
                                , PersonalPrimaryProduct::getProductCover)
                        .orderByDesc(PersonalPrimaryProduct::getCreateTime)).getRecords();

    }

    /**
     * 其中condition是对入参进行判断，符合条件的缓存，不符合的不缓存。
     * 其中unless是对出参进行判断，符合条件的不缓存，不符合的缓存。
     */
    @Override
    @Cacheable(value = RedisConstants.REDIS_PRODUCT_KEY,key="#id",unless = "#result == null ")
    public ProductInfoDTO getProductInfo(Long id) {
        List<ProductInfoDTO> productInfoDTOList = this.baseMapper.selectJoinList(ProductInfoDTO.class, new MPJLambdaWrapper<>()
                .select(PersonalPrimaryProduct::getId,
                        PersonalPrimaryProduct::getProductCover,
                        PersonalPrimaryProduct::getProductRemark,
                        PersonalPrimaryProduct::getCreateTime,
                        PersonalPrimaryProduct::getProductProductionArea,
                        PersonalPrimaryProduct::getProductSpecies,
                        PersonalPrimaryProduct::getProductWeight,
                        PersonalPrimaryProduct::getProductVariety,
                        PersonalPrimaryProduct::getProductPrice,
                        PersonalPrimaryProduct::getContactInfoId,
                        PersonalPrimaryProduct::getProductStatus)
                .select(ProductPicture::getPictureUrl)
                .leftJoin(ProductPicture.class, ProductPicture::getProductId, PersonalPrimaryProduct::getId)
                .eq(PersonalPrimaryProduct::getId, id));
        List<String> pictureList = productInfoDTOList.stream().map(ProductInfoDTO::getPictureUrl).collect(Collectors.toList());
        ProductInfoDTO productInfoDTO = productInfoDTOList.get(0);
        productInfoDTO.setImgList(pictureList);
        return productInfoDTO;
    }
}
