package com.git.bds.nyc.admin.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.git.bds.nyc.admin.mapper.SysAdminMapper;
import com.git.bds.nyc.admin.model.domain.SysAdmin;
import com.git.bds.nyc.admin.service.SysAdminService;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.result.ResultCode;
import com.github.yulichang.base.MPJBaseServiceImpl;
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
@Service
public class SysAdminServiceImpl extends MPJBaseServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {

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
}
