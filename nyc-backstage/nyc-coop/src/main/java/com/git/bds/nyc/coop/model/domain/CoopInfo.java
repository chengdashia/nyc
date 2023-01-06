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
 * 合作社信息表
 * </p>
 *
 * @author 成大事
 * @since 2023-01-06 15:02:50
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("coop_info")
@ApiModel(value = "CoopInfo对象", description = "合作社信息表")
public class CoopInfo extends Model<CoopInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("合作社id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("合作社名称")
    @TableField("coop_name")
    private String coopName;

    @ApiModelProperty("合作社地址")
    @TableField("coop_address")
    private String coopAddress;

    @ApiModelProperty("合作社信息")
    @TableField("coop_info")
    private String coopInfo;


    public static final String ID = "id";

    public static final String COOP_NAME = "coop_name";

    public static final String COOP_ADDRESS = "coop_address";

    public static final String COOP_INFO = "coop_info";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
