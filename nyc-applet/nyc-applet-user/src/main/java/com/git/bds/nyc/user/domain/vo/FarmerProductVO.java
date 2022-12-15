package com.git.bds.nyc.user.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author 成大事
 * @since 2022/10/26 14:24
 */
@Data
public class FarmerProductVO implements Serializable {

    @ApiModelProperty("产品id")
    private Long productId;
    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("商品重量")
    private BigDecimal weight;

    @ApiModelProperty("商品单价")
    private BigDecimal price;

    @ApiModelProperty("商品的生产地区")
    private String area;

    @ApiModelProperty("种类")
    private String species;

    @ApiModelProperty("品种")
    private String varieties;

    @ApiModelProperty("封面图")
    private String coverImg;

    @ApiModelProperty("收藏时间")
    private LocalDateTime collectionTime;
}
