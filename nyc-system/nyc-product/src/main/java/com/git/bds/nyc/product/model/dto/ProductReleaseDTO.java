package com.git.bds.nyc.product.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author 成大事
 * @since 2022/10/19 10:45
 * 用于查看自己发布的农产品
 */
@Data
public class ProductReleaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**商品id**/
    private Long id;

    /** 产品种类 */
    private String productSpecies;

    /** 产品种类 */
    private String productVariety;

    /** 产品重量 */
    private BigDecimal productWeight;

    /** 产品价格 */
    private BigDecimal productPrice;

    /** 合作社审核状态(-1：未审核；0：不通过；1：审核通过) */
    private Integer coopAuditStatus;


    /** 供销社审核状态(-1：未审核；0：不通过；1：审核通过) */
    private Integer auditStatus;

    /** 产品封面 */
    private String productCover;

    /** 发布时间 */
    private LocalDateTime createTime;

    /** 上市时间 */
    private LocalDateTime marketTime;


}
