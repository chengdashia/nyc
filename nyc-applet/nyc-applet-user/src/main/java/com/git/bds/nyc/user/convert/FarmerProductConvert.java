package com.git.bds.nyc.user.convert;

import com.git.bds.nyc.product.model.dto.ProductCollectAndHistoryDTO;
import com.git.bds.nyc.product.model.dto.ProductReleaseDTO;
import com.git.bds.nyc.user.model.vo.FarmerAuditPrimaryProductVO;
import com.git.bds.nyc.user.model.vo.FarmerProductVO;
import com.git.bds.nyc.user.model.vo.FarmerReleaseOnSellPrimaryProductVO;
import com.git.bds.nyc.user.model.vo.FarmerReleasePreSellPrimaryProductVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 成大事
 * @since 2022/10/19 11:40
 */
@Mapper
public interface FarmerProductConvert {
    FarmerProductConvert INSTANCE = Mappers.getMapper(FarmerProductConvert.class);


    /**
     * 农民发布销售初级产品vo
     *
     * @param list 列表
     * @return {@link List}<{@link FarmerReleaseOnSellPrimaryProductVO}>
     */
    List<FarmerReleaseOnSellPrimaryProductVO> toFarmerReleaseOnSellPrimaryProductVO(List<ProductReleaseDTO> list);


    /**
     * 向农民发布预售初级产品vo
     *
     * @param list 列表
     * @return {@link List}<{@link FarmerReleasePreSellPrimaryProductVO}>
     */
    List<FarmerReleasePreSellPrimaryProductVO> toFarmerReleasePreSellPrimaryProductVO(List<ProductReleaseDTO> list);


    /**
     * 向农民审计预售初级产品vo
     *
     * @param list 列表
     * @return {@link List}<{@link FarmerAuditPrimaryProductVO}>
     */
    List<FarmerAuditPrimaryProductVO> toFarmerAuditPreSellPrimaryProductVO(List<ProductReleaseDTO> list);


    /**
     * 到农产品收集vo
     *
     * @param list 列表
     * @return {@link List}<{@link FarmerProductVO}>
     */
    List<FarmerProductVO> toProductVO(List<ProductCollectAndHistoryDTO> list);


}
