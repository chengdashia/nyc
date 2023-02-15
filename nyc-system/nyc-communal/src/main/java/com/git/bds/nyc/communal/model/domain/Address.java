package com.git.bds.nyc.communal.model.domain;

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
 * 收货地址表（也可用于发布商品和需求的时候用作发货地址表）
 * </p>
 *
 * @author 成大事
 * @since 2022-11-14 15:09:55
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("address")
@ApiModel(value = "Address对象", description = "收货地址表（也可用于发布商品和需求的时候用作发货地址表）")
public class Address extends Model<Address> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("地址id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("用户的id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("收货人")
    @TableField("consignee")
    private String consignee;

    @ApiModelProperty("手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("所在地区(一般是地位到县级)")
    @TableField("location")
    private String location;

    @ApiModelProperty("详细地址(在所在地区的基础上)")
    @TableField("detailed_address")
    private String detailedAddress;

    @ApiModelProperty("默认(0:非默认；1:默认)")
    @TableField("is_default")
    private Integer isDefault;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String CONSIGNEE = "consignee";

    public static final String PHONE = "phone";

    public static final String LOCATION = "location";

    public static final String DETAILED_ADDRESS = "detailed_address";

    public static final String isDEFAULT = "is_default";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
