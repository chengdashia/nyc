package com.git.bds.nyc.user.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 成大事
 * @since 2022/7/27 16:06
 */
@Data
public class WxUserInfoDTO implements Serializable {

    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("会话密钥")
    private String sessionKey;

    @ApiModelProperty("签名信息")
    private String signature;

    @ApiModelProperty("非敏感的用户信息")
    private String rawData;

    @ApiModelProperty("加密的数据")
    private String encryptedData;

    @ApiModelProperty("加密密钥")
    private String iv;
}
