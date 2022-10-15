package com.git.bds.nyc.service.impl;

import com.git.bds.nyc.model.domain.AuditCorpProduct;
import com.git.bds.nyc.dao.AuditCorpProductDao;
import com.git.bds.nyc.service.AuditCorpProductService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 审核公司发布的农产品(包括初级和加工的商品) 服务实现类
 * </p>
 *
 * @author chnnc
 * @since 2022-10-15 18:50:50
 */
@Service
public class AuditCorpProductServiceImpl extends MPJBaseServiceImpl<AuditCorpProductDao, AuditCorpProduct> implements AuditCorpProductService {

}
