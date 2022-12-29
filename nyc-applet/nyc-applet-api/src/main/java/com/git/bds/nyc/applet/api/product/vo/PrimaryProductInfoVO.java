package com.git.bds.nyc.applet.api.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 成大事
 * @since 2022/9/6 23:08
 */
@Data
public class PrimaryProductInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品id")
    private Long id;

    @ApiModelProperty("联系信息(即发布人的联系方式，发货地址等)")
    private Long contactInfoId;

    @ApiModelProperty("种类")
    private String productSpecies;

    @ApiModelProperty("品种")
    private String productVariety;

    @ApiModelProperty("商品重量")
    private BigDecimal productWeight;

    @ApiModelProperty("商品单价")
    private BigDecimal productPrice;

    @ApiModelProperty("商品的生产地区")
    private String productProductionArea;

    @ApiModelProperty("商品的封面图")
    private String productCover;

    @ApiModelProperty("商品的备注")
    private String productRemark;

    @ApiModelProperty("状态（0：在售  1：预售）")
    private Integer productStatus;

    @ApiModelProperty("商品的发布时间")
    private LocalDateTime createTime;

    @ApiModelProperty("商品图片列表")
    private List<String> imgList;

    @ApiModelProperty("是否收藏  0:未收藏 1:已收藏")
    private Integer isCollection;



}
