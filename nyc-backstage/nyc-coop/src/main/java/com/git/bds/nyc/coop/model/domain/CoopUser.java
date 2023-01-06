package com.git.bds.nyc.coop.model.domain;

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
 * 合作社和农户关系表
 * </p>
 *
 * @author 成大事
 * @since 2023-01-06 15:02:50
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("coop_user")
@ApiModel(value = "CoopUser对象", description = "合作社和农户关系表")
public class CoopUser extends Model<CoopUser> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("合作社id")
    @TableField("coop_id")
    private Long coopId;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;


    public static final String ID = "id";

    public static final String COOP_ID = "coop_id";

    public static final String USER_ID = "user_id";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
