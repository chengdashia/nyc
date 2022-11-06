package com.git.bds.nyc.corp.service;

import com.git.bds.nyc.corp.model.domain.AuditCorpProduct;
import com.git.bds.nyc.corp.model.dto.AuditCorpProductUpdateDTO;
import com.git.bds.nyc.corp.model.vo.AuditCorpProductVO;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.github.yulichang.base.MPJBaseService;

/**
 * <p>
 * 审核公司发布的农产品(包括初级和加工的商品) 服务类
 * </p>
 *
 * @author chnnc
 * @since 2022-10-15 18:50:50
 */
public interface AuditCorpProductService extends MPJBaseService<AuditCorpProduct> {
    /**
     * 公司商品审核更新
     *
     * @param auditCorpProductUpdateDTO 更新信息
     * @return {@link  Boolean}
     */
    Boolean updateCropProduct(AuditCorpProductUpdateDTO auditCorpProductUpdateDTO);

    /**
     * 获取分页数据
     *
     * @param pageParam 分页参数
     * @return {@link PageResult<AuditCorpProductVO>}
     */
    PageResult<AuditCorpProductVO> getPage(PageParam pageParam);

}
