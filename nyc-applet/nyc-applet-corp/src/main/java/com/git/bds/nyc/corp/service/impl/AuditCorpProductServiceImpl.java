package com.git.bds.nyc.corp.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.corp.mapper.AuditCorpProductMapper;
import com.git.bds.nyc.corp.model.domain.AuditCorpProduct;
import com.git.bds.nyc.corp.model.dto.AuditCorpProductUpdateDTO;
import com.git.bds.nyc.corp.model.vo.AuditCorpProductVO;
import com.git.bds.nyc.corp.service.AuditCorpProductService;
import com.git.bds.nyc.enums.ProductSellType;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.mapper.primary.corp.CorpPrimaryProductMapper;
import com.git.bds.nyc.product.model.dto.PrimaryProductUpdateDTO;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 审核公司发布的农产品(包括初级和加工的商品) 服务实现类
 * </p>
 *
 * @author chnnc
 * @since 2022-10-15 18:50:50
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuditCorpProductServiceImpl extends MPJBaseServiceImpl<AuditCorpProductMapper, AuditCorpProduct> implements AuditCorpProductService {
    private final AuditCorpProductMapper auditCorpProductMapper;
    private final CorpPrimaryProductMapper corpPrimaryProductMapper;

    /**
     * 公司商品审核更新
     *
     * @param auditCorpProductUpdateDTO 更新信息
     * @return {@link  Boolean}
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateCropProduct(AuditCorpProductUpdateDTO auditCorpProductUpdateDTO) {
        PrimaryProductUpdateDTO primaryProductUpdateDTO = new PrimaryProductUpdateDTO();
        primaryProductUpdateDTO.setId(auditCorpProductUpdateDTO.getProductId());
        primaryProductUpdateDTO.setAuditStatus(auditCorpProductUpdateDTO.getAuditStatus());
        corpPrimaryProductMapper.updateAuditStatus(primaryProductUpdateDTO);
        return auditCorpProductMapper.updateCropProduct(auditCorpProductUpdateDTO);
    }

    @Override
    public PageResult<AuditCorpProductVO> getPage(PageParam pageParam) {
        IPage<AuditCorpProductVO> page = getPage(pageParam, ProductSellType.ON_SELL.getValue());
        return new PageResult<>(page.getRecords(),page.getTotal());
    }

    /**
     * 获取分页数据
     *
     * @param pageParam 分页参数
     * @param type 审核的商品类型
     * @return {@link IPage<AuditCorpProductVO>}
     */
    public IPage<AuditCorpProductVO> getPage(PageParam pageParam, int type) {
        IPage<AuditCorpProductVO> pageInfo = this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(),pageParam.getPageSize()),
                AuditCorpProductVO.class,
                new MPJLambdaWrapper<>()
                        .select(
                                AuditCorpProductVO::getId,
                                AuditCorpProductVO::getUserId,
                                AuditCorpProductVO::getProductId,
                                AuditCorpProductVO::getAuditTime,
                                AuditCorpProductVO::getAuditRemark,
                                AuditCorpProductVO::getApplyTimes,
                                AuditCorpProductVO::getProductStatus,
                                AuditCorpProductVO::getAuditStatus,
                                AuditCorpProductVO::getCreateTime
                        )
                        .eq(AuditCorpProductVO::getProductStatus,type)
                        .eq(AuditCorpProductVO::getUserId, StpUtil.getLoginIdAsLong())
                        .orderByDesc(AuditCorpProductVO::getCreateTime));
        return pageInfo;
    }
}
