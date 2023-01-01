package com.git.bds.nyc.applet.api.controller.demand.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 成大事
 * @since 2022/10/31 16:11
 */
@Data
public class DemandVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("需求的id")
    private Long id;

    @ApiModelProperty("需求的种类")
    private String demandSpecies;

    @ApiModelProperty("需求的品种")
    private String demandVariety;

    @ApiModelProperty("需求的重量")
    private BigDecimal demandWeight;

    @ApiModelProperty("价格")
    private BigDecimal demandPrice;

    @ApiModelProperty("详细地址")
    private String detailedAddress;

    @ApiModelProperty("需求封面")
    private String demandCover;

}
