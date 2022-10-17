package com.git.bds.nyc.product.service.primary.corp;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import com.git.bds.nyc.product.convert.PrimaryProductConvert;
import com.git.bds.nyc.product.mapper.ProductPictureMapper;
import com.git.bds.nyc.product.mapper.primary.corp.CorpPrimaryProductMapper;
import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.domain.ProductPicture;
import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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


}
