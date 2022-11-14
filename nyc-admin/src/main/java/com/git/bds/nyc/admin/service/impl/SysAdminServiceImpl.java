package com.git.bds.nyc.admin.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.admin.mapper.SysAdminMapper;
import com.git.bds.nyc.admin.model.domain.SysAdmin;
import com.git.bds.nyc.admin.service.SysAdminService;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.result.ResultCode;
import com.git.bds.nyc.role.domain.SysRole;
import com.git.bds.nyc.role.domain.SysUserRole;
import com.git.bds.nyc.user.domain.User;
import com.git.bds.nyc.user.domain.dto.UserDTO;
import com.git.bds.nyc.user.mapper.user.UserMapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>
 * 管理员表，用来登录web端。后台管理 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-11-14 16:53:41
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SysAdminServiceImpl extends MPJBaseServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {

    private final UserMapper userMapper;

    /**
     * 通过密码登录
     *
     * @param account 账户
     * @param password     密码
     * @return {@link String}
     */
    @Override
    public String loginByPwd(String account, String password) {
        SysAdmin admin = this.baseMapper.selectOne(new QueryWrapper<SysAdmin>()
                .select(SysAdmin.ID, SysAdmin.PASSWORD)
                .eq(SysAdmin.ACCOUNT, account));
        admin = Optional.ofNullable(admin)
                .orElseThrow(() -> new BusinessException(ResultCode.NOT_EXIST.getCode(), ResultCode.NOT_EXIST.getMessage()));
        if(admin.getPassword().equals(password)){
            StpUtil.login(admin.getId());
            return StpUtil.getTokenValue();
        }else {
            throw new BusinessException(ResultCode.PWD_ERROR.getCode(), ResultCode.PWD_ERROR.getMessage());
        }
    }

    /**
     * 按页面获取用户
     *
     * @param pageParam 页面参数
     * @return {@link PageResult}<{@link UserDTO}>
     */
    @Override
    public PageResult<UserDTO> getUserByPage(PageParam pageParam) {
        IPage<UserDTO> page = userMapper.selectJoinPage(new Page<>(pageParam.getPageNo(),
                        pageParam.getPageSize()), UserDTO.class,
                new MPJLambdaWrapper<>()
                        .select(User::getId, User::getAvatar, User::getCreateTime, User::getLoginTime)
                        .select(SysRole::getRoleName)
                        .leftJoin(SysUserRole.class, SysUserRole::getUserId, User::getId)
                        .leftJoin(SysRole.class, SysRole::getId, SysUserRole::getRoleId));
        log.info("page  "+page);
        return new PageResult<>(page.getRecords(),page.getCurrent());
    }
}
