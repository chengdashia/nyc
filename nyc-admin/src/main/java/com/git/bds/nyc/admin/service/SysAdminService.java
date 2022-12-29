package com.git.bds.nyc.admin.service;

import com.git.bds.nyc.admin.model.domain.SysAdmin;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;

import com.git.bds.nyc.user.domain.dto.UserDTO;
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
     * @param password     密码
     * @return {@link String}
     */
    String loginByPwd(String account, String password);

    /**
     * 按页面获取用户
     *
     * @param pageParam 页面参数
     * @return {@link PageResult}<{@link UserDTO}>
     */
    PageResult<UserDTO> getUserByPage(PageParam pageParam);
}
