package com.git.bds.nyc.communal.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 成大事
 * @since 2023/1/5 15:08
 * 待定审核的商品的
 */
@Setter
@Getter
public class AuditProductDTO implements Serializable {


    /** 审核的id */
    private Long id;

    /** 产品id */
    private Long productId;

    /** 提交次数 */
    private Integer applyTimes;

    /** 提交审核时间 */
    private LocalDateTime createTime;

    /** 产品种类 */
    private String productSpecies;

    /** 产品种类 */
    private String productVariety;

    /** 商品的封面图 */
    private String productCover;

}
