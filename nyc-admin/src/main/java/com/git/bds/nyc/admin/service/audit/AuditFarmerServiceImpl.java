package com.git.bds.nyc.admin.service.audit;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.admin.convert.ProductConvert;
import com.git.bds.nyc.admin.model.AuditStatusDTO;
import com.git.bds.nyc.communal.mapper.mp.audit.AuditFarmerDemandMapper;
import com.git.bds.nyc.communal.mapper.mp.audit.AuditFarmerProductMapper;
import com.git.bds.nyc.communal.model.domain.audit.AuditFarmerDemand;
import com.git.bds.nyc.communal.model.domain.audit.AuditFarmerProduct;
import com.git.bds.nyc.communal.model.domain.audit.CoopAuditProduct;
import com.git.bds.nyc.communal.model.dto.AuditProductDTO;
import com.git.bds.nyc.demand.mapper.mp.CorpDemandMapper;
import com.git.bds.nyc.enums.AuditType;
import com.git.bds.nyc.enums.ProductType;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.mapper.ee.ProductEsMapper;
import com.git.bds.nyc.product.mapper.mp.primary.farmer.FarmerPrimaryProductMapper;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.es.ProductEs;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 成大事
 * @since 2023/1/5 16:04
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuditFarmerServiceImpl implements AuditFarmerService{

    private final AuditFarmerProductMapper auditFarmerProductMapper;
    private final AuditFarmerDemandMapper auditFarmerDemandMapper;
    private final FarmerPrimaryProductMapper farmerPrimaryProductMapper;
    private final CorpDemandMapper demandMapper;
    private final ProductEsMapper productEsMapper;



    /**
     * 按页面获取挂起审核产品
     *
     * @param pageParam 页面参数
     * @param type      类型
     * @return {@link PageResult}<{@link AuditProductDTO}>
     */
    @Override
    public PageResult<AuditProductDTO> getPendingAuditProductByPage(PageParam pageParam, Integer type) {
        IPage<AuditProductDTO> page = auditFarmerProductMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                AuditProductDTO.class,
                new MPJLambdaWrapper<AuditFarmerProduct>()
                        .select(AuditFarmerProduct::getId, AuditFarmerProduct::getProductId, AuditFarmerProduct::getApplyTimes, AuditFarmerProduct::getCreateTime)
                        .select(FarmerPrimaryProduct::getProductVariety, FarmerPrimaryProduct::getProductSpecies, FarmerPrimaryProduct::getProductCover)
                        .leftJoin(FarmerPrimaryProduct.class, FarmerPrimaryProduct::getId, CoopAuditProduct::getProductId)
                        .eq(AuditFarmerProduct::getAuditStatus, type));
        return new PageResult<>(page.getRecords(),page.getTotal());
    }

    /**
     * 经销社审核农户发布的初级农产品
     *
     * @param statusDTO 状态dto
     * @return {@link Boolean}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean toExamineFarmerPrimaryProduct(AuditStatusDTO statusDTO) {
        auditFarmerProductMapper.update(null,
                new LambdaUpdateWrapper<AuditFarmerProduct>()
                        .set(AuditFarmerProduct::getAuditStatus,statusDTO.getStatus())
                        .set(AuditFarmerProduct::getAuditRemark,statusDTO.getRemark())
                        .eq(AuditFarmerProduct::getId,statusDTO.getId()));
        farmerPrimaryProductMapper.update(null,
                new LambdaUpdateWrapper<FarmerPrimaryProduct>()
                        .set(FarmerPrimaryProduct::getAuditStatus,statusDTO.getStatus())
                        .eq(FarmerPrimaryProduct::getId,statusDTO.getGoodsId()));
        //如果审核通过
        if(AuditType.PASS.getValue().equals(statusDTO.getStatus())){
            FarmerPrimaryProduct farmerPrimaryProduct = farmerPrimaryProductMapper.selectOne(new LambdaQueryWrapper<FarmerPrimaryProduct>()
                    .select(
                            FarmerPrimaryProduct::getId,
                            FarmerPrimaryProduct::getProductSpecies,
                            FarmerPrimaryProduct::getProductVariety,
                            FarmerPrimaryProduct::getProductPrice,
                            FarmerPrimaryProduct::getProductProductionArea,
                            FarmerPrimaryProduct::getProductCover,
                            FarmerPrimaryProduct::getAuditStatus,
                            FarmerPrimaryProduct::getCreateTime,
                            FarmerPrimaryProduct::getMarketTime
                    )
                    .eq(FarmerPrimaryProduct::getId, statusDTO.getGoodsId()));
            ProductEs productEs = ProductConvert.INSTANCE.toProductEs(farmerPrimaryProduct, ProductType.FARMER_PRIMARY.getValue());
            log.info(""+productEs);
            Integer insert = productEsMapper.insert(productEs);
            return insert > 0;
        }
        return false;
    }

    /**
     * 经销社审核农户发布的需求
     *
     * @param statusDTO 状态dto
     * @return {@link Boolean}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean toExamineDemand(AuditStatusDTO statusDTO) {
        return auditFarmerDemandMapper.update(null,
                new LambdaUpdateWrapper<AuditFarmerDemand>()
                        .set(AuditFarmerDemand::getAuditStatus,statusDTO.getStatus())
                        .set(AuditFarmerDemand::getAuditRemark,statusDTO.getRemark())
                        .eq(AuditFarmerDemand::getId,statusDTO.getId())) > 0;
    }

    /**
     * 删除农民初级产品
     *
     * @param id 身份证件
     * @return {@link Boolean}
     */
    @Override
    public Boolean toDeleteFarmerPrimaryProduct(Long id) {
        return auditFarmerProductMapper.delete(new LambdaQueryWrapper<AuditFarmerProduct>()
                .eq(AuditFarmerProduct::getId,id)
                .eq(AuditFarmerProduct::getAuditStatus,AuditType.PASS)) > 0;
    }
}
