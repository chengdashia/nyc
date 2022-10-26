package com.git.bds.nyc.product.service.collection;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.enums.ProductType;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.mapper.ProductCollectionMapper;
import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.domain.CorpProcessingProduct;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.domain.ProductCollection;
import com.git.bds.nyc.product.model.dto.ProductCollectionDTO;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品收藏表 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-10-26 10:35:29
 */
@Service
public class ProductCollectionServiceImpl extends MPJBaseServiceImpl<ProductCollectionMapper, ProductCollection> implements ProductCollectionService {

    /**
     * 按页面获取产品集合
     *
     * @param pageParam 页面参数
     * @param type      类型
     * @return {@link PageResult}<{@link ProductCollectionDTO}>
     */
    @Override
    public PageResult<ProductCollectionDTO> getProductCollectsByPage(PageParam pageParam, int type) {
        long userId = StpUtil.getLoginIdAsLong();
        IPage<ProductCollectionDTO> page;
        if(type == ProductType.FARMER_PRIMARY.getValue()){
            page = this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                    ProductCollectionDTO.class, new MPJLambdaWrapper<>()
                            .select(ProductCollection::getCreateTime)
                            .select(FarmerPrimaryProduct::getProductPrice,
                                    FarmerPrimaryProduct::getProductCover,
                                    FarmerPrimaryProduct::getProductSpecies,
                                    FarmerPrimaryProduct::getProductVariety)
                            .leftJoin(FarmerPrimaryProduct.class, FarmerPrimaryProduct::getId, ProductCollection::getProductId)
                            .eq(ProductCollection::getUserId, userId)
                            .eq(ProductCollection::getProductStatus, type));
        }else if (type == ProductType.CORP_PRIMARY.getValue()){
            page = this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                    ProductCollectionDTO.class, new MPJLambdaWrapper<>()
                            .select(ProductCollection::getCreateTime)
                            .select(CorpPrimaryProduct::getProductPrice,
                                    CorpPrimaryProduct::getProductCover,
                                    CorpPrimaryProduct::getProductSpecies,
                                    CorpPrimaryProduct::getProductVariety)
                            .leftJoin(CorpPrimaryProduct.class, CorpPrimaryProduct::getId, ProductCollection::getProductId)
                            .eq(ProductCollection::getUserId, userId)
                            .eq(ProductCollection::getProductStatus, type));
        }else {
            page = this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                    ProductCollectionDTO.class, new MPJLambdaWrapper<>()
                            .select(ProductCollection::getCreateTime)
                            .select(CorpProcessingProduct::getProductPrice,
                                    CorpProcessingProduct::getProductCover,
                                    CorpProcessingProduct::getProductSpecies,
                                    CorpProcessingProduct::getProductVariety)
                            .leftJoin(CorpProcessingProduct.class, CorpProcessingProduct::getId, ProductCollection::getProductId)
                            .eq(ProductCollection::getUserId, userId)
                            .eq(ProductCollection::getProductStatus, type));
        }
        return new PageResult<>(page.getRecords(),page.getSize());
    }
}
