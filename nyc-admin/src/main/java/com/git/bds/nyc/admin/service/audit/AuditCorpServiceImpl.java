package com.git.bds.nyc.admin.service.audit;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.admin.model.AuditStatusDTO;
import com.git.bds.nyc.communal.mapper.mp.audit.AuditCorpDemandMapper;
import com.git.bds.nyc.communal.mapper.mp.audit.AuditCorpProductMapper;
import com.git.bds.nyc.communal.model.domain.audit.AuditCorpDemand;
import com.git.bds.nyc.communal.model.domain.audit.AuditCorpProduct;
import com.git.bds.nyc.communal.model.domain.audit.CoopAuditProduct;
import com.git.bds.nyc.communal.model.dto.AuditProductDTO;
import com.git.bds.nyc.demand.mapper.mp.DemandMapper;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.mapper.mp.CorpProcessingProductMapper;
import com.git.bds.nyc.product.mapper.mp.primary.corp.CorpPrimaryProductMapper;
import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.domain.CorpProcessingProduct;
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


    private final DemandMapper demandMapper;

    /**
     * 按页面获取挂起审核产品
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
        return auditCorpProductMapper.update(null,
                new LambdaUpdateWrapper<AuditCorpProduct>()
                        .set(AuditCorpProduct::getAuditStatus,statusDTO.getStatus())
                        .set(AuditCorpProduct::getAuditRemark,statusDTO.getRemark())
                        .eq(AuditCorpProduct::getId,statusDTO.getId())) > 0
                && corpPrimaryProductMapper.update(null,
                new LambdaUpdateWrapper<CorpPrimaryProduct>()
                        .set(CorpPrimaryProduct::getAuditStatus,statusDTO.getStatus())
                        .eq(CorpPrimaryProduct::getId,statusDTO.getGoodsId())) > 0;
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
        return auditCorpProductMapper.update(null,
                new LambdaUpdateWrapper<AuditCorpProduct>()
                        .set(AuditCorpProduct::getAuditStatus,statusDTO.getStatus())
                        .set(AuditCorpProduct::getAuditRemark,statusDTO.getRemark())
                        .eq(AuditCorpProduct::getId,statusDTO.getId())) > 0
                && corpProcessingProductMapper.update(null,
                new LambdaUpdateWrapper<CorpProcessingProduct>()
                        .set(CorpProcessingProduct::getAuditStatus,statusDTO.getStatus())
                        .eq(CorpProcessingProduct::getId,statusDTO.getGoodsId())) > 0;
    }


    /**
     * 审核需求
     *
     * @param statusDTO 状态dto
     * @return {@link Boolean}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean toExamineDemand(AuditStatusDTO statusDTO) {
        return auditCorpDemandMapper.update(null,
                new LambdaUpdateWrapper<AuditCorpDemand>()
                        .set(AuditCorpDemand::getAuditStatus,statusDTO.getStatus())
                        .set(AuditCorpDemand::getAuditRemark,statusDTO.getRemark())
                        .eq(AuditCorpDemand::getId,statusDTO.getId())) > 0;
    }


}
