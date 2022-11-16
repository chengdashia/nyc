package com.git.bds.nyc.user.mapper.mp;

import com.git.bds.nyc.user.domain.User;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 成大事
 * @since 2022-08-14 19:28:00
 */
@Mapper
public interface UserMapper extends MPJBaseMapper<User> {

}
