package com.git.bds.nyc.communal.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 成大事
 * @since 2023/1/2 14:17
 * 订单信息表
 */
@Setter
@Getter
@ApiModel("订单的model")
public class OrderDTO implements Serializable {

    @NotNull
    @NotBlank(message = "签名不能为空！！！")
    @ApiModelProperty("买家的签名 base64")
    private String buyerSignature;

    @NotNull
    @ApiModelProperty("产品的id")
    private Long productId;

    @NotNull
    @ApiModelProperty("买家联系信息id")
    private Long buyerContactInfoId;
    @NotNull
    @Digits(integer = 10,fraction = 2)
    @ApiModelProperty("下单重量")
    private BigDecimal orderWeight;

    @ApiModelProperty("备注，可以没有")
    private String remark;

    @NotNull
    @Min(0)
    @Max(3)
    @ApiModelProperty("类型 农户初级农产品(0) 公司初级农产品(1) 公司加工农产品(2) 农户需求(3) 公司需求(4)")
    private Integer type;

}
