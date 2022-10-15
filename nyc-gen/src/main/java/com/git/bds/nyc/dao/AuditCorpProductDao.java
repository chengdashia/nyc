package com.git.bds.nyc.dao;

import com.git.bds.nyc.model.domain.AuditCorpProduct;
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
public interface AuditCorpProductDao extends MPJBaseMapper<AuditCorpProduct> {

}
