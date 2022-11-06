package com.git.bds.nyc.product.mapper.primary.corp;


import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.dto.PrimaryProductUpdateDTO;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
     * 更新审核状态
     *
     * @param primaryProductUpdateDTO 更新数据
     * @return {@link  Boolean}
     */
    Boolean updateAuditStatus(PrimaryProductUpdateDTO primaryProductUpdateDTO);

}
