package com.git.bds.nyc.demand.model.ee;

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
 * @since 2023/1/6 13:07
 */
@Data
@IndexName("demand")
public class DemandEs implements Serializable {

    private static final long serialVersionUID = 1L;

    /**需求id*/
    @IndexId(type = IdType.CUSTOMIZE)
    @IndexField(fieldType = FieldType.LONG)
    private Long id;

    /** 需求品种 */
    @HighLight
    @IndexField(fieldType = FieldType.TEXT,analyzer = Analyzer.IK_MAX_WORD,searchAnalyzer = Analyzer.IK_MAX_WORD)
    private String demandSpecies;

    /** 需求种类 */
    @HighLight
    @IndexField(fieldType = FieldType.TEXT,analyzer = Analyzer.IK_MAX_WORD,searchAnalyzer = Analyzer.IK_MAX_WORD)
    private String demandVariety;

    /** 需求价格 */
    @IndexField(fieldType = FieldType.DOUBLE)
    private BigDecimal demandPrice;

    /** 生产区 */
    @HighLight
    @IndexField(fieldType = FieldType.TEXT,analyzer = Analyzer.IK_MAX_WORD,searchAnalyzer = Analyzer.IK_MAX_WORD)
    private String detailedAddress;

    /** 需求封面 */
    private String demandCover;

    /** 需求状态  0:农户需求 1:公司需求 */
    @IndexField(fieldType = FieldType.INTEGER)
    private Integer demandType;

    /** 创建时间 */
    @IndexField(fieldType = FieldType.DATE,dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
