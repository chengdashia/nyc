package com.git.bds.nyc.user.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 成大事
 * @since 2022/11/14 17:53
 */
@Data
public class UserDTO implements Serializable {

    /** id */
    private Long id;

    /** 昵称 */
    private String screenName;

    /** 头像 */
    private String avatar;

    /** 角色名称 */
    private String roleName;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 登录时间 */
    private LocalDateTime loginTime;

}
