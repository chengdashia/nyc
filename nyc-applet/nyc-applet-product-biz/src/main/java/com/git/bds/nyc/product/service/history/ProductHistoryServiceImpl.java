package com.git.bds.nyc.product.service.history;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.enums.ProductType;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.mapper.ProductHistoryMapper;
import com.git.bds.nyc.product.model.domain.*;
import com.git.bds.nyc.product.model.dto.ProductCollectionDTO;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品浏览记录表 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-10-26 10:35:30
 */
@Service
public class ProductHistoryServiceImpl extends MPJBaseServiceImpl<ProductHistoryMapper, ProductHistory> implements ProductHistoryService {

    /**
     * 按页面获取产品历史记录
     *
     * @param pageParam 页面参数
     * @param type      类型
     * @return {@link PageResult}<{@link ProductCollectionDTO}>
     */
    @Override
    public PageResult<ProductCollectionDTO> getProductHistoryByPage(PageParam pageParam, int type) {
        long userId = StpUtil.getLoginIdAsLong();
        IPage<ProductCollectionDTO> page;
        if(type == ProductType.FARMER_PRIMARY.getValue()){
            page = this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                    ProductCollectionDTO.class, new MPJLambdaWrapper<>()
                            .select(ProductHistory::getProductId)
                            .selectAs(ProductHistory::getCreateTime,ProductCollectionDTO.COLLECTION_TIME)
                            .selectAs(ProductHistory::getProductType,ProductCollectionDTO.TYPE)
                            .selectAs(FarmerPrimaryProduct::getProductPrice,ProductCollectionDTO.PRICE)
                            .selectAs(FarmerPrimaryProduct::getProductCover,ProductCollectionDTO.COVER_URL)
                            .selectAs(FarmerPrimaryProduct::getProductSpecies,ProductCollectionDTO.SPECIES)
                            .selectAs(FarmerPrimaryProduct::getProductVariety,ProductCollectionDTO.VARIETIES)
                            .leftJoin(FarmerPrimaryProduct.class, FarmerPrimaryProduct::getId, ProductCollection::getProductId)
                            .eq(ProductHistory::getUserId, userId)
                            .eq(ProductHistory::getProductType, type));
        }else if (type == ProductType.CORP_PRIMARY.getValue()){
            page = this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                    ProductCollectionDTO.class, new MPJLambdaWrapper<>()
                            .select(ProductHistory::getProductId)
                            .selectAs(ProductHistory::getCreateTime,ProductCollectionDTO.COLLECTION_TIME)
                            .selectAs(ProductHistory::getProductType,ProductCollectionDTO.TYPE)
                            .selectAs(CorpPrimaryProduct::getProductPrice,ProductCollectionDTO.PRICE)
                            .selectAs(CorpPrimaryProduct::getProductCover,ProductCollectionDTO.COVER_URL)
                            .selectAs(CorpPrimaryProduct::getProductSpecies,ProductCollectionDTO.SPECIES)
                            .selectAs(CorpPrimaryProduct::getProductVariety,ProductCollectionDTO.VARIETIES)
                            .leftJoin(CorpPrimaryProduct.class, CorpPrimaryProduct::getId, ProductCollection::getProductId)
                            .eq(ProductHistory::getUserId, userId)
                            .eq(ProductHistory::getProductType, type));
        }else {
            page = this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                    ProductCollectionDTO.class, new MPJLambdaWrapper<>()
                            .select(ProductHistory::getProductId)
                            .selectAs(ProductHistory::getCreateTime,ProductCollectionDTO.COLLECTION_TIME)
                            .selectAs(ProductHistory::getProductType,ProductCollectionDTO.TYPE)
                            .selectAs(CorpProcessingProduct::getProductPrice,ProductCollectionDTO.PRICE)
                            .selectAs(CorpProcessingProduct::getProductCover,ProductCollectionDTO.COVER_URL)
                            .selectAs(CorpProcessingProduct::getProductSpecies,ProductCollectionDTO.SPECIES)
                            .selectAs(CorpProcessingProduct::getProductVariety,ProductCollectionDTO.VARIETIES)
                            .leftJoin(CorpProcessingProduct.class, CorpProcessingProduct::getId, ProductCollection::getProductId)
                            .eq(ProductHistory::getUserId, userId)
                            .eq(ProductHistory::getProductType, type));
        }
        return new PageResult<>(page.getRecords(),page.getSize());
    }
}
