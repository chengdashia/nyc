package com.git.bds.nyc.corp.service.impl;

import com.git.bds.nyc.corp.dao.AuditCorpProductDao;
import com.git.bds.nyc.corp.model.domain.AuditCorpProduct;
import com.git.bds.nyc.corp.service.AuditCorpProductService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuditCorpProductServiceImpl extends MPJBaseServiceImpl<AuditCorpProductDao, AuditCorpProduct> implements AuditCorpProductService {

}