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
public class ProductCollectAndHistoryDTO {

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
    private String coverImg;


    /** 产品重量 */
    private BigDecimal weight;

    /** 产品生产区 */
    private String area;

    /** 收藏时间 */
    private LocalDateTime collectionTime;


    public static final String PRODUCT_ID = "id";
    public static final String TYPE = "type";
    public static final String PRICE = "price";
    public static final String SPECIES = "species";
    public static final String AREA = "area";
    public static final String WEIGHT = "weight";
    public static final String VARIETIES = "varieties";
    public static final String COVER_URL = "coverImg";
    public static final String COLLECTION_TIME = "collectionTime";

}
