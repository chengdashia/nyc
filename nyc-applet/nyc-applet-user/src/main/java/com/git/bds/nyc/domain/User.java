package com.git.bds.nyc.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 成大事
 * @since 2022-08-14 19:28:00
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "User对象", description = "用户表")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("微信用户的openid")
    @TableField("user_openid")
    private String openid;

    @ApiModelProperty("用户昵称")
    @TableField("user_screen_name")
    private String screenName;


    @ApiModelProperty("注册时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("最后登录时间")
    @TableField("login_time")
    private LocalDateTime loginTime;

    public static final String USER_ID = "user_id";

    public static final String USER_OPENID = "user_openid";

    public static final String USER_SCREEN_NAME = "user_screen_name";


    public static final String CREATE_TIME = "create_time";

    public static final String LOGIN_TIME = "login_time";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
