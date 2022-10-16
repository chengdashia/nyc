package com.git.bds.nyc.corp.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.git.bds.nyc.corp.dao.AuditCorpProductDao;
import com.git.bds.nyc.corp.dao.CorpPrimaryProductDao;
import com.git.bds.nyc.corp.model.domain.AuditCorpProduct;
import com.git.bds.nyc.corp.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.corp.service.CorpPrimaryProductService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 初级农产品表 服务实现类
 * </p>
 *
 * @author chnnc
 * @since 2022-10-15 18:50:50
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CorpPrimaryProductServiceImpl extends MPJBaseServiceImpl<CorpPrimaryProductDao, CorpPrimaryProduct> implements CorpPrimaryProductService {
    private final CorpPrimaryProductDao corpPrimaryProductDao;
    private final AuditCorpProductDao auditCorpProductDao;

    /**
     * 分页查询
     *
     * @param pageInfo 分页
     * @param param 查询条件
     * @return {@link = IPage<CorpPrimaryProduct>}
     */
    @Override
    public IPage<CorpPrimaryProduct> findPage(IPage<CorpPrimaryProduct> pageInfo, CorpPrimaryProduct param) {
        IPage<CorpPrimaryProduct> pageList = corpPrimaryProductDao.findPage(pageInfo,param);
        return pageList;
    }

    /**
     * 新增一个初级农产品
     *
     * @param corpPrimaryProduct 实体对象
     * @return true 成功，false 失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(CorpPrimaryProduct corpPrimaryProduct) {
        corpPrimaryProductDao.insert(corpPrimaryProduct);
        AuditCorpProduct auditCorpProduct = new AuditCorpProduct();
        auditCorpProduct.setUserId(Long.valueOf(StpUtil.getLoginId().toString()));
        auditCorpProduct.setProductId(corpPrimaryProduct.getId());
        auditCorpProduct.setProductStatus(corpPrimaryProduct.getProductStatus());
        auditCorpProduct.setAuditStatus(corpPrimaryProduct.getAuditStatus());
        int isAuditCorpProduct = auditCorpProductDao.insert(auditCorpProduct);
        return isAuditCorpProduct > 0;
    }

    /**
     * 根据农产品id删除
     *
     * @param corpPrimaryProductId 农产品id
     * @return true 成功，false 失败
     */
    @Override
    public boolean delete(Long corpPrimaryProductId) {
        int i = corpPrimaryProductDao.deleteById(corpPrimaryProductId);
        return i > 0;
    }

    /**
     * 更新初级农产品信息
     *
     * @param corpPrimaryProduct  初级农产品信息
     * @return true 成功，false 失败
     */
    @Override
    public boolean modify(CorpPrimaryProduct corpPrimaryProduct) {
        int i = corpPrimaryProductDao.updateById(corpPrimaryProduct);
        return i > 0;
    }
}
