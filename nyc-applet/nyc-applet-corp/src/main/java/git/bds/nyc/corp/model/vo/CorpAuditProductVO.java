package git.bds.nyc.corp.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author 成大事
 * @since 2023/2/7 16:44
 */
@Data
public class CorpAuditProductVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品id")
    private Long id;

    @ApiModelProperty("种类")
    private String productSpecies;

    @ApiModelProperty("品种")
    private String productVariety;

    @ApiModelProperty("商品重量")
    private BigDecimal productWeight;

    @ApiModelProperty("商品单价")
    private BigDecimal productPrice;

    @ApiModelProperty("商品的封面图")
    private String productCover;

    @ApiModelProperty("供销社审核状态(-1：未审核；0：不通过；1：审核通过)")
    private Integer auditStatus;

    @ApiModelProperty("发布时间")
    private LocalDateTime createTime;
}
