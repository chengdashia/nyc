package com.git.bds.nyc.corp;

import com.git.bds.nyc.corp.model.vo.CorpSelfPrimaryProductVO;
import com.git.bds.nyc.product.model.dto.PrimaryProductSelfDTO;
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
     * 到公司自己主要产品vo
     *
     * @param list 列表
     * @return {@link List}<{@link CorpSelfPrimaryProductVO}>
     */
    List<CorpSelfPrimaryProductVO> toCorpSelfPrimaryProductVO(List<PrimaryProductSelfDTO> list);
}
