package com.git.bds.nyc.corp.service.impl;

import com.git.bds.nyc.corp.mapper.AuditCorpProductMapper;
import com.git.bds.nyc.corp.model.domain.AuditCorpProduct;
import com.git.bds.nyc.corp.model.dto.AuditCorpProductUpdateDTO;
import com.git.bds.nyc.corp.service.AuditCorpProductService;
import com.git.bds.nyc.product.mapper.primary.corp.CorpPrimaryProductMapper;
import com.git.bds.nyc.product.model.dto.PrimaryProductUpdateDTO;
import com.github.yulichang.base.MPJBaseServiceImpl;
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
}
