package com.git.bds.nyc.applet.api.model.vo.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author 成大事
 * @since 2023/2/15 16:49
 */
@Getter
@Setter
public class ReleasePreSellPrimaryProductVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品id")
    private Long id;

    @ApiModelProperty("种类")
    private String productSpecies;

    @ApiModelProperty("品种")
    private String productVariety;

    @ApiModelProperty("商品重量")
    private BigDecimal productWeight;

    @ApiModelProperty("商品单价")
    private BigDecimal productPrice;

    @ApiModelProperty("商品的封面图")
    private String productCover;

    @ApiModelProperty("发布时间")
    private LocalDateTime createTime;

    @ApiModelProperty("上市时间")
    private LocalDateTime marketTime;
}
