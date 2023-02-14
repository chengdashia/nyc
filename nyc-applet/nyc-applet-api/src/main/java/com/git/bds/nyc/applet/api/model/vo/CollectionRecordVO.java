package com.git.bds.nyc.applet.api.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author 成大事
 * @since 2023/2/14 20:50
 */
@Getter
@Setter
public class CollectionRecordVO implements Serializable {

    private static final long serialVersionUID = 1L;

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
