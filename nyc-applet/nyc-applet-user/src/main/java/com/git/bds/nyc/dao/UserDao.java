package com.git.bds.nyc.dao;

import com.git.bds.nyc.domain.User;
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
public interface UserDao extends MPJBaseMapper<User> {

}
