package com.git.bds.nyc.product.model.es;

import cn.easyes.annotation.HighLight;
import cn.easyes.annotation.IndexField;
import cn.easyes.annotation.IndexId;
import cn.easyes.annotation.IndexName;
import cn.easyes.common.constants.Analyzer;
import cn.easyes.common.enums.FieldType;
import cn.easyes.common.enums.IdType;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author 成大事
 * @since 2022/11/16 11:34
 */
@Data
@IndexName("product")
public class ProductEs implements Serializable {

    private static final long serialVersionUID = 1L;

    /**产品id*/
    @IndexId(type = IdType.CUSTOMIZE)
    @IndexField(fieldType = FieldType.LONG)
    private Long id;

    /** 产品种类 */
    @HighLight
    @IndexField(fieldType = FieldType.TEXT,analyzer = Analyzer.IK_MAX_WORD,searchAnalyzer = Analyzer.IK_MAX_WORD)
    private String productSpecies;

    /** 产品品类 */
    @HighLight
    @IndexField(fieldType = FieldType.TEXT,analyzer = Analyzer.IK_MAX_WORD,searchAnalyzer = Analyzer.IK_MAX_WORD)
    private String productVariety;

    /** 产品名称 */
    @HighLight
    @IndexField(fieldType = FieldType.TEXT,analyzer = Analyzer.IK_MAX_WORD,searchAnalyzer = Analyzer.IK_MAX_WORD)
    private String productName;

    /** 产品价格 */
    @IndexField(fieldType = FieldType.DOUBLE)
    private BigDecimal productPrice;

    /** 产品生产区 */
    @HighLight
    @IndexField(fieldType = FieldType.TEXT,analyzer = Analyzer.IK_MAX_WORD,searchAnalyzer = Analyzer.IK_MAX_WORD)
    private String productProductionArea;

    /** 产品封面 */
    private String productCover;

    /** 产品状态  0:在售 1:预售 */
    @IndexField(fieldType = FieldType.INTEGER)
    private Integer productStatus;

    /** 创建时间 */
    @IndexField(fieldType = FieldType.DATE,dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /** 市场时间 */
    @IndexField(fieldType = FieldType.DATE,dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime marketTime;

    /** 类型 */
    @IndexField(fieldType = FieldType.INTEGER)
    private Integer type;


    public static final String VARIETY = "productVariety";
    public static final String SPECIES = "productSpecies";
}
