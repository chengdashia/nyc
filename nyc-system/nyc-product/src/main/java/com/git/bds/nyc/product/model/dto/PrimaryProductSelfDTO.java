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
public class PrimaryProductSelfDTO implements Serializable {

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

    /** 产品封面 */
    private String productCover;

    /** 创建时间 */
    private LocalDateTime createTime;


}
