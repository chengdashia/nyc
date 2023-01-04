package com.git.bds.nyc.applet.api.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 成大事
 * @since 2023/1/4 20:38
 */
@Data
public class IndexAdvertisementVO implements Serializable {

    @ApiModelProperty("广告id")
    private Long id;

    @ApiModelProperty("广告的title")
    private String title;

    @ApiModelProperty("广告的图片地址")
    private String pictureUrl;

}
