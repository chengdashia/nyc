package com.git.bds.nyc.admin.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 成大事
 * @since 2023/1/4 15:51
 */
@Data
@ApiModel("广告")
public class AdvertisementVO implements Serializable {

    @ApiModelProperty("广告id")
    private Long id;

    @ApiModelProperty("广告的title")
    private String title;

    @ApiModelProperty("状态（0：禁用；1:在用）")
    private Integer status;

    @ApiModelProperty("广告的图片地址")
    private String pictureUrl;

    @ApiModelProperty("发布时间")
    private LocalDateTime createTime;
}
