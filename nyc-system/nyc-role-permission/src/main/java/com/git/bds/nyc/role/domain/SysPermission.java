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
@TableName("sys_permission")
@ApiModel(value = "SysPermission对象", description = "")
public class SysPermission extends Model<SysPermission> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("权限名称")
    @TableField("permission_name")
    private String permissionName;

    @ApiModelProperty("权限描述")
    @TableField("description")
    private String description;


    public static final String ID = "id";

    public static final String PERMISSION_NAME = "permission_name";

    public static final String DESCRIPTION = "description";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
