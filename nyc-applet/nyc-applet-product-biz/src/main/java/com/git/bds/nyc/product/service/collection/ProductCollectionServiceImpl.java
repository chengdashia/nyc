package com.git.bds.nyc.product.service.collection;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.enums.ProductType;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.mapper.mp.ProductCollectionMapper;
import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.domain.CorpProcessingProduct;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.domain.ProductCollection;
import com.git.bds.nyc.product.model.dto.ProductCollectAndHistoryDTO;
import com.git.bds.nyc.result.ResultCode;
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
     * @return {@link PageResult}<{@link ProductCollectAndHistoryDTO}>
     */
    @Override
    public PageResult<ProductCollectAndHistoryDTO> getProductCollectsByPage(PageParam pageParam, int type) {
        long userId = StpUtil.getLoginIdAsLong();
        IPage<ProductCollectAndHistoryDTO> page;
        if(type == ProductType.FARMER_PRIMARY.getValue()){
            page = this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                    ProductCollectAndHistoryDTO.class, new MPJLambdaWrapper<>()
                            .select(ProductCollection::getProductId)
                            .selectAs(ProductCollection::getCreateTime, ProductCollectAndHistoryDTO.COLLECTION_TIME)
                            .selectAs(ProductCollection::getProductType, ProductCollectAndHistoryDTO.TYPE)
                            .selectAs(FarmerPrimaryProduct::getProductPrice, ProductCollectAndHistoryDTO.PRICE)
                            .selectAs(FarmerPrimaryProduct::getProductCover, ProductCollectAndHistoryDTO.COVER_URL)
                            .selectAs(FarmerPrimaryProduct::getProductSpecies, ProductCollectAndHistoryDTO.SPECIES)
                            .selectAs(FarmerPrimaryProduct::getProductVariety, ProductCollectAndHistoryDTO.VARIETIES)
                            .leftJoin(FarmerPrimaryProduct.class, FarmerPrimaryProduct::getId, ProductCollection::getProductId)
                            .eq(ProductCollection::getUserId, userId)
                            .eq(ProductCollection::getProductType, type));
        }else if (type == ProductType.CORP_PRIMARY.getValue()){
            page = this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                    ProductCollectAndHistoryDTO.class, new MPJLambdaWrapper<>()
                            .select(ProductCollection::getProductId)
                            .selectAs(ProductCollection::getCreateTime, ProductCollectAndHistoryDTO.COLLECTION_TIME)
                            .selectAs(ProductCollection::getProductType, ProductCollectAndHistoryDTO.TYPE)
                            .selectAs(CorpPrimaryProduct::getProductPrice, ProductCollectAndHistoryDTO.PRICE)
                            .selectAs(CorpPrimaryProduct::getProductCover, ProductCollectAndHistoryDTO.COVER_URL)
                            .selectAs(CorpPrimaryProduct::getProductSpecies, ProductCollectAndHistoryDTO.SPECIES)
                            .selectAs(CorpPrimaryProduct::getProductVariety, ProductCollectAndHistoryDTO.VARIETIES)
                            .leftJoin(CorpPrimaryProduct.class, CorpPrimaryProduct::getId, ProductCollection::getProductId)
                            .eq(ProductCollection::getUserId, userId)
                            .eq(ProductCollection::getProductType, type));
        }else {
            page = this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                    ProductCollectAndHistoryDTO.class, new MPJLambdaWrapper<>()
                            .select(ProductCollection::getProductId)
                            .selectAs(ProductCollection::getCreateTime, ProductCollectAndHistoryDTO.COLLECTION_TIME)
                            .selectAs(ProductCollection::getProductType, ProductCollectAndHistoryDTO.TYPE)
                            .selectAs(CorpProcessingProduct::getProductPrice, ProductCollectAndHistoryDTO.PRICE)
                            .selectAs(CorpProcessingProduct::getProductCover, ProductCollectAndHistoryDTO.COVER_URL)
                            .selectAs(CorpProcessingProduct::getProductSpecies, ProductCollectAndHistoryDTO.SPECIES)
                            .selectAs(CorpProcessingProduct::getProductVariety, ProductCollectAndHistoryDTO.VARIETIES)
                            .leftJoin(CorpProcessingProduct.class, CorpProcessingProduct::getId, ProductCollection::getProductId)
                            .eq(ProductCollection::getUserId, userId)
                            .eq(ProductCollection::getProductType, type));
        }
        return new PageResult<>(page.getRecords(),page.getSize());
    }

    /**
     * 产品添加收藏
     *
     * @param id   产品id
     * @param type 类型
     * @return {@link Boolean}
     */
    @Override
    public Boolean addProductCollection(Long id, int type) {
        ProductCollection productCollection = this.baseMapper.selectOne(new LambdaQueryWrapper<ProductCollection>()
                .select()
                .eq(ProductCollection::getProductId, id)
                .eq(ProductCollection::getProductType, type));
        if(productCollection == null){
            productCollection = new ProductCollection();
            productCollection.setProductId(id);
            productCollection.setUserId(StpUtil.getLoginIdAsLong());
            productCollection.setProductType(type);
            return this.baseMapper.insert(productCollection) > 0;
        }else {
            throw new BusinessException(ResultCode.COLLECTED.getCode(),ResultCode.COLLECTED.getMessage());
        }
    }
}
