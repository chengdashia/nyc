package com.git.bds.nyc.admin.service.coop;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.admin.mapper.mp.CoopUserMapper;
import com.git.bds.nyc.admin.model.domain.CoopUser;
import com.git.bds.nyc.admin.model.dto.CoopUserDTO;
import com.git.bds.nyc.communal.model.domain.authentication.FarmerAuthentication;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.user.model.domain.User;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
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

    /**
     * 通过合作社id获取下级农民
     *
     * @param pageParam
     * @param id        合作社id
     * @return {@link PageResult}<{@link CoopUserDTO}>
     */
    @Override
    public PageResult<CoopUserDTO> getSubordinateFarmerByCoopId(PageParam pageParam, Long id) {

        IPage<CoopUserDTO> page = this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                CoopUserDTO.class,
                new MPJLambdaWrapper<CoopUserDTO>()
                        .select(CoopUser::getUserId)
                        .select(User::getAvatar, User::getLoginTime)
                        .select(FarmerAuthentication::getRealName, FarmerAuthentication::getIdCartNum)
                        .leftJoin(User.class, User::getId, CoopUser::getUserId)
                        .leftJoin(FarmerAuthentication.class, FarmerAuthentication::getUserId, CoopUser::getUserId)
                        .eq(CoopUser::getCoopId, id));
        return new PageResult<>(page.getRecords(),page.getTotal());
    }
}
