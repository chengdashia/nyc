package com.git.bds.nyc.product.service.productpicture;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.git.bds.nyc.framework.file.minio.MinioConfig;
import com.git.bds.nyc.framework.file.minio.MinioUtil;
import com.git.bds.nyc.product.mapper.mp.ProductPictureMapper;
import com.git.bds.nyc.product.model.domain.ProductPicture;
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
 *  服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-09-07 13:55:14
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProductPictureServiceImpl extends MPJBaseServiceImpl<ProductPictureMapper, ProductPicture> implements ProductPictureService {

    private final MinioConfig minioConfig;

    private final MinioUtil minioUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateProductPicture(Long productId,List<String> productNewImgList) {
        //商品原始的图片的列表
        List<ProductPicture> productPictureList = this.baseMapper.selectList(new LambdaQueryWrapper<ProductPicture>()
                .select(ProductPicture::getId, ProductPicture::getPictureUrl)
                .eq(ProductPicture::getProductId, productId));
        List<String> originImgList = productPictureList.stream().map(ProductPicture::getPictureUrl).collect(Collectors.toList());
        //新添加的商品的图片的列表
        List<String> newImgList = productNewImgList.stream().filter(item -> !originImgList.contains(item)).collect(Collectors.toList());
        //需要删除的商品图片的列表
        List<String> delImgList = originImgList.stream().filter(item -> !productNewImgList.contains(item)).collect(Collectors.toList());
        //需要删除的图片的id
        List<Long> delImgIds = productPictureList.stream().filter(item -> delImgList.contains(item.getPictureUrl())).map(ProductPicture::getId).collect(Collectors.toList());
        if(!delImgIds.isEmpty()){
            this.baseMapper.deleteBatchIds(delImgIds);
        }
        //minio 将其删除
        minioUtil.removeFiles(minioConfig.getBucketName(), delImgList);
        //新添加的图片的 将其添加
        for (String img : newImgList) {
            ProductPicture productPicture = new ProductPicture().setProductId(productId).setPictureUrl(img);
            this.baseMapper.insert(productPicture);
        }
        return true;
    }
}
