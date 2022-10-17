package com.git.bds.nyc.product.service.primary.corp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.git.bds.nyc.corp.model.domain.CorpPrimaryProduct;
import com.github.yulichang.base.MPJBaseService;

/**
 * <p>
 * 初级农产品表 服务类
 * </p>
 *
 * @author chnnc
 * @since 2022-10-15 18:50:50
 */
public interface CorpPrimaryProductService extends MPJBaseService<CorpPrimaryProduct> {
    /**
     * 分页、条件查询
     *
     * @param param 查询条件
     * @return { @link =  R<List<CorpPrimaryProduct>> }
     */
    IPage<CorpPrimaryProduct> findPage(IPage<CorpPrimaryProduct> pageInfo, CorpPrimaryProduct param);

    /**
     * 新增一条初级农产品
     *
     * @param corpPrimaryProduct 实体对象
     * @return true 成功，false 失败
     */
    boolean save(CorpPrimaryProduct corpPrimaryProduct);

    /**
     * 根据初级农产品id删除
     *
     * @param corpPrimaryProductId
     * @return true 成功，false 失败
     */
    boolean delete(Long corpPrimaryProductId);

    /**
     * 更新初级农产品信息
     *
     * @param corpPrimaryProduct
     * @return true 成功，false 失败
     */
    boolean modify(CorpPrimaryProduct corpPrimaryProduct);


}
