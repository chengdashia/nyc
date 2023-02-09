package com.git.bds.nyc.product.service.primary.corp;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.enums.AuditType;
import com.git.bds.nyc.enums.ProductStatusType;
import com.git.bds.nyc.enums.ProductType;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.framework.file.minio.MinioConfig;
import com.git.bds.nyc.framework.file.minio.MinioUtil;
import com.git.bds.nyc.framework.redis.constant.RedisConstants;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.convert.ProductConvert;
import com.git.bds.nyc.product.mapper.ee.ProductEsMapper;
import com.git.bds.nyc.product.mapper.mp.ProductPictureMapper;
import com.git.bds.nyc.product.mapper.mp.primary.corp.CorpPrimaryProductMapper;
import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.dto.ProductModifyDTO;
import com.git.bds.nyc.product.model.dto.ProductReleaseDTO;
import com.git.bds.nyc.product.service.productpicture.ProductPictureService;
import com.git.bds.nyc.result.ResultCode;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 初级农产品表 服务实现类
 * </p>
 *
 * @author chnnc
 * @since 2022-10-15 18:50:50
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CorpPrimaryProductServiceImpl extends MPJBaseServiceImpl<CorpPrimaryProductMapper, CorpPrimaryProduct> implements CorpPrimaryProductService {

    private final ProductPictureMapper productPictureMapper;

    private final ProductPictureService productPictureService;

    private final ProductEsMapper productEsMapper;

    private final MinioConfig minioConfig;

    private final MinioUtil minioUtil;


    /**
     * 修改产品信息
     *
     * @param productDTO 产品dto
     * @return {@link Boolean}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = RedisConstants.REDIS_PRODUCT_KEY, key = "#productDTO.getId()", condition = "#result == true ")
    public Boolean modifyProductInfo(ProductModifyDTO productDTO) {
        //用户id
        long userId = StpUtil.getLoginIdAsLong();
        //商品新的图片列表
        List<String> productNewImgList = productDTO.getProductImgList();
        //商品id
        Long productId = productDTO.getId();
        //封面
        String coverImg = productNewImgList.get(0);
        CorpPrimaryProduct product = ProductConvert.INSTANCE.toCorpPrimaryProductForUpdate(userId, coverImg, productDTO);
        //更新商品的信息
        int update = this.baseMapper.updateById(product);
        //如果商品表的数据更新成功 则进行下一步
        if(update == 1){
            //如果不是审核 。则需要更新es上的数据
            if(!ProductStatusType.AUDIT.getValue().equals(productDTO.getType())){
                //更新es上的
                productEsMapper.updateById(ProductConvert.INSTANCE.toProductEs(product, ProductType.FARMER_PRIMARY.getValue()));
            }
            return productPictureService.updateProductPicture(productId,productNewImgList);
        }
        return false;
    }


    /**
     * 按页面获取发布产品
     *
     * @param pageParam 页面参数
     * @param type      类型
     * @return {@link PageResult}<{@link ProductReleaseDTO}>
     */
    @Override
    public PageResult<ProductReleaseDTO> getReleaseProductByPage(PageParam pageParam, int type) {
        IPage<ProductReleaseDTO> page = null;
        if(ProductStatusType.ON_SELL.getValue().equals(type)){
            page = this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                    ProductReleaseDTO.class,
                    new MPJLambdaWrapper<CorpPrimaryProduct>()
                            .select(CorpPrimaryProduct::getId,
                                    CorpPrimaryProduct::getProductSpecies,
                                    CorpPrimaryProduct::getProductVariety,
                                    CorpPrimaryProduct::getProductWeight,
                                    CorpPrimaryProduct::getProductPrice,
                                    CorpPrimaryProduct::getProductCover,
                                    CorpPrimaryProduct::getCreateTime
                            )
                            .eq(CorpPrimaryProduct::getProductStatus, type)
                            .eq(CorpPrimaryProduct::getUserId, StpUtil.getLoginIdAsLong())
                            .orderByDesc(CorpPrimaryProduct::getCreateTime));
        }else if(ProductStatusType.PRE_SELL.getValue().equals(type)){
            page = this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                    ProductReleaseDTO.class,
                    new MPJLambdaWrapper<CorpPrimaryProduct>()
                            .select(CorpPrimaryProduct::getId,
                                    CorpPrimaryProduct::getProductSpecies,
                                    CorpPrimaryProduct::getProductVariety,
                                    CorpPrimaryProduct::getProductWeight,
                                    CorpPrimaryProduct::getProductPrice,
                                    CorpPrimaryProduct::getProductCover,
                                    CorpPrimaryProduct::getCreateTime,
                                    CorpPrimaryProduct::getMarketTime
                            )
                            .eq(CorpPrimaryProduct::getProductStatus, type)
                            .eq(CorpPrimaryProduct::getUserId, StpUtil.getLoginIdAsLong())
                            .orderByDesc(CorpPrimaryProduct::getCreateTime));
        }
        if(ObjectUtil.isNotNull(page)){
            return new PageResult<>(page.getRecords(),page.getTotal());
        }
        throw new BusinessException(ResultCode.NOT_EXIST.getCode(),ResultCode.NOT_EXIST.getMessage());
    }

    /**
     * 按页面获取未审核产品
     *
     * @param pageParam 页面参数
     * @return {@link PageResult}<{@link ProductReleaseDTO}>
     */
    @Override
    public PageResult<ProductReleaseDTO> getUnauditedProductByPage(PageParam pageParam) {
        IPage<ProductReleaseDTO> page = this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                ProductReleaseDTO.class,
                new MPJLambdaWrapper<CorpPrimaryProduct>()
                        .select(CorpPrimaryProduct::getId,
                                CorpPrimaryProduct::getProductSpecies,
                                CorpPrimaryProduct::getProductVariety,
                                CorpPrimaryProduct::getProductWeight,
                                CorpPrimaryProduct::getProductPrice,
                                CorpPrimaryProduct::getProductCover,
                                CorpPrimaryProduct::getAuditStatus,
                                CorpPrimaryProduct::getCreateTime
                        )
                        .ne(CorpPrimaryProduct::getAuditStatus, AuditType.PASS.getValue())
                        .eq(CorpPrimaryProduct::getUserId, StpUtil.getLoginIdAsLong())
                        .orderByDesc(FarmerPrimaryProduct::getCreateTime));
        if(ObjectUtil.isNull(page)){
            throw new BusinessException(ResultCode.NOT_EXIST.getCode(),ResultCode.NOT_EXIST.getMessage());
        }
        return new PageResult<>(page.getRecords(),page.getTotal());
    }


}
