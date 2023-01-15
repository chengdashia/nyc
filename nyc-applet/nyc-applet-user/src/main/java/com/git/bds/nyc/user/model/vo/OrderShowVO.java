package com.git.bds.nyc.user.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author 成大事
 * @since 2023/1/15 13:50
 */
@Getter
@Setter
public class OrderShowVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单id")
    private Long id;

    @ApiModelProperty("产品id")
    private Long productId;

    @ApiModelProperty("产品品种")
    private String productSpecies;

    @ApiModelProperty("产品种类")
    private String productVarieties;

    @ApiModelProperty("订单重量")
    private BigDecimal orderWeight;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}
