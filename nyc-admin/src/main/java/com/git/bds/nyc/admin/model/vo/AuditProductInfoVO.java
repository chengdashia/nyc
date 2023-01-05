package com.git.bds.nyc.admin.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 成大事
 * @since 2023/1/5 16:26
 */
@Data
public class AuditProductInfoVO implements Serializable {

    @ApiModelProperty("联系信息(即发布人的联系方式，发货地址等)")
    private Long contactInfoId;

    @ApiModelProperty("产品种类")
    private String productSpecies;

    @ApiModelProperty("产品种类")
    private String productVariety;

    @ApiModelProperty("产品重量")
    private BigDecimal productWeight;

    @ApiModelProperty("产品价格")
    private BigDecimal productPrice;

    @ApiModelProperty("产品生产区")
    private String productProductionArea;

    @ApiModelProperty("产品封面")
    private String productCover;

    @ApiModelProperty("产品备注")
    private String productRemark;

    @ApiModelProperty("产品状态")
    private Integer productStatus;

    @ApiModelProperty("发布时间")
    private LocalDateTime createTime;

    @ApiModelProperty("图片url")
    private String pictureUrl;

    @ApiModelProperty("img列表")
    private List<String> imgList;
}
