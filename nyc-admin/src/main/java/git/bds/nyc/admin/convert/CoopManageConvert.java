package git.bds.nyc.admin.convert;

import git.bds.nyc.admin.model.dto.CoopUserDTO;
import git.bds.nyc.admin.model.vo.CoopUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 成大事
 * @since 2023/1/11 10:19
 */
@Mapper
public interface CoopManageConvert {

    CoopManageConvert INSTANCE = Mappers.getMapper(CoopManageConvert.class);


    /**
     * 到coop用户vo
     *
     * @param list 列表
     * @return {@link List}<{@link CoopUserVO}>
     */
    List<CoopUserVO> toCoopUserVO(List<CoopUserDTO> list);
}
