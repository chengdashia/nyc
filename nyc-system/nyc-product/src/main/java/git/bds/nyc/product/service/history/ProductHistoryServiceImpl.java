package git.bds.nyc.product.service.history;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import git.bds.nyc.enums.ProductType;
import git.bds.nyc.page.PageParam;
import git.bds.nyc.page.PageResult;
import git.bds.nyc.product.mapper.mp.ProductHistoryMapper;
import git.bds.nyc.product.model.domain.*;
import git.bds.nyc.product.model.dto.ProductCollectAndHistoryDTO;
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
     * @return {@link PageResult}<{@link ProductCollectAndHistoryDTO}>
     */
    @Override
    public PageResult<ProductCollectAndHistoryDTO> getBrowsingRecordPageByType(PageParam pageParam, int type) {
        long userId = StpUtil.getLoginIdAsLong();
        IPage<ProductCollectAndHistoryDTO> page;
        if(type == ProductType.FARMER_PRIMARY.getValue()){
            page = this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                    ProductCollectAndHistoryDTO.class, new MPJLambdaWrapper<ProductHistory>()
                            .select(ProductHistory::getProductId)
                            .selectAs(ProductHistory::getCreateTime, ProductCollectAndHistoryDTO.CREATE_TIME)
                            .selectAs(ProductHistory::getProductType, ProductCollectAndHistoryDTO.TYPE)
                            .selectAs(FarmerPrimaryProduct::getProductPrice, ProductCollectAndHistoryDTO.PRICE)
                            .selectAs(FarmerPrimaryProduct::getProductCover, ProductCollectAndHistoryDTO.COVER_URL)
                            .selectAs(FarmerPrimaryProduct::getProductProductionArea, ProductCollectAndHistoryDTO.AREA)
                            .selectAs(FarmerPrimaryProduct::getProductWeight, ProductCollectAndHistoryDTO.WEIGHT)
                            .selectAs(FarmerPrimaryProduct::getProductSpecies, ProductCollectAndHistoryDTO.SPECIES)
                            .selectAs(FarmerPrimaryProduct::getProductVariety, ProductCollectAndHistoryDTO.VARIETIES)
                            .leftJoin(FarmerPrimaryProduct.class, FarmerPrimaryProduct::getId, ProductCollection::getProductId)
                            .eq(ProductHistory::getUserId, userId)
                            .eq(ProductHistory::getProductType, type)
                            .orderByDesc(ProductHistory::getCreateTime));
        }else if (type == ProductType.CORP_PRIMARY.getValue()){
            page = this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                    ProductCollectAndHistoryDTO.class, new MPJLambdaWrapper<ProductHistory>()
                            .select(ProductHistory::getProductId)
                            .selectAs(ProductHistory::getCreateTime, ProductCollectAndHistoryDTO.CREATE_TIME)
                            .selectAs(ProductHistory::getProductType, ProductCollectAndHistoryDTO.TYPE)
                            .selectAs(CorpPrimaryProduct::getProductPrice, ProductCollectAndHistoryDTO.PRICE)
                            .selectAs(CorpPrimaryProduct::getProductCover, ProductCollectAndHistoryDTO.COVER_URL)
                            .selectAs(CorpPrimaryProduct::getProductProductionArea, ProductCollectAndHistoryDTO.AREA)
                            .selectAs(CorpPrimaryProduct::getProductWeight, ProductCollectAndHistoryDTO.WEIGHT)
                            .selectAs(CorpPrimaryProduct::getProductSpecies, ProductCollectAndHistoryDTO.SPECIES)
                            .selectAs(CorpPrimaryProduct::getProductVariety, ProductCollectAndHistoryDTO.VARIETIES)
                            .leftJoin(CorpPrimaryProduct.class, CorpPrimaryProduct::getId, ProductCollection::getProductId)
                            .eq(ProductHistory::getUserId, userId)
                            .eq(ProductHistory::getProductType, type)
                            .orderByDesc(ProductHistory::getCreateTime));
        }else {
            page = this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                    ProductCollectAndHistoryDTO.class, new MPJLambdaWrapper<ProductHistory>()
                            .select(ProductHistory::getProductId)
                            .selectAs(ProductHistory::getCreateTime, ProductCollectAndHistoryDTO.CREATE_TIME)
                            .selectAs(ProductHistory::getProductType, ProductCollectAndHistoryDTO.TYPE)
                            .selectAs(CorpProcessingProduct::getProductPrice, ProductCollectAndHistoryDTO.PRICE)
                            .selectAs(CorpProcessingProduct::getProductCover, ProductCollectAndHistoryDTO.COVER_URL)
                            .selectAs(CorpProcessingProduct::getProductProductionArea, ProductCollectAndHistoryDTO.AREA)
                            .selectAs(CorpProcessingProduct::getProductWeight, ProductCollectAndHistoryDTO.WEIGHT)
                            .selectAs(CorpProcessingProduct::getProductSpecies, ProductCollectAndHistoryDTO.SPECIES)
                            .selectAs(CorpProcessingProduct::getProductVariety, ProductCollectAndHistoryDTO.VARIETIES)
                            .leftJoin(CorpProcessingProduct.class, CorpProcessingProduct::getId, ProductCollection::getProductId)
                            .eq(ProductHistory::getUserId, userId)
                            .eq(ProductHistory::getProductType, type)
                            .orderByDesc(ProductHistory::getCreateTime));
        }
        return new PageResult<>(page.getRecords(),page.getSize());
    }

    @Override
    public Boolean addBrowsingHistory(long userId, Long id, int type) {
        ProductHistory one = this.baseMapper.selectOne(new QueryWrapper<ProductHistory>()
                .select(ProductHistory.ID)
                .eq(ProductHistory.PRODUCT_ID, id)
                .eq(ProductHistory.PRODUCT_TYPE, type));
        if(one == null){
            one = new ProductHistory();
            one.setProductId(id);
            one.setUserId(userId);
            one.setProductType(type);
            return this.baseMapper.insert(one) > 0;
        }
        return true;
    }
}
