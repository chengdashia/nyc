package com.git.bds.nyc.product.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author 成大事
 * @since 2022/10/26 11:06
 * 用于连表查询 收藏记录的
 */
@Data
public class ProductCollectionDTO {

    /** 产品id */
    private Long productId;

    /** 类型 */
    private Integer type;

    /** 价格 */
    private BigDecimal price;

    /** 种 */
    private String species;

    /** 品种 */
    private String varieties;

    /** 封面图 */
    private String imgUrl;

    /** 收藏时间 */
    private LocalDateTime collectionTime;

}
