package com.git.bds.nyc.applet.api.convert;

import com.git.bds.nyc.applet.api.model.vo.CollectionRecordVO;
import com.git.bds.nyc.product.model.dto.ProductCollectAndHistoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 成大事
 * @since 2023/2/14 21:09
 */
@Mapper
public interface CollectionConvert {

    CollectionConvert INSTANCE = Mappers.getMapper(CollectionConvert.class);


    /**
     * 到集合记录vo
     *
     * @param productCollectAndHistoryDTO 产品收集和历史dto
     * @return {@link CollectionRecordVO}
     */
    @Mapping(source = "createTime",target = "collectionTime")
    CollectionRecordVO toCollectionRecordVO(ProductCollectAndHistoryDTO productCollectAndHistoryDTO);

    /**
     * 到收藏记录展示的vo
     *
     * @param list 列表
     * @return {@link List}<{@link CollectionRecordVO}>
     */
    List<CollectionRecordVO> toCollectionRecordVO(List<ProductCollectAndHistoryDTO> list);
}
