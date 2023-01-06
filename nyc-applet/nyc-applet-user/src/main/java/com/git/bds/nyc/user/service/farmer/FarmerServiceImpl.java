package com.git.bds.nyc.user.service.farmer;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import com.git.bds.nyc.communal.service.audit.CoopAuditProductService;
import com.git.bds.nyc.product.convert.ProductConvert;
import com.git.bds.nyc.product.mapper.mp.primary.farmer.FarmerPrimaryProductMapper;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.domain.ProductPicture;
import com.git.bds.nyc.product.model.dto.PrimaryProductDTO;
import com.git.bds.nyc.product.service.productpicture.ProductPictureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 成大事
 * @since 2022/12/29 21:01
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FarmerServiceImpl implements FarmerService{

    private final FarmerPrimaryProductMapper productMapper;

    private final ProductPictureService productPictureService;

    private final CoopAuditProductService coopAuditProductService;

    /**
     * 发售在售产品
     *
     * @param productDTO 产品dto
     * @return {@link Boolean}
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean releaseOnSellProduct(PrimaryProductDTO productDTO) {
        long userId = StpUtil.getLoginIdAsLong();
        long productId = IdUtil.getSnowflakeNextId();
        List<String> productImgList = productDTO.getProductImgList();
        String coverImg = productImgList.get(0);
        FarmerPrimaryProduct product = ProductConvert.INSTANCE.toFarmerPrimaryProduct(productId, userId, coverImg,productDTO);
        // 插入
        productMapper.insert(product);
        // 添加合作社审核
        coopAuditProductService.addAudit(userId,productId);
        //循环将图片插入
        List<ProductPicture> productPictureList = new ArrayList<>(productImgList.size());
        for (String img : productImgList) {
            ProductPicture productPicture = new ProductPicture().setProductId(productId).setPictureUrl(img);
            productPictureList.add(productPicture);
        }
        productPictureService.saveBatch(productPictureList);
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
        FarmerPrimaryProduct product = ProductConvert.INSTANCE.toFarmerPrimaryProduct(productId, userId, coverImg,productDTO);
        // 插入
        productMapper.insert(product);
        // 添加合作社审核
        coopAuditProductService.addAudit(userId,productId);
        //循环将图片插入
        List<ProductPicture> productPictureList = new ArrayList<>(productImgList.size());
        for (String img : productImgList) {
            ProductPicture productPicture = new ProductPicture().setProductId(productId).setPictureUrl(img);
            productPictureList.add(productPicture);
        }
        productPictureService.saveBatch(productPictureList);
        return true;
    }
}
