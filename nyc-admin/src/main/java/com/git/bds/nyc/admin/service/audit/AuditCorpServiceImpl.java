package com.git.bds.nyc.admin.service.audit;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.communal.mapper.mp.audit.AuditCorpDemandMapper;
import com.git.bds.nyc.communal.mapper.mp.audit.AuditCorpProductMapper;
import com.git.bds.nyc.communal.model.domain.audit.AuditCorpProduct;
import com.git.bds.nyc.communal.model.domain.audit.CoopAuditProduct;
import com.git.bds.nyc.communal.model.dto.AuditProductDTO;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 成大事
 * @since 2023/1/5 16:03
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuditCorpServiceImpl implements AuditCorpService{

    private final AuditCorpProductMapper auditCorpProductMapper;
    private final AuditCorpDemandMapper auditCorpDemandMapper;

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
}
