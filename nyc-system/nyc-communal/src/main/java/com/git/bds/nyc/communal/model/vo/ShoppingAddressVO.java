package com.git.bds.nyc.communal.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 成大事
 * @since 2022/11/14 15:35
 */
@Data
public class ShoppingAddressVO implements Serializable {

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
    private Integer isDefault;
}
