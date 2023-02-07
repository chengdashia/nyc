package com.git.bds.nyc.corp.convert;

import com.git.bds.nyc.corp.model.vo.CorpAuditProductVO;
import com.git.bds.nyc.corp.model.vo.CorpProductVO;
import com.git.bds.nyc.corp.model.vo.CorpReleaseOnSellProductVO;
import com.git.bds.nyc.corp.model.vo.CorpReleasePreSellProductVO;
import com.git.bds.nyc.product.model.dto.ProductCollectAndHistoryDTO;
import com.git.bds.nyc.product.model.dto.ProductReleaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 成大事
 * @since 2022/10/19 11:06
 */
@Mapper
public interface CorpProductConvert {

    CorpProductConvert INSTANCE = Mappers.getMapper(CorpProductConvert.class);

    /**
     * 公司发布销售产品vo
     *
     * @param list 列表
     * @return {@link List}<{@link CorpReleaseOnSellProductVO}>
     */
    List<CorpReleaseOnSellProductVO> toCorpReleaseOnSellProductVO(List<ProductReleaseDTO> list);


    /**
     * 公司发布预售产品vo
     *
     * @param list 列表
     * @return {@link List}<{@link CorpReleasePreSellProductVO}>
     */
    List<CorpReleasePreSellProductVO> toCorpReleasePreSellProductVO(List<ProductReleaseDTO> list);

    /**
     * 公司审计主要产品vo
     *
     * @param list 列表
     * @return {@link List}<{@link CorpAuditProductVO}>
     */
    List<CorpAuditProductVO> toCorpAuditPrimaryProductVO(List<ProductReleaseDTO> list);

    /**
     * 至产品集合vo
     *
     * @param list 列表
     * @return {@link List}<{@link CorpProductVO}>
     */
    List<CorpProductVO> toProductVO(List<ProductCollectAndHistoryDTO> list);


}
