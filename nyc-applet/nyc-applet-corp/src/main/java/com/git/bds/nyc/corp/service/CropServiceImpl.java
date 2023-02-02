package com.git.bds.nyc.corp.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.git.bds.nyc.communal.mapper.mp.ContractOrderMapper;
import com.git.bds.nyc.communal.model.domain.ContractOrder;
import com.git.bds.nyc.communal.model.dto.OrderDTO;
import com.git.bds.nyc.communal.service.audit.AuditCorpProductService;
import com.git.bds.nyc.communal.util.Base64Util;
import com.git.bds.nyc.enums.AuditType;
import com.git.bds.nyc.enums.ProductType;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.framework.file.minio.MinioConfig;
import com.git.bds.nyc.framework.file.minio.MinioUtil;
import com.git.bds.nyc.product.convert.ProductConvert;
import com.git.bds.nyc.product.mapper.ee.ProductEsMapper;
import com.git.bds.nyc.product.mapper.mp.CorpProcessingProductMapper;
import com.git.bds.nyc.product.mapper.mp.ProductPictureMapper;
import com.git.bds.nyc.product.mapper.mp.primary.corp.CorpPrimaryProductMapper;
import com.git.bds.nyc.product.mapper.mp.primary.farmer.FarmerPrimaryProductMapper;
import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.domain.CorpProcessingProduct;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.domain.ProductPicture;
import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import com.git.bds.nyc.result.ResultCode;
import com.git.bds.nyc.util.DecimalUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 成大事
 * @since 2022/12/29 20:42
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CropServiceImpl implements CorpService{

    private final AuditCorpProductService auditCorpProductService;

    private final ProductPictureMapper productPictureMapper;

    private final CorpPrimaryProductMapper corpPrimaryProductMapper;

    private final CorpProcessingProductMapper corpProcessingProductMapper;

    private final FarmerPrimaryProductMapper farmerPrimaryProductMapper;

    private final MinioUtil minioUtil;

    private final MinioConfig minioConfig;

    private final ProductEsMapper productEsMapper;

    private final ContractOrderMapper contractOrderMapper;

    /**
     * 发售在售产品
     *
     * @param productDTO 产品dto
     * @return {@link Boolean}
     */
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean releaseOnSellProduct(PrimaryProductDTO productDTO) {
        long userId = StpUtil.getLoginIdAsLong();
        long productId = IdUtil.getSnowflakeNextId();
        //新的图片列表
        List<String> productImgList = productDTO.getProductImgList();
        //封面图
        String coverImg = productImgList.get(0);
        CorpPrimaryProduct product = ProductConvert.INSTANCE.toCorpPrimaryProduct(productId, userId, coverImg,productDTO);
        //插入
        corpPrimaryProductMapper.insert(product);
        // 添加审核
        auditCorpProductService.addAudit(userId,productId);
        for (String img : productImgList) {
            ProductPicture productPicture = new ProductPicture().setProductId(productId).setPictureUrl(img);
            productPictureMapper.insert(productPicture);
        }
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
        long userId = StpUtil.getLoginIdAsLong();
        long productId = IdUtil.getSnowflakeNextId();
        List<String> productImgList = productDTO.getProductImgList();
        String coverImg = productImgList.get(0);
        CorpPrimaryProduct product = ProductConvert.INSTANCE.toCorpPrimaryProduct(productId, userId, coverImg,productDTO);
        corpPrimaryProductMapper.insert(product);
        // 添加审核
        auditCorpProductService.addAudit(userId,productId);
        for (String img : productImgList) {
            ProductPicture productPicture = new ProductPicture().setProductId(productId).setPictureUrl(img);
            productPictureMapper.insert(productPicture);
        }
        log.info("product:  "+product);
        return true;
    }

    /**
     * 按id删除产品
     *
     * @param id   身份证件
     * @param type 类型
     * @return {@link Boolean}
     */
    @Override
    public Boolean deleteProductById(Long id, int type) {
        if (ProductType.CORP_PRIMARY.getValue().equals(type)){
            CorpPrimaryProduct corpPrimaryProduct = corpPrimaryProductMapper.selectOne(new LambdaQueryWrapper<CorpPrimaryProduct>()
                    .select(CorpPrimaryProduct::getAuditStatus));
            if(corpPrimaryProduct == null){
                throw new BusinessException(ResultCode.NOT_EXIST.getCode(),ResultCode.NOT_EXIST.getMessage());
            }else {
                if(AuditType.PASS.getValue().equals(corpPrimaryProduct.getAuditStatus())){
                    return productEsMapper.deleteById(id) > 0 && corpPrimaryProductMapper.deleteById(id) > 0;
                }else {
                    return corpPrimaryProductMapper.deleteById(id) > 0;
                }
            }
        }else {
            CorpProcessingProduct corpProcessingProduct = corpProcessingProductMapper.selectOne(new LambdaQueryWrapper<CorpProcessingProduct>()
                    .select(CorpProcessingProduct::getAuditStatus));
            if(corpProcessingProduct == null){
                throw new BusinessException(ResultCode.NOT_EXIST.getCode(),ResultCode.NOT_EXIST.getMessage());
            }else {
                if(AuditType.PASS.getValue().equals(corpProcessingProduct.getAuditStatus())){
                    return productEsMapper.deleteById(id) > 0 && corpProcessingProductMapper.deleteById(id) > 0;
                }else {
                    return corpProcessingProductMapper.deleteById(id) > 0;
                }
            }
        }


    }

    /**
     * 下单
     *
     * @param orderDTO 订单dto
     * @return {@link Boolean}
     */
    @SneakyThrows
    @Override
    public Boolean placeOrder(OrderDTO orderDTO) {
        //买家id
        long buyerId = StpUtil.getLoginIdAsLong();
        Long productId = orderDTO.getProductId();
        Integer type = orderDTO.getType();
        BigDecimal orderWeight = orderDTO.getOrderWeight();
        BigDecimal unitPrice = null;
        String productSpecies = null;
        String productVariety = null;
        Long sellerContactInfoId = null;
        Long sellerId = null;
        if(ProductType.FARMER_PRIMARY.getValue().equals(type)){
            FarmerPrimaryProduct farmerPrimaryProduct = farmerPrimaryProductMapper.selectOne(new LambdaQueryWrapper<FarmerPrimaryProduct>()
                    .select(
                            FarmerPrimaryProduct::getProductSpecies,
                            FarmerPrimaryProduct::getUserId,
                            FarmerPrimaryProduct::getProductVariety,
                            FarmerPrimaryProduct::getProductPrice,
                            FarmerPrimaryProduct::getProductWeight,
                            FarmerPrimaryProduct::getContactInfoId
                    )
                    .eq(FarmerPrimaryProduct::getId,productId)
            );
            //有数据
            if(farmerPrimaryProduct == null){
                throw new BusinessException(ResultCode.NOT_EXIST.getCode(),ResultCode.NOT_EXIST.getMessage());
            }
            //如果库存量不够
            if(Boolean.TRUE.equals(DecimalUtils.lessThan(farmerPrimaryProduct.getProductWeight(),orderWeight))){
                throw new BusinessException(ResultCode.INSUFFICIENT_STOCK.getCode(),ResultCode.INSUFFICIENT_STOCK.getMessage());
            }
            unitPrice = farmerPrimaryProduct.getProductPrice();
            productSpecies = farmerPrimaryProduct.getProductSpecies();
            productVariety = farmerPrimaryProduct.getProductVariety();
            sellerContactInfoId = farmerPrimaryProduct.getContactInfoId();
            sellerId = farmerPrimaryProduct.getUserId();
        }else if(ProductType.CORP_PRIMARY.getValue().equals(type)){
            CorpPrimaryProduct corpPrimaryProduct = corpPrimaryProductMapper.selectOne(new LambdaQueryWrapper<CorpPrimaryProduct>()
                    .select(
                            CorpPrimaryProduct::getProductSpecies,
                            CorpPrimaryProduct::getUserId,
                            CorpPrimaryProduct::getProductVariety,
                            CorpPrimaryProduct::getProductPrice,
                            CorpPrimaryProduct::getProductWeight
                    )
                    .eq(CorpPrimaryProduct::getId,productId)
            );
            if(ObjectUtil.isNull(corpPrimaryProduct)){
                throw new BusinessException(ResultCode.NOT_EXIST.getCode(),ResultCode.NOT_EXIST.getMessage());
            }
            //如果库存量不够
            if(Boolean.TRUE.equals(DecimalUtils.lessThan(corpPrimaryProduct.getProductWeight(),orderWeight))){
                throw new BusinessException(ResultCode.INSUFFICIENT_STOCK.getCode(),ResultCode.INSUFFICIENT_STOCK.getMessage());
            }
            unitPrice = corpPrimaryProduct.getProductPrice();
            productSpecies = corpPrimaryProduct.getProductSpecies();
            productVariety = corpPrimaryProduct.getProductVariety();
            sellerContactInfoId = corpPrimaryProduct.getContactInfoId();
            sellerId = corpPrimaryProduct.getUserId();
        }else if(ProductType.CORP_PROCESSING.getValue().equals(type)){
            CorpProcessingProduct corpProcessingProduct = corpProcessingProductMapper.selectOne(new LambdaQueryWrapper<CorpProcessingProduct>()
                    .select(
                            CorpProcessingProduct::getProductSpecies,
                            CorpProcessingProduct::getUserId,
                            CorpProcessingProduct::getProductVariety,
                            CorpProcessingProduct::getProductPrice,
                            CorpProcessingProduct::getProductWeight
                    )
                    .eq(CorpProcessingProduct::getId,productId)
            );
            if(corpProcessingProduct == null){
                throw new BusinessException(ResultCode.NOT_EXIST.getCode(),ResultCode.NOT_EXIST.getMessage());
            }
            //如果库存量不够
            if(Boolean.TRUE.equals(DecimalUtils.lessThan(corpProcessingProduct.getProductWeight(),orderWeight))){
                throw new BusinessException(ResultCode.INSUFFICIENT_STOCK.getCode(),ResultCode.INSUFFICIENT_STOCK.getMessage());
            }
            unitPrice = corpProcessingProduct.getProductPrice();
            productSpecies = corpProcessingProduct.getProductSpecies();
            productVariety = corpProcessingProduct.getProductVariety();
            sellerContactInfoId = corpProcessingProduct.getContactInfoId();
            sellerId = corpProcessingProduct.getUserId();
        }
        //获取签名的字节流
        InputStream inputStream = Base64Util.generateImageStream(orderDTO.getBuyerSignature());
        String signPath = minioUtil.uploadSign(minioConfig.getBucketName(), inputStream, buyerId);
        ContractOrder contractOrder = new ContractOrder();
        contractOrder.setSellerId(sellerId);
        contractOrder.setBuyerId(buyerId);
        contractOrder.setProductId(productId);
        contractOrder.setProductSpecies(productSpecies);
        contractOrder.setProductVariety(productVariety);
        contractOrder.setType(type);
        contractOrder.setSellerContactInfoId(sellerContactInfoId);
        contractOrder.setBuyerContactInfoId(orderDTO.getBuyerContactInfoId());
        //买家签名地址
        contractOrder.setBuyerSignature(signPath);
        contractOrder.setUnitPrice(unitPrice);
        contractOrder.setOrderWeight(orderWeight);
        contractOrder.setRemark(orderDTO.getRemark());
        return contractOrderMapper.insert(contractOrder) > 0;
    }
}
