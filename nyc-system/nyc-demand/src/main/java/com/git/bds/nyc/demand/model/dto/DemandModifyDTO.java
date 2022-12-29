package com.git.bds.nyc.demand.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author 成大事
 * @since 2022/11/2 10:57
 */
@Data
public class DemandModifyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("需求的id")
    private Long id;

    @ApiModelProperty("需求的种类")
    @NotBlank(message = "种类不能为空！")
    private String demandSpecies;

    @ApiModelProperty("需求的品种")
    @NotBlank(message = "品种不能为空！")
    private String demandVariety;

    @ApiModelProperty("需求的重量")
    @Digits(integer = 10,fraction = 2,message = "整数上限为10位，小数上限为2位")
    private BigDecimal demandWeight;

    @ApiModelProperty("价格")
    @Digits(integer = 10,fraction = 2,message = "整数上限为10位，小数上限为2位")
    private BigDecimal demandPrice;

    @ApiModelProperty("详细地址")
    @NotBlank(message = "地址不能为空！")
    private String detailedAddress;

    @ApiModelProperty("需求封面")
    @NotBlank(message = "至少需要一张封面图")
    private String demandCover;

    @ApiModelProperty("备注")
    private String demandRemark;

    @ApiModelProperty("截止日期")
    @Future(message = "截止日期必须是未来的时间！")
    private LocalDateTime expirationDate;
}
