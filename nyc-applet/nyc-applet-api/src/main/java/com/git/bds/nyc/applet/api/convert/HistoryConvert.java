package com.git.bds.nyc.applet.api.convert;

import com.git.bds.nyc.applet.api.model.vo.CollectionRecordVO;
import com.git.bds.nyc.applet.api.model.vo.HistoryRecordVO;
import com.git.bds.nyc.product.model.dto.ProductCollectAndHistoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 成大事
 * @since 2023/2/14 21:10
 */
@Mapper
public interface HistoryConvert {

    HistoryConvert INSTANCE = Mappers.getMapper(HistoryConvert.class);


    /**
     * 到历史记录vo
     *
     * @param productCollectAndHistoryDTO 产品收集和历史dto
     * @return {@link HistoryRecordVO}
     */
    @Mapping(source = "createTime",target = "browseTime")
    HistoryRecordVO toHistoryRecordVO(ProductCollectAndHistoryDTO productCollectAndHistoryDTO);

    /**
     * 到收藏记录展示的vo
     *
     * @param list 列表
     * @return {@link List}<{@link CollectionRecordVO}>
     */
    @Mapping(source = "createTime",target = "browseTime")
    List<HistoryRecordVO> toHistoryRecordVO(List<ProductCollectAndHistoryDTO> list);
}
