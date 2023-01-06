package com.git.bds.nyc.admin.convert;

import com.git.bds.nyc.admin.model.vo.AuditDemandVO;
import com.git.bds.nyc.admin.model.vo.AuditProductInfoVO;
import com.git.bds.nyc.admin.model.vo.AuditProductVO;
import com.git.bds.nyc.communal.model.dto.AuditProductDTO;
import com.git.bds.nyc.demand.model.dto.DemandInfoDTO;
import com.git.bds.nyc.product.model.dto.AuditProductInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 成大事
 * @since 2023/1/5 15:28
 */
@Mapper
public interface AuditConvert {

    AuditConvert INSTANCE = Mappers.getMapper(AuditConvert.class);

    /**
     * 审核产品列表
     *
     * @param list 列表
     * @return {@link List}<{@link AuditProductVO}>
     */
    List<AuditProductVO> toAuditProductVOList(List<AuditProductDTO> list);

    /**
     * 审核产品信息vo
     *
     * @param productInfoDTO 产品信息dto
     * @return {@link AuditProductInfoVO}
     */
    AuditProductInfoVO toAuditProductInfoVO(AuditProductInfoDTO productInfoDTO);


    /**
     * 审核需求信息vo
     *
     * @param demandDTO 需求dto
     * @return {@link AuditDemandVO}
     */
    AuditDemandVO toAuditDemandInfoVO(DemandInfoDTO demandDTO);
}
