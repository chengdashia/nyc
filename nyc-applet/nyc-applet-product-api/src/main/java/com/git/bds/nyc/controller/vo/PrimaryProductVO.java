package com.git.bds.nyc.controller.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 成大事
 * @since 2022/9/5 18:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrimaryProductVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("种类")
    @TableField("product_species")
    private String productSpecies;

    @ApiModelProperty("品种")
    @TableField("product_variety")
    private String productVariety;

    @ApiModelProperty("商品重量")
    @TableField("product_weight")
    private BigDecimal productWeight;

    @ApiModelProperty("商品单价")
    @TableField("product_price")
    private BigDecimal productPrice;

    @ApiModelProperty("商品的生产地区")
    @TableField("product_production_area")
    private String productProductionArea;

    @ApiModelProperty("商品的封面图")
    @TableField("product_cover")
    private String productCover;



}
