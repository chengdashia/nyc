package com.git.bds.nyc.applet.api.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 成大事
 * @since 2023/2/8 14:31
 */
@Getter
@Setter
@ApiModel("发布数量model")
public class NumberOfReleaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("总数")
    private Integer totalNum;

    @ApiModelProperty("在售数量")
    private Integer onSellNum;

    @ApiModelProperty("预售数量")
    private Integer preSellNum;

    @ApiModelProperty("审核数量")
    private Integer auditNum;
}
