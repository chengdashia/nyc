package git.bds.nyc.product.service.processing;


import cn.dev33.satoken.stp.StpUtil;
import git.bds.nyc.enums.ProductStatusType;
import git.bds.nyc.enums.ProductType;
import git.bds.nyc.framework.redis.constant.RedisConstants;
import git.bds.nyc.product.convert.ProductConvert;
import git.bds.nyc.product.mapper.ee.ProductEsMapper;
import git.bds.nyc.product.mapper.mp.CorpProcessingProductMapper;
import git.bds.nyc.product.model.domain.CorpProcessingProduct;
import git.bds.nyc.product.model.dto.ProductModifyDTO;
import git.bds.nyc.product.service.productpicture.ProductPictureService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 加工产品表 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-10-26 11:43:50
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CorpProcessingProductServiceImpl extends MPJBaseServiceImpl<CorpProcessingProductMapper, CorpProcessingProduct> implements CorpProcessingProductService {


    private final ProductEsMapper productEsMapper;

    private final ProductPictureService productPictureService;
    /**
     * 修改产品信息
     *
     * @param productDTO 产品dto
     * @return {@link Boolean}
     */
    @Override
    @Transactional
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
        CorpProcessingProduct product = ProductConvert.INSTANCE.toCorpProcessingProductForUpdate(userId, coverImg, productDTO);
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
}
