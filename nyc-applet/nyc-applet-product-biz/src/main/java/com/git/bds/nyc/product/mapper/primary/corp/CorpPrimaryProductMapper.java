package com.git.bds.nyc.product.mapper.primary.corp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.git.bds.nyc.corp.model.domain.CorpPrimaryProduct;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 初级农产品表 Mapper 接口
 * </p>
 *
 * @author chnnc
 * @since 2022-10-15 18:50:50
 */
@Mapper
public interface CorpPrimaryProductMapper extends MPJBaseMapper<CorpPrimaryProduct> {
    /**
     * 分页、条件查询初级农产品列表
     *
     * @param param 查询参数
     * @return {@link = IPage<CorpPrimaryProduct>}
     */
    IPage<CorpPrimaryProduct> findPage(IPage<CorpPrimaryProduct> pageInfo, @Param(value = "param") CorpPrimaryProduct param);

}
