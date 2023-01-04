package com.git.bds.nyc.admin.convert;

import com.git.bds.nyc.admin.model.domain.Advertisement;
import com.git.bds.nyc.admin.model.vo.AdvertisementVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 成大事
 * @since 2023/1/4 15:54
 */
@Mapper
public interface AdvertisementConvert {

    AdvertisementConvert INSTANCE = Mappers.getMapper(AdvertisementConvert.class);


    /**
     * 到广告列表
     *
     * @param list 列表
     * @return {@link List}<{@link AdvertisementVO}>
     */
    List<AdvertisementVO> toAdvertisementVOList(List<Advertisement> list);
}
