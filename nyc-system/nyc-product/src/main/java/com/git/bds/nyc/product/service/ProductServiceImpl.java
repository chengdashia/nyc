package com.git.bds.nyc.product.service;

import cn.hutool.core.util.ObjectUtil;
import com.git.bds.nyc.communal.model.domain.Address;
import com.git.bds.nyc.enums.ProductType;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.framework.redis.constant.RedisConstants;
import com.git.bds.nyc.product.mapper.mp.CorpProcessingProductMapper;
import com.git.bds.nyc.product.mapper.mp.primary.corp.CorpPrimaryProductMapper;
import com.git.bds.nyc.product.mapper.mp.primary.farmer.FarmerPrimaryProductMapper;
import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.domain.CorpProcessingProduct;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.domain.ProductPicture;
import com.git.bds.nyc.product.model.dto.ProductInfoDTO;
import com.git.bds.nyc.product.model.dto.PhoneDTO;
import com.git.bds.nyc.result.ResultCode;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 成大事
 * @since 2023/2/2 18:45
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProductServiceImpl implements ProductService{

    private final FarmerPrimaryProductMapper farmerPrimaryProductMapper;

    private final CorpPrimaryProductMapper corpPrimaryProductMapper;

    private final CorpProcessingProductMapper corpProcessingProductMapper;

    /**
     * 获取产品信息
     *
     * @param id   身份证件
     * @param type 类型
     * @return {@link ProductInfoDTO}
     */
    @Override
    @Cacheable(value = RedisConstants.REDIS_PRODUCT_KEY,key="#id  + #type",unless = "#result == null ")
    public ProductInfoDTO getProductInfo(Long id, int type) {
        List<ProductInfoDTO> productInfoDTOList ;
        if(Objects.equals(type, ProductType.FARMER_PRIMARY.getValue())){
            productInfoDTOList = farmerPrimaryProductMapper.selectJoinList(ProductInfoDTO.class, new MPJLambdaWrapper<FarmerPrimaryProduct>()
                    .select(FarmerPrimaryProduct::getId,
                            FarmerPrimaryProduct::getProductCover,
                            FarmerPrimaryProduct::getProductRemark,
                            FarmerPrimaryProduct::getCreateTime,
                            FarmerPrimaryProduct::getMarketTime,
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
            productInfoDTOList = corpPrimaryProductMapper.selectJoinList(ProductInfoDTO.class, new MPJLambdaWrapper<CorpPrimaryProduct>()
                    .select(CorpPrimaryProduct::getId,
                            CorpPrimaryProduct::getProductCover,
                            CorpPrimaryProduct::getProductRemark,
                            CorpPrimaryProduct::getCreateTime,
                            CorpPrimaryProduct::getMarketTime,
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
            productInfoDTOList = corpProcessingProductMapper.selectJoinList(ProductInfoDTO.class, new MPJLambdaWrapper<CorpProcessingProduct>()
                    .select(CorpProcessingProduct::getId,
                            CorpProcessingProduct::getProductCover,
                            CorpProcessingProduct::getProductRemark,
                            CorpProcessingProduct::getCreateTime,
                            CorpProcessingProduct::getMarketTime,
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
        if(productInfoDTOList.isEmpty()){
            throw new BusinessException(ResultCode.NOT_EXIST.getCode(), ResultCode.NOT_EXIST.getMessage());
        }
        List<String> pictureList = productInfoDTOList.stream().map(ProductInfoDTO::getPictureUrl).collect(Collectors.toList());
        ProductInfoDTO productInfoDTO = productInfoDTOList.get(0);
        productInfoDTO.setImgList(pictureList);
        return productInfoDTO;
    }

    /**
     * 获取卖家电话
     *
     * @param id   身份证件
     * @param type 类型
     * @return {@link String}
     */
    @Override
    public String getSellerTel(Long id, int type) {
        PhoneDTO phone;
        if(Objects.equals(type, ProductType.FARMER_PRIMARY.getValue())){
            phone = farmerPrimaryProductMapper.selectJoinOne(PhoneDTO.class, new MPJLambdaWrapper<FarmerPrimaryProduct>()
                    .select(Address::getPhone)
                    .leftJoin(Address.class, Address::getId, FarmerPrimaryProduct::getContactInfoId)
                    .eq(FarmerPrimaryProduct::getId, id));
            if(ObjectUtil.isNull(phone)) {
                throw new BusinessException(ResultCode.NOT_EXIST.getCode(),ResultCode.NOT_EXIST.getMessage());
            }
        }else if(Objects.equals(type, ProductType.CORP_PRIMARY.getValue())){
            phone = corpPrimaryProductMapper.selectJoinOne(PhoneDTO.class, new MPJLambdaWrapper<CorpPrimaryProduct>()
                    .select(Address::getPhone)
                    .leftJoin(Address.class, Address::getId, CorpPrimaryProduct::getContactInfoId)
                    .eq(CorpPrimaryProduct::getId, id));
            if(ObjectUtil.isNull(phone)) {
                throw new BusinessException(ResultCode.NOT_EXIST.getCode(),ResultCode.NOT_EXIST.getMessage());
            }
        }else if(Objects.equals(type, ProductType.CORP_PROCESSING.getValue())){
            phone = corpProcessingProductMapper.selectJoinOne(PhoneDTO.class, new MPJLambdaWrapper<CorpProcessingProduct>()
                    .select(Address::getPhone)
                    .leftJoin(Address.class, Address::getId, CorpProcessingProduct::getContactInfoId)
                    .eq(Address::getId, id));
            if(ObjectUtil.isNull(phone)) {
                throw new BusinessException(ResultCode.NOT_EXIST.getCode(),ResultCode.NOT_EXIST.getMessage());
            }
        }else {
            throw new BusinessException(ResultCode.BAD_REQUEST.getCode(),ResultCode.BAD_REQUEST.getMessage());
        }
        return phone.getPhone();
    }
}
