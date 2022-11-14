package com.git.bds.nyc.admin.service;

import com.git.bds.nyc.admin.model.domain.SysAdmin;
import com.github.yulichang.base.MPJBaseService;

/**
 * <p>
 * 管理员表，用来登录web端。后台管理 服务类
 * </p>
 *
 * @author 成大事
 * @since 2022-11-14 16:53:41
 */
public interface SysAdminService extends MPJBaseService<SysAdmin> {

    /**
     * 通过密码登录
     *
     * @param account 账户
     * @param pwd     密码
     * @return {@link String}
     */
    String loginByPwd(String account, String pwd);
}
