package com.git.bds.nyc.communal.mapper.mp.audit;


import com.git.bds.nyc.communal.model.domain.audit.CoopAuditProduct;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 合作社审核个人发布的农产品(只有初级农产品) Mapper 接口
 * </p>
 *
 * @author 成大事
 * @since 2022-11-23 10:43:48
 */
@Mapper
public interface CoopAuditProductMapper extends MPJBaseMapper<CoopAuditProduct> {

}
