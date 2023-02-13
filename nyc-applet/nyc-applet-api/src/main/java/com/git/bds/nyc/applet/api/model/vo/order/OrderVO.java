package com.git.bds.nyc.applet.api.model.vo.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author 成大事
 * @since 2023/2/3 16:32
 */
@Data
public class OrderVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单id")
    private Long id;

    @ApiModelProperty("产品id")
    private Long productId;

    @ApiModelProperty("产品品种")
    private String productSpecies;

    @ApiModelProperty("产品种类")
    private String productVariety;

    @ApiModelProperty("产品封面图")
    private String productCover;

    @ApiModelProperty("订单重量")
    private BigDecimal orderWeight;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("类型（0:农户初级、1:公司初级、2:公司加工、3:农户需求、4:公司需求）")
    private Integer type;
}
