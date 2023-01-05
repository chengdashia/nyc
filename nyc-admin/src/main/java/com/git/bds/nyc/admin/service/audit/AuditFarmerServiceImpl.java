package com.git.bds.nyc.admin.service.audit;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.communal.mapper.mp.audit.AuditFarmerProductMapper;
import com.git.bds.nyc.communal.model.domain.audit.AuditFarmerProduct;
import com.git.bds.nyc.communal.model.domain.audit.CoopAuditProduct;
import com.git.bds.nyc.communal.model.dto.AuditProductDTO;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 成大事
 * @since 2023/1/5 16:04
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuditFarmerServiceImpl implements AuditFarmerService{

    private final AuditFarmerProductMapper auditFarmerProductMapper;

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
                new MPJLambdaWrapper<AuditProductDTO>()
                        .select(AuditFarmerProduct::getId, AuditFarmerProduct::getProductId, AuditFarmerProduct::getApplyTimes, AuditFarmerProduct::getCreateTime)
                        .select(FarmerPrimaryProduct::getProductVariety, FarmerPrimaryProduct::getProductSpecies, FarmerPrimaryProduct::getProductCover)
                        .leftJoin(FarmerPrimaryProduct.class, FarmerPrimaryProduct::getId, CoopAuditProduct::getProductId)
                        .eq(AuditFarmerProduct::getAuditStatus, type));
        return new PageResult<>(page.getRecords(),page.getTotal());
    }
}
