package com.git.bds.nyc.role.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 成大事
 * @since 2022-08-17 20:46:59
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_role_permission")
@ApiModel(value = "SysRolePermission对象", description = "")
public class SysRolePermission extends Model<SysRolePermission> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("角色id")
    @TableField("role_id")
    private Integer roleId;

    @ApiModelProperty("权限id")
    @TableField("permission_id")
    private Integer permissionId;


    public static final String ID = "id";

    public static final String ROLE_ID = "role_id";

    public static final String PERMISSION_ID = "permission_id";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
