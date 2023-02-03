package com.git.bds.nyc.corp.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.git.bds.nyc.communal.mapper.mp.ContractOrderMapper;
import com.git.bds.nyc.communal.service.audit.AuditCorpProductService;
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
import com.git.bds.nyc.product.model.domain.ProductPicture;
import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import com.git.bds.nyc.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
