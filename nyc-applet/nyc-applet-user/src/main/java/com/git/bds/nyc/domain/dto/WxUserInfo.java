package com.git.bds.nyc.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 成大事
 * @since 2022/7/27 16:06
 */
@Data
@Accessors(chain = true)
public class WxUserInfo implements Serializable {
    private String appid;
    private String sessionKey;
    /**
     * 签名信息
     */
    private String signature;
    /**
     * 非敏感的用户信息
     */
    private String rawData;
    /**
     * 加密的数据
     */
    private String encryptedData;
    /**
     * 加密密钥
     */
    private String iv;
}
