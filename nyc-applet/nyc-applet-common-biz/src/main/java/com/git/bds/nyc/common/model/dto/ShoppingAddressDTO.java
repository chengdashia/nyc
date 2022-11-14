package com.git.bds.nyc.common.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 成大事
 * @since 2022/11/14 15:40
 */
@Data
public class ShoppingAddressDTO implements Serializable {

    /**地址id*/
    private Long id;

    /**收货人*/
    private String consignee;

    /**手机号*/
    private String phone;

    /**所在地区(一般是地位到县级)*/
    private String location;

    /**详细地址(在所在地区的基础上)*/
    private String detailedAddress;

    /**默认(0:非默认；1:默认)*/
    private Integer isDefault;
}
