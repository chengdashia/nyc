package com.git.bds.nyc.applet.api.demand;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author 成大事
 * @since 2022/10/31 16:12
 */
@Data
public class DemandInfoVO implements Serializable {

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

    @ApiModelProperty("备注")
    private String demandRemark;

    @ApiModelProperty("发布时间")
    private LocalDateTime createTime;

    @ApiModelProperty("截止日期")
    private LocalDateTime expirationDate;

}
