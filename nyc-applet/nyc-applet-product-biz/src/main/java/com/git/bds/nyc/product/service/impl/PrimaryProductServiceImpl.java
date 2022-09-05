package com.git.bds.nyc.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.product.dao.PrimaryProductDao;
import com.git.bds.nyc.product.model.domain.PrimaryProduct;
import com.git.bds.nyc.product.service.PrimaryProductService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 初级农产品表 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-09-02 22:02:03
 */
@Service
public class PrimaryProductServiceImpl extends MPJBaseServiceImpl<PrimaryProductDao, PrimaryProduct> implements PrimaryProductService {

    @Override
    public List<PrimaryProduct> homePageProductsByPage(PageParam pageParam) {
        List<PrimaryProduct> productList = this.baseMapper.selectPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize(), false),
                new LambdaQueryWrapper<PrimaryProduct>()
                        .select(PrimaryProduct::getId
                                , PrimaryProduct::getProductSpecies
                                , PrimaryProduct::getProductVariety
                                , PrimaryProduct::getProductWeight
                                , PrimaryProduct::getProductPrice
                                , PrimaryProduct::getProductProductionArea
                                , PrimaryProduct::getProductCover)
                        .orderByDesc(PrimaryProduct::getCreateTime)).getRecords();
        return productList;
    }
}
