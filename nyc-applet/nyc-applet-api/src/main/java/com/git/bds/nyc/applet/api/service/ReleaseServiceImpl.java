package com.git.bds.nyc.applet.api.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.easyes.core.conditions.LambdaEsUpdateWrapper;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.git.bds.nyc.applet.api.model.dto.NumberOfReleaseDTO;
import com.git.bds.nyc.communal.mapper.mp.audit.AuditCorpProductMapper;
import com.git.bds.nyc.communal.mapper.mp.audit.AuditFarmerProductMapper;
import com.git.bds.nyc.communal.mapper.mp.audit.CoopAuditProductMapper;
import com.git.bds.nyc.communal.model.domain.audit.AuditCorpProduct;
import com.git.bds.nyc.communal.model.domain.audit.AuditFarmerProduct;
import com.git.bds.nyc.communal.model.domain.audit.CoopAuditProduct;
import com.git.bds.nyc.enums.AuditType;
import com.git.bds.nyc.enums.ProductStatusType;
import com.git.bds.nyc.enums.ProductType;
import com.git.bds.nyc.enums.RoleType;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.framework.file.minio.MinioConfig;
import com.git.bds.nyc.framework.file.minio.MinioUtil;
import com.git.bds.nyc.framework.redis.constant.RedisConstants;
import com.git.bds.nyc.product.mapper.ee.ProductEsMapper;
import com.git.bds.nyc.product.mapper.mp.CorpProcessingProductMapper;
import com.git.bds.nyc.product.mapper.mp.ProductPictureMapper;
import com.git.bds.nyc.product.mapper.mp.primary.corp.CorpPrimaryProductMapper;
import com.git.bds.nyc.product.mapper.mp.primary.farmer.FarmerPrimaryProductMapper;
import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.domain.CorpProcessingProduct;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.domain.ProductPicture;
import com.git.bds.nyc.product.model.es.ProductEs;
import com.git.bds.nyc.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 成大事
 * @since 2023/2/7 20:43
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReleaseServiceImpl implements ReleaseService {

    private final FarmerPrimaryProductMapper farmerPrimaryProductMapper;

    private final CorpPrimaryProductMapper corpPrimaryProductMapper;

    private final CorpProcessingProductMapper corpProcessingProductMapper;

    private final ProductPictureMapper productPictureMapper;

    private final CoopAuditProductMapper coopAuditProductMapper;

    private final AuditFarmerProductMapper auditFarmerProductMapper;

    private final AuditCorpProductMapper auditCorpProductMapper;

    private final ProductEsMapper productEsMapper;

    private final MinioUtil minioUtil;

    private final MinioConfig minioConfig;


    /**
     * 获取发布数量
     *
     * @return {@link Long}
     */
    @Override
    public NumberOfReleaseDTO getNumberOfReleases(int type) {
        List<String> roleList = StpUtil.getRoleList();
        long userId = StpUtil.getLoginIdAsLong();
        List<Map<String, Object>> maps;
        String productStatus = "product_status";
        String nums = "nums";
        Long auditNum;
        int onSellNum = 0;
        int preSellNum = 0;
        if(ProductType.PRIMARY.getValue().equals(type)){
            //农户
            if(roleList.contains(RoleType.FARMER.getMsg())){
                maps = farmerPrimaryProductMapper.selectMaps(new QueryWrapper<FarmerPrimaryProduct>()
                        .select("count(*) as nums,product_status")
                        .groupBy(productStatus)
                        .eq(FarmerPrimaryProduct.USER_ID, userId));
                auditNum = farmerPrimaryProductMapper.selectCount(new LambdaQueryWrapper<FarmerPrimaryProduct>()
                        .eq(FarmerPrimaryProduct::getUserId, userId)
                        .ne(FarmerPrimaryProduct::getCoopAuditStatus, AuditType.PASS.getValue())
                        .ne(FarmerPrimaryProduct::getAuditStatus, AuditType.PASS.getValue()));
            }else if(roleList.contains(RoleType.COOP.getMsg())){
                maps = corpPrimaryProductMapper.selectMaps(new QueryWrapper<CorpPrimaryProduct>()
                        .select("count(*) as nums,product_status")
                        .groupBy(productStatus)
                        .eq(CorpPrimaryProduct.USER_ID, userId));
                auditNum = corpPrimaryProductMapper.selectCount(new LambdaQueryWrapper<CorpPrimaryProduct>()
                        .eq(CorpPrimaryProduct::getUserId, userId)
                        .ne(CorpPrimaryProduct::getAuditStatus, AuditType.PASS.getValue()));
            }else {
                throw new BusinessException(ResultCode.NOT_ROLE.getCode(),ResultCode.NOT_EXIST.getMessage());
            }
        }else if(ProductType.PROCESSING.getValue().equals(type)){
            maps = corpProcessingProductMapper.selectMaps(new QueryWrapper<CorpProcessingProduct>()
                    .select("count(*) as nums,product_status")
                    .groupBy(productStatus)
                    .eq(CorpProcessingProduct.USER_ID, userId));
            auditNum = corpProcessingProductMapper.selectCount(new LambdaQueryWrapper<CorpProcessingProduct>()
                    .eq(CorpProcessingProduct::getUserId, userId)
                    .ne(CorpProcessingProduct::getAuditStatus, AuditType.PASS.getValue()));
        }else {
            throw new BusinessException(ResultCode.CAPTCHA_ERROR.getCode(), ResultCode.CAPTCHA_ERROR.getMessage());
        }
        if (ObjectUtil.isNull(maps)){
            throw new BusinessException(ResultCode.NOT_EXIST.getCode(), ResultCode.NOT_EXIST.getMessage());
        }
        NumberOfReleaseDTO numberOfReleaseDTO = new NumberOfReleaseDTO();
        log.info(""+maps);
        for (Map<String, Object> map : maps) {
            if(ObjectUtil.equal(map.get("productStatus"),ProductStatusType.ON_SELL.getValue())){
                onSellNum = Integer.parseInt(map.get(nums).toString());
            }
            if(ObjectUtil.equal(map.get("productStatus"),ProductStatusType.PRE_SELL.getValue())){
                preSellNum = Integer.parseInt(map.get(nums).toString());
            }
        }
        numberOfReleaseDTO.setOnSellNum(onSellNum);
        numberOfReleaseDTO.setPreSellNum(preSellNum);
        numberOfReleaseDTO.setAuditNum(auditNum.intValue());
        numberOfReleaseDTO.setTotalNum(onSellNum + preSellNum + auditNum.intValue());
        return numberOfReleaseDTO;
    }

    /**
     * 根据id删除发布的产品
     *
     * @param id     商品的id
     * @param type   类型(0：初级、1:加工)
     * @param status 状态(-1:审核,0:在售,1:预售)
     * @return {@link Boolean}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = RedisConstants.REDIS_PRODUCT_KEY, key = "#id", condition = "#result == true ")
    public Boolean delReleaseProductById(Long id, int type, int status) {
        List<String> roleList = StpUtil.getRoleList();
        long userId = StpUtil.getLoginIdAsLong();
        //商品的图片地址
        List<String> imgList = productPictureMapper.selectList(new LambdaQueryWrapper<ProductPicture>()
                .select(ProductPicture::getPictureUrl)
                .eq(ProductPicture::getProductId, id)).stream().map(ProductPicture::getPictureUrl).collect(Collectors.toList());
        //在minio中删除
        minioUtil.removeFiles(minioConfig.getBucketName(),imgList);
        //如果是初级的产品
        if(ProductType.PRIMARY.getValue().equals(type)){
            //农户删除
            if(roleList.contains(RoleType.FARMER.getMsg())){
                //如果是审核中的商品
                int delete;
                if(ProductStatusType.AUDIT.getValue().equals(status)){
                    //删除合作社审核的
                    coopAuditProductMapper.delete(new LambdaQueryWrapper<CoopAuditProduct>().eq(CoopAuditProduct::getProductId,id));
                    //删除供销社审核的
                    auditFarmerProductMapper.delete(new LambdaQueryWrapper<AuditFarmerProduct>().eq(AuditFarmerProduct::getProductId,id));
                    return farmerPrimaryProductMapper.delete(new LambdaQueryWrapper<FarmerPrimaryProduct>()
                            .eq(FarmerPrimaryProduct::getUserId, userId)
                            .eq(FarmerPrimaryProduct::getId, id)) > 0;
                }else {
                    delete = farmerPrimaryProductMapper.delete(new LambdaQueryWrapper<FarmerPrimaryProduct>()
                            .eq(FarmerPrimaryProduct::getUserId, userId)
                            .eq(FarmerPrimaryProduct::getId, id));
                    if(delete > 0){
                        return productEsMapper.deleteById(id) > 0;
                    }
                    throw new BusinessException();
                }
                //公司删除
            }else if(roleList.contains(RoleType.COOP.getMsg())){
                int delete;
                if(ProductStatusType.AUDIT.getValue().equals(status)){
                    //删除供销社审核的
                    auditCorpProductMapper.delete(new LambdaQueryWrapper<AuditCorpProduct>().eq(AuditCorpProduct::getProductId,id));
                    return corpPrimaryProductMapper.delete(new LambdaQueryWrapper<CorpPrimaryProduct>()
                            .eq(CorpPrimaryProduct::getUserId, userId)
                            .eq(CorpPrimaryProduct::getId, id)) > 0;
                }else {
                    delete =  corpPrimaryProductMapper.delete(new LambdaQueryWrapper<CorpPrimaryProduct>()
                            .eq(CorpPrimaryProduct::getUserId, userId)
                            .eq(CorpPrimaryProduct::getId, id));
                    if(delete > 0){
                        return productEsMapper.deleteById(id) > 0;
                    }
                    throw new BusinessException();
                }
            }else {
                throw new BusinessException(ResultCode.NOT_ROLE.getCode(),ResultCode.NOT_EXIST.getMessage());
            }
            //如果是加工的产品
        }else if (ProductType.PROCESSING.getValue().equals(type)){
            //只能公司删除
           if(roleList.contains(RoleType.COOP.getMsg())){
               int delete;
               if(ProductStatusType.AUDIT.getValue().equals(status)){
                   //删除供销社审核的
                   auditCorpProductMapper.delete(new LambdaQueryWrapper<AuditCorpProduct>().eq(AuditCorpProduct::getProductId,id));
                   return corpProcessingProductMapper.delete(new LambdaQueryWrapper<CorpProcessingProduct>()
                           .eq(CorpProcessingProduct::getUserId, userId)
                           .eq(CorpProcessingProduct::getId, id)) > 0;
               }else {
                   delete = corpProcessingProductMapper.delete(new LambdaQueryWrapper<CorpProcessingProduct>()
                           .eq(CorpProcessingProduct::getUserId, userId)
                           .eq(CorpProcessingProduct::getId, id));
                   if(delete > 0){
                       return productEsMapper.deleteById(id) > 0;
                   }
                   throw new BusinessException();
               }

            }else {
                throw new BusinessException(ResultCode.NOT_ROLE.getCode(),ResultCode.NOT_EXIST.getMessage());
            }
        }else {
            throw new BusinessException(ResultCode.CAPTCHA_ERROR.getCode(), ResultCode.CAPTCHA_ERROR.getMessage());
        }
    }

    /**
     * 按id立即发布产品
     *
     * @param id   身份证件
     * @param type 类型
     * @return {@link Boolean}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean releaseProductNowById(Long id, int type) {
        List<String> roleList = StpUtil.getRoleList();
        long userId = StpUtil.getLoginIdAsLong();
        productEsMapper.update(null,new LambdaEsUpdateWrapper<ProductEs>()
                .set(ProductEs::getMarketTime,LocalDateTime.now())
                .eq(ProductEs::getId,id));
        //如果是初级的
        if(ProductType.PRIMARY.getValue().equals(type)){
            //农户的
            if(roleList.contains(RoleType.FARMER.getMsg())){
                return farmerPrimaryProductMapper.update(null,new LambdaUpdateWrapper<FarmerPrimaryProduct>()
                                .set(FarmerPrimaryProduct::getMarketTime, LocalDateTime.now())
                                .eq(FarmerPrimaryProduct::getId,id)
                        .eq(FarmerPrimaryProduct::getUserId, userId)) > 0;
            }//公司
            else if(roleList.contains(RoleType.COOP.getMsg())){
                return corpPrimaryProductMapper.update(null,new LambdaUpdateWrapper<CorpPrimaryProduct>()
                        .set(CorpPrimaryProduct::getMarketTime, LocalDateTime.now())
                        .eq(CorpPrimaryProduct::getId,id)
                        .eq(CorpPrimaryProduct::getUserId, userId)) > 0;

            }else {
                throw new BusinessException(ResultCode.NOT_ROLE.getCode(),ResultCode.NOT_EXIST.getMessage());
            }
        }else if(ProductType.PROCESSING.getValue().equals(type)){
            if(roleList.contains(RoleType.COOP.getMsg())){
                return corpProcessingProductMapper.update(null,new LambdaUpdateWrapper<CorpProcessingProduct>()
                        .set(CorpProcessingProduct::getMarketTime, LocalDateTime.now())
                        .eq(CorpProcessingProduct::getId,id)
                        .eq(CorpProcessingProduct::getUserId, userId)) > 0;
            }else {
                throw new BusinessException(ResultCode.NOT_ROLE.getCode(),ResultCode.NOT_EXIST.getMessage());
            }
        }else {
            throw new BusinessException(ResultCode.CAPTCHA_ERROR.getCode(), ResultCode.CAPTCHA_ERROR.getMessage());
        }
    }
}
