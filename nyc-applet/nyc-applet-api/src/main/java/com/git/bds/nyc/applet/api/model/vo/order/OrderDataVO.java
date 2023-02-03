package com.git.bds.nyc.applet.api.model.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 成大事
 * @since 2023/2/3 17:15
 */
@Getter
@Setter
@ApiModel("订单的各项数据")
public class OrderDataVO implements Serializable {

    @ApiModelProperty("未签字")
    private Integer unSigned;

    @ApiModelProperty("签署")
    private Integer signed;

    @ApiModelProperty("拒绝签字")
    private Integer refuseToSign;
    @ApiModelProperty("成功交易")
    private Integer successfulTrade;
}
