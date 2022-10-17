package com.git.bds.nyc.product.service.primary.corp;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.git.bds.nyc.framework.file.minio.MinioConfig;
import com.git.bds.nyc.framework.file.minio.MinioUtil;
import com.git.bds.nyc.product.convert.PrimaryProductConvert;
import com.git.bds.nyc.product.mapper.ProductPictureMapper;
import com.git.bds.nyc.product.mapper.primary.corp.CorpPrimaryProductMapper;
import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.domain.ProductPicture;
import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import com.git.bds.nyc.product.model.dto.PrimaryProductModifyDTO;
import com.github.yulichang.base.MPJBaseServiceImpl;
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
        //新的图片列表
        List<String> productImgList = productDTO.getProductImgList();
        //封面图
        String coverImg = productImgList.get(0);
        CorpPrimaryProduct product = PrimaryProductConvert.INSTANCE.toCorpPrimaryProduct(productId,id, coverImg,productDTO);
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
        CorpPrimaryProduct product = PrimaryProductConvert.INSTANCE.toCorpPrimaryProduct(productId,id, coverImg,productDTO);
        this.baseMapper.insert(product);

        for (String img : productImgList) {
            ProductPicture productPicture = new ProductPicture().setProductId(productId).setPictureUrl(img);
            productPictureMapper.insert(productPicture);
        }
        log.info("product:  "+product);
        return true;
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
        CorpPrimaryProduct product = PrimaryProductConvert.INSTANCE.toCorpPrimaryProductForUpdate(id,coverImg,productDTO);
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


}
