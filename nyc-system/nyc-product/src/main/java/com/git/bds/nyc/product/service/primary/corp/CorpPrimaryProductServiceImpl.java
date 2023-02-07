package com.git.bds.nyc.product.service.primary.corp;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.enums.AuditType;
import com.git.bds.nyc.enums.ProductStatusType;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.framework.file.minio.MinioConfig;
import com.git.bds.nyc.framework.file.minio.MinioUtil;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.convert.ProductConvert;
import com.git.bds.nyc.product.mapper.mp.ProductPictureMapper;
import com.git.bds.nyc.product.mapper.mp.primary.corp.CorpPrimaryProductMapper;
import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.domain.ProductPicture;
import com.git.bds.nyc.product.model.dto.PrimaryProductModifyDTO;
import com.git.bds.nyc.product.model.dto.ProductReleaseDTO;
import com.git.bds.nyc.result.ResultCode;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    private final MinioConfig minioConfig;

    private final MinioUtil minioUtil;


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
        CorpPrimaryProduct product = ProductConvert.INSTANCE.toCorpPrimaryProductForUpdate(id,coverImg,productDTO);
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
