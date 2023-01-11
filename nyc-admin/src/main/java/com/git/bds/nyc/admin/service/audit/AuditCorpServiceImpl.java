package com.git.bds.nyc.admin.service.audit;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.admin.convert.DemandConvert;
import com.git.bds.nyc.admin.convert.ProductConvert;
import com.git.bds.nyc.admin.model.AuditStatusDTO;
import com.git.bds.nyc.communal.mapper.mp.audit.AuditCorpDemandMapper;
import com.git.bds.nyc.communal.mapper.mp.audit.AuditCorpProductMapper;
import com.git.bds.nyc.communal.model.domain.audit.AuditCorpDemand;
import com.git.bds.nyc.communal.model.domain.audit.AuditCorpProduct;
import com.git.bds.nyc.communal.model.domain.audit.CoopAuditProduct;
import com.git.bds.nyc.communal.model.dto.AuditProductDTO;
import com.git.bds.nyc.demand.mapper.ee.DemandEsMapper;
import com.git.bds.nyc.demand.mapper.mp.CorpDemandMapper;
import com.git.bds.nyc.demand.model.domain.CorpDemand;
import com.git.bds.nyc.demand.model.ee.DemandEs;
import com.git.bds.nyc.enums.AuditType;
import com.git.bds.nyc.enums.DemandType;
import com.git.bds.nyc.enums.ProductType;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.mapper.ee.ProductEsMapper;
import com.git.bds.nyc.product.mapper.mp.CorpProcessingProductMapper;
import com.git.bds.nyc.product.mapper.mp.primary.corp.CorpPrimaryProductMapper;
import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.domain.CorpProcessingProduct;
import com.git.bds.nyc.product.model.es.ProductEs;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 成大事
 * @since 2023/1/5 16:03
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuditCorpServiceImpl implements AuditCorpService{

    private final AuditCorpProductMapper auditCorpProductMapper;

    private final AuditCorpDemandMapper auditCorpDemandMapper;

    private final CorpPrimaryProductMapper corpPrimaryProductMapper;

    private final CorpProcessingProductMapper corpProcessingProductMapper;

    private final CorpDemandMapper corpDemandMapper;

    private final ProductEsMapper productEsMapper;

    private final DemandEsMapper demandEsMapper;

    /**
     * 按页面获取需要审核产品
     *
     * @param pageParam 页面参数
     * @param type      类型
     * @return {@link PageResult}<{@link AuditProductDTO}>
     */
    @Override
    public PageResult<AuditProductDTO> getPendingAuditProductByPage(PageParam pageParam, Integer type) {
        IPage<AuditProductDTO> page = auditCorpProductMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize(),false),
                AuditProductDTO.class,
                new MPJLambdaWrapper<AuditProductDTO>()
                        .select(AuditCorpProduct::getId, AuditCorpProduct::getProductId, AuditCorpProduct::getApplyTimes, AuditCorpProduct::getCreateTime)
                        .select(CorpPrimaryProduct::getProductVariety, CorpPrimaryProduct::getProductSpecies, CorpPrimaryProduct::getProductCover)
                        .leftJoin(CorpPrimaryProduct.class, CorpPrimaryProduct::getId, CoopAuditProduct::getProductId)
                        .eq(AuditCorpProduct::getAuditStatus, type));
        return new PageResult<>(page.getRecords(),page.getTotal());
    }

    /**
     * 供销社审核公司发布的初级农产品
     *
     * @param statusDTO 状态dto
     * @return {@link Boolean}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean toExamineCorpPrimaryProduct(AuditStatusDTO statusDTO) {
        //更新审核表
        auditCorpProductMapper.update(null,
                new LambdaUpdateWrapper<AuditCorpProduct>()
                        .set(AuditCorpProduct::getAuditStatus,statusDTO.getStatus())
                        .set(AuditCorpProduct::getAuditRemark,statusDTO.getRemark())
                        .eq(AuditCorpProduct::getId,statusDTO.getId()));
        //更新公司初级农产品的表
        corpPrimaryProductMapper.update(null,
                new LambdaUpdateWrapper<CorpPrimaryProduct>()
                        .set(CorpPrimaryProduct::getAuditStatus,statusDTO.getStatus())
                        .eq(CorpPrimaryProduct::getId,statusDTO.getGoodsId()));
        if(AuditType.PASS.getValue().equals(statusDTO.getStatus())){
            CorpPrimaryProduct corpPrimaryProduct = corpPrimaryProductMapper.selectOne(new LambdaQueryWrapper<CorpPrimaryProduct>()
                    .select(
                            CorpPrimaryProduct::getId,
                            CorpPrimaryProduct::getProductSpecies,
                            CorpPrimaryProduct::getProductVariety,
                            CorpPrimaryProduct::getProductPrice,
                            CorpPrimaryProduct::getProductProductionArea,
                            CorpPrimaryProduct::getProductCover,
                            CorpPrimaryProduct::getAuditStatus,
                            CorpPrimaryProduct::getMarketTime,
                            CorpPrimaryProduct::getMarketTime
                    )
                    .eq(CorpPrimaryProduct::getId, statusDTO.getGoodsId()));
            ProductEs productEs = ProductConvert.INSTANCE.toProductEs(corpPrimaryProduct, ProductType.CORP_PRIMARY.getValue());
            //将数据插入es
            productEsMapper.insert(productEs);
        }
        return true;
    }

    /**
     * 供销社审核公司发布的加工农产品
     *
     * @param statusDTO 状态dto
     * @return {@link Boolean}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean toExamineCorpProcessingProduct(AuditStatusDTO statusDTO) {
         auditCorpProductMapper.update(null,
                new LambdaUpdateWrapper<AuditCorpProduct>()
                        .set(AuditCorpProduct::getAuditStatus,statusDTO.getStatus())
                        .set(AuditCorpProduct::getAuditRemark,statusDTO.getRemark())
                        .eq(AuditCorpProduct::getId,statusDTO.getId()));
         corpProcessingProductMapper.update(null,
                new LambdaUpdateWrapper<CorpProcessingProduct>()
                        .set(CorpProcessingProduct::getAuditStatus,statusDTO.getStatus())
                        .eq(CorpProcessingProduct::getId,statusDTO.getGoodsId()));
        if(AuditType.PASS.getValue().equals(statusDTO.getStatus())){
            CorpProcessingProduct corpProcessingProduct = corpProcessingProductMapper.selectOne(new LambdaQueryWrapper<CorpProcessingProduct>()
                    .select(
                            CorpProcessingProduct::getId,
                            CorpProcessingProduct::getProductSpecies,
                            CorpProcessingProduct::getProductVariety,
                            CorpProcessingProduct::getProductPrice,
                            CorpProcessingProduct::getProductProductionArea,
                            CorpProcessingProduct::getProductCover,
                            CorpProcessingProduct::getAuditStatus,
                            CorpProcessingProduct::getMarketTime,
                            CorpProcessingProduct::getMarketTime
                    )
                    .eq(CorpProcessingProduct::getId, statusDTO.getGoodsId()));
            ProductEs productEs = ProductConvert.INSTANCE.toProductEs(corpProcessingProduct, ProductType.CORP_PROCESSING.getValue());
            //将数据插入es
            productEsMapper.insert(productEs);
        }
        return true;
    }


    /**
     * 供销社审核公司发布的需求
     *
     * @param statusDTO 状态dto
     * @return {@link Boolean}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean toExamineCorpDemand(AuditStatusDTO statusDTO) {
        auditCorpDemandMapper.update(null,
                new LambdaUpdateWrapper<AuditCorpDemand>()
                        .set(AuditCorpDemand::getAuditStatus,statusDTO.getStatus())
                        .set(AuditCorpDemand::getAuditRemark,statusDTO.getRemark())
                        .eq(AuditCorpDemand::getId,statusDTO.getId()));
        corpDemandMapper.update(null,
                new LambdaUpdateWrapper<CorpDemand>()
                        .set(CorpDemand::getAuditStatus,statusDTO.getStatus())
                        .eq(CorpDemand::getId,statusDTO.getGoodsId()));
        if(AuditType.PASS.getValue().equals(statusDTO.getStatus())){
            CorpDemand corpDemand = corpDemandMapper.selectOne(new LambdaQueryWrapper<CorpDemand>()
                    .select(
                            CorpDemand.class, i -> !i.getColumn().equals(CorpDemand.USER_ID)
                                    && !i.getColumn().equals(CorpDemand.DEMAND_REMARK)
                                    && !i.getColumn().equals(CorpDemand.AUDIT_STATUS)
                                    && !i.getColumn().equals(CorpDemand.EXPIRATION_DATE)
                    )
                    .eq(CorpDemand::getId, statusDTO.getGoodsId()));
            DemandEs demandEs = DemandConvert.INSTANCE.toDemandEs(corpDemand, DemandType.CORP_DEMAND.getValue());
            //将数据插入es
            demandEsMapper.insert(demandEs);
        }
        return true;
    }


}
