package com.git.bds.nyc.applet.api.convert.index;

import com.git.bds.nyc.applet.api.model.vo.IndexAdvertisementVO;
import com.git.bds.nyc.communal.model.domain.Advertisement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 成大事
 * @since 2023/1/4 20:40
 */
@Mapper
public interface IndexConvert {

    IndexConvert INSTANCE = Mappers.getMapper(IndexConvert.class);

    /**
     * 到广告列表
     *
     * @param list 列表
     * @return {@link List}<{@link IndexAdvertisementVO}>
     */
    List<IndexAdvertisementVO> toAdvertisementVOList(List<Advertisement> list);
}
