package com.git.bds.nyc.common.service.audit.impl;


import com.git.bds.nyc.common.mapper.mp.audit.AuditFarmerProductMapper;
import com.git.bds.nyc.common.model.domain.audit.AuditFarmerProduct;
import com.git.bds.nyc.common.service.audit.AuditFarmerProductService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 审核公司发布的农产品(包括初级和加工的商品) 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-11-23 10:43:48
 */
@Service
public class AuditFarmerProductServiceImpl extends MPJBaseServiceImpl<AuditFarmerProductMapper, AuditFarmerProduct> implements AuditFarmerProductService {

}