package com.git.bds.nyc.admin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 成大事
 * @since 2023/1/5 17:47
 */
@Data
public class AuditStatusDTO implements Serializable {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("商品id or 需求id")
    private Long goodsId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("是否通过")
    @Min(-1)
    @Max(1)
    private Integer status;

    @NotNull
    @ApiModelProperty("备注")
    private String remark;


}
