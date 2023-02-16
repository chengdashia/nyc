package git.bds.nyc.admin.service.audit.coop;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import git.bds.nyc.admin.model.AuditStatusDTO;
import git.bds.nyc.communal.mapper.mp.audit.AuditFarmerProductMapper;
import git.bds.nyc.communal.mapper.mp.audit.CoopAuditDemandMapper;
import git.bds.nyc.communal.mapper.mp.audit.CoopAuditProductMapper;
import git.bds.nyc.communal.model.domain.audit.AuditFarmerProduct;
import git.bds.nyc.communal.model.domain.audit.CoopAuditDemand;
import git.bds.nyc.communal.model.domain.audit.CoopAuditProduct;
import git.bds.nyc.communal.model.dto.AuditProductDTO;
import git.bds.nyc.demand.mapper.mp.CorpDemandMapper;
import git.bds.nyc.enums.AuditType;
import git.bds.nyc.page.PageParam;
import git.bds.nyc.page.PageResult;
import git.bds.nyc.product.mapper.mp.primary.farmer.FarmerPrimaryProductMapper;
import git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 成大事
 * @since 2023/1/5 15:46
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CoopAuditServiceImpl implements CoopAuditService{

    private final CoopAuditProductMapper coopAuditProductMapper;

    private final CoopAuditDemandMapper auditDemandMapper;

    private final FarmerPrimaryProductMapper farmerPrimaryProductMapper;

    private final CorpDemandMapper demandMapper;

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

        IPage<AuditProductDTO> page = coopAuditProductMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                AuditProductDTO.class,
                new MPJLambdaWrapper<CoopAuditProduct>()
                        .select(CoopAuditProduct::getId, CoopAuditProduct::getProductId, CoopAuditProduct::getApplyTimes, CoopAuditProduct::getCreateTime)
                        .select(FarmerPrimaryProduct::getProductVariety, FarmerPrimaryProduct::getProductSpecies, FarmerPrimaryProduct::getProductCover)
                        .leftJoin(FarmerPrimaryProduct.class, FarmerPrimaryProduct::getId, CoopAuditProduct::getProductId)
                        .eq(CoopAuditProduct::getAuditStatus, type)
                        .orderByDesc(CoopAuditProduct::getCreateTime));
        return new PageResult<>(page.getRecords(),page.getTotal());
    }

    /**
     * 审核产品
     *
     * @param statusDTO 状态dto
     * @return {@link Boolean}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean toExamineProduct(AuditStatusDTO statusDTO) {
        coopAuditProductMapper.update(null,
                new LambdaUpdateWrapper<CoopAuditProduct>()
                        .set(CoopAuditProduct::getAuditStatus,statusDTO.getStatus())
                        .set(CoopAuditProduct::getAuditRemark,statusDTO.getRemark())
                        .eq(CoopAuditProduct::getId,statusDTO.getId()));
        farmerPrimaryProductMapper.update(null,
                new LambdaUpdateWrapper<FarmerPrimaryProduct>()
                        .set(FarmerPrimaryProduct::getCoopAuditStatus,statusDTO.getStatus())
                        .eq(FarmerPrimaryProduct::getId,statusDTO.getGoodsId()));
        //如果审核通过
        if(AuditType.PASS.getValue().equals(statusDTO.getStatus())){
            AuditFarmerProduct auditFarmerProduct = new AuditFarmerProduct()
                    .setProductId(statusDTO.getGoodsId())
                    .setUserId(statusDTO.getUserId());
            return auditFarmerProductMapper.insert(auditFarmerProduct) > 0;
        }
        return true;
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
        return auditDemandMapper.update(null,
                new LambdaUpdateWrapper<CoopAuditDemand>()
                        .set(CoopAuditDemand::getAuditStatus,statusDTO.getStatus())
                        .set(CoopAuditDemand::getAuditRemark,statusDTO.getRemark())
                        .eq(CoopAuditDemand::getId,statusDTO.getId())) > 0;
    }
}
