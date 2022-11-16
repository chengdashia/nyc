package com.git.bds.nyc.corp.mapper.mp;

import com.git.bds.nyc.corp.model.domain.AuditCorpProduct;
import com.git.bds.nyc.corp.model.dto.AuditCorpProductUpdateDTO;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 审核公司发布的农产品(包括初级和加工的商品) Mapper 接口
 * </p>
 *
 * @author chnnc
 * @since 2022-10-15 18:50:50
 */
@Mapper
public interface AuditCorpProductMapper extends MPJBaseMapper<AuditCorpProduct> {
    /**
     * 公司商品审核更新
     *
     * @param auditCorpProductUpdateDTO 更新信息
     * @return {@link  Boolean}
     */
    Boolean updateCropProduct(AuditCorpProductUpdateDTO auditCorpProductUpdateDTO);



}
