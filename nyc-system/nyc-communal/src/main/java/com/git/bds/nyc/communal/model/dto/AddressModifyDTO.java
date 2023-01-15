package com.git.bds.nyc.communal.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 成大事
 * @since 2022/11/26 19:25
 */
@Setter
@Getter
public class AddressModifyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("地址id")
    private Long id;

    @ApiModelProperty("收货人")
    private String consignee;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("所在地区(一般是地位到县级)")
    private String location;

    @ApiModelProperty("详细地址(在所在地区的基础上)")
    private String detailedAddress;

    @ApiModelProperty("默认(0:非默认；1:默认)")
    @TableField("is_default")
    private Integer isDefault;
}
