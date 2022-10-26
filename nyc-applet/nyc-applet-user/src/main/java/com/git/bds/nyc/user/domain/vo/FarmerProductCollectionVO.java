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
public class FarmerProductCollectionVO implements Serializable {

    @ApiModelProperty("产品id")
    private Long productId;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("种")
    private String species;

    @ApiModelProperty("品种")
    private String varieties;

    @ApiModelProperty("封面图")
    private String imgUrl;

    @ApiModelProperty("收藏时间")
    private LocalDateTime collectionTime;
}
