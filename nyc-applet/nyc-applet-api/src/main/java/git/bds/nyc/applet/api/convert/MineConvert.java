package git.bds.nyc.applet.api.convert;

import git.bds.nyc.applet.api.model.dto.NumberOfReleaseDTO;
import git.bds.nyc.applet.api.model.vo.NumberOfReleaseVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author 成大事
 * @since 2023/2/8 14:42
 */
@Mapper
public interface MineConvert {

    MineConvert INSTANCE = Mappers.getMapper(MineConvert.class);

    /**
     * 至释放数量vo
     *
     * @param numberOfReleaseDTO 发布dto数量
     * @return {@link NumberOfReleaseVO}
     */
    @Mapping(source = "totalNum",target = "totalNum",defaultValue = "0")
    @Mapping(source = "onSellNum",target = "onSellNum",defaultValue = "0")
    @Mapping(source = "preSellNum",target = "preSellNum",defaultValue = "0")
    @Mapping(source = "auditNum",target = "auditNum",defaultValue = "0")
    NumberOfReleaseVO toNumberOfReleaseVO(NumberOfReleaseDTO numberOfReleaseDTO);
}
