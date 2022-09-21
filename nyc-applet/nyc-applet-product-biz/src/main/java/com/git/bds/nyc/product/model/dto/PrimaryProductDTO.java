package com.git.bds.nyc.product.model.dto;


import com.git.bds.nyc.product.valid.ValidGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 成大事
 * @since 2022/9/14 18:48
 * 用户发布商品
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrimaryProductDTO {

    @NotNull(groups = {ValidGroup.PreSale.class,ValidGroup.OnSell.class})
    @ApiModelProperty("联系信息(即发布人的联系方式，发货地址等)")
    private Long contactInfoId;

    @NotNull(groups = {ValidGroup.PreSale.class,ValidGroup.OnSell.class})
    @NotBlank(message = "种类名不能为空",groups = {ValidGroup.PreSale.class,ValidGroup.OnSell.class})
    @ApiModelProperty("种类")
    private String productSpecies;

    @NotNull(groups = {ValidGroup.PreSale.class,ValidGroup.OnSell.class})
    @NotBlank(message = "种类名不能为空",groups = {ValidGroup.PreSale.class,ValidGroup.OnSell.class})
    @ApiModelProperty("品种")
    private String productVariety;

    @ApiModelProperty("商品重量")
    @Digits(integer = 10,fraction = 2,message = "整数上限为10位，小数上限为2位",groups = {ValidGroup.PreSale.class,ValidGroup.OnSell.class})
    private BigDecimal productWeight;

    @Digits(integer = 10,fraction = 2,message = "整数上限为10位，小数上限为2位",groups = {ValidGroup.PreSale.class,ValidGroup.OnSell.class})
    @ApiModelProperty("商品单价")
    private BigDecimal productPrice;

    @NotNull(groups = {ValidGroup.PreSale.class,ValidGroup.OnSell.class})
    @NotBlank(message = "种类名不能为空",groups = {ValidGroup.PreSale.class,ValidGroup.OnSell.class})
    @ApiModelProperty("商品的生产地区")
    private String productProductionArea;

    @Size(min = 1,max = 10,message = "图片列表有误",groups = {ValidGroup.OnSell.class,ValidGroup.PreSale.class})
    @ApiModelProperty("商品的封面图")
    private List<String> productImgList;

    @ApiModelProperty("商品的备注")
    private String productRemark;

    @NotNull(groups = {ValidGroup.PreSale.class,ValidGroup.OnSell.class})
    @Min(value = 0,message = "状态有误",groups = {ValidGroup.OnSell.class,ValidGroup.PreSale.class})
    @Max(value = 1,message = "状态有误",groups = {ValidGroup.OnSell.class,ValidGroup.PreSale.class})
    @ApiModelProperty("状态（0：在售  1：预售）")
    private Integer productStatus;

    @Future(groups = {ValidGroup.PreSale.class})
    @ApiModelProperty("上市时间，用于预售")
    private LocalDateTime marketTime;

}
