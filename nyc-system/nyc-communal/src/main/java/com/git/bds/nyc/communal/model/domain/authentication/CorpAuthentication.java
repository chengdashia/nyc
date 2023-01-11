package com.git.bds.nyc.communal.model.domain.authentication;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 公司认证表
 * </p>
 *
 * @author 成大事
 * @since 2023-01-11 10:06:59
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("corp_authentication")
@ApiModel(value = "CorpAuthentication对象", description = "公司认证表")
public class CorpAuthentication extends Model<CorpAuthentication> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("认证表的id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("负责人姓名")
    @TableField("real_name")
    private String realName;


    public static final String ID = "id";

    public static final String REAL_NAME = "real_name";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
