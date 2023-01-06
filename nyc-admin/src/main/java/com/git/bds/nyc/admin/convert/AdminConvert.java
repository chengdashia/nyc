package com.git.bds.nyc.admin.convert;

import com.git.bds.nyc.admin.model.vo.UserVO;
import com.git.bds.nyc.user.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 成大事
 * @since 2022/11/14 18:15
 */
@Mapper
public interface AdminConvert {

    AdminConvert INSTANCE = Mappers.getMapper(AdminConvert.class);

    /**
     * toUserVO
     *
     * @param userDTOList 用户数据列表
     * @return {@link List}<{@link UserVO}>
     */
    List<UserVO> toUserVOList(List<UserDTO> userDTOList);
}
