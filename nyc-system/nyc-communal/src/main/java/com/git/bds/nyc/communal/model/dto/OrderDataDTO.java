package com.git.bds.nyc.communal.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 成大事
 * @since 2023/2/3 17:10
 */
@Getter
@Setter
@ToString
public class OrderDataDTO implements Serializable {


    /** 未签字 */
    private Integer unSigned;

    /** 签署 */
    private Integer signed;

    /** 拒绝签字 */
    private Integer refuseToSign;

    /** 成功交易 */
    private Integer successfulTrade;
}
