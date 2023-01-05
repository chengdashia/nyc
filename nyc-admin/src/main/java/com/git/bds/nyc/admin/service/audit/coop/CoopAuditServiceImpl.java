package com.git.bds.nyc.admin.service.audit.coop;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.communal.mapper.mp.audit.CoopAuditProductMapper;
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
 * @since 2023/1/5 15:46
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CoopAuditServiceImpl implements CoopAuditService{

    private final CoopAuditProductMapper coopAuditProductMapper;
    /**
     * 按页面获取挂起审核产品
     *
     * @param pageParam 页面参数
     * @param type      类型
     * @return {@link PageResult}<{@link AuditProductDTO}>
     */
    @Override
    public PageResult<AuditProductDTO> getPendingAuditProductByPage(PageParam pageParam, Integer type) {
        IPage<AuditProductDTO> page = coopAuditProductMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize(),false),
                AuditProductDTO.class,
                new MPJLambdaWrapper<AuditProductDTO>()
                        .select(CoopAuditProduct::getId, CoopAuditProduct::getProductId, CoopAuditProduct::getApplyTimes, CoopAuditProduct::getCreateTime)
                        .select(FarmerPrimaryProduct::getProductVariety, FarmerPrimaryProduct::getProductSpecies, FarmerPrimaryProduct::getProductCover)
                        .leftJoin(FarmerPrimaryProduct.class, FarmerPrimaryProduct::getId, CoopAuditProduct::getProductId)
                        .eq(CoopAuditProduct::getAuditStatus, type));
        return new PageResult<>(page.getRecords(),page.getTotal());
    }
}
