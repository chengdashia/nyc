package com.git.bds.nyc.coop.service.impl;

import com.git.bds.nyc.coop.service.CoopUserService;
import com.git.bds.nyc.coop.model.domain.CoopUser;
import com.git.bds.nyc.coop.mapper.CoopUserMapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 合作社和农户关系表 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2023-01-06 15:02:50
 */
@Service
public class CoopUserServiceImpl extends MPJBaseServiceImpl<CoopUserMapper, CoopUser> implements CoopUserService {

}
