package com.git.bds.nyc.user.convert;

import com.git.bds.nyc.product.model.dto.PrimaryProductSelfDTO;
import com.git.bds.nyc.product.model.dto.ProductCollectAndHistoryDTO;
import com.git.bds.nyc.user.model.vo.FarmerSelfPrimaryProductVO;
import com.git.bds.nyc.user.model.vo.FarmerProductVO;
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
     * 给农民自己初级产品vo
     *
     * @param list 列表
     * @return {@link List}<{@link FarmerSelfPrimaryProductVO}>
     */
    List<FarmerSelfPrimaryProductVO> toFarmerSelfPrimaryProductVO(List<PrimaryProductSelfDTO> list);


    /**
     * 到农产品收集vo
     *
     * @param list 列表
     * @return {@link List}<{@link FarmerProductVO}>
     */
    List<FarmerProductVO> toProductVO(List<ProductCollectAndHistoryDTO> list);
}
