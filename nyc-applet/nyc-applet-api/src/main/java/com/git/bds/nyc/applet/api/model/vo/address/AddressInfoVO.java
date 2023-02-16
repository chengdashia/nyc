package com.git.bds.nyc.applet.api.model.vo.address;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 成大事
 * @since 2023/2/13 17:02
 */
@Getter
@Setter
public class AddressInfoVO implements Serializable {


    private static final long serialVersionUID = 1L;

    @ApiModelProperty("收货人")
    private String consignee;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("所在地区(一般是地位到县级)")
    private String location;

    @ApiModelProperty("详细地址(在所在地区的基础上)")
    private String detailedAddress;

}
