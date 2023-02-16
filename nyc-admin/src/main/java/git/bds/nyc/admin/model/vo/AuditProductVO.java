package git.bds.nyc.admin.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 成大事
 * @since 2023/1/5 15:09
 */
@Data
@ApiModel("审核产品的列表")
public class AuditProductVO implements Serializable {

    @ApiModelProperty("审核的id")
    private Long id;

    @ApiModelProperty("产品id")
    private Long productId;

    @ApiModelProperty("提交次数")
    private Integer applyTimes;

    @ApiModelProperty("提交审核时间")
    private LocalDateTime createTime;

    @ApiModelProperty("产品品种")
    private String productSpecies;

    @ApiModelProperty("产品种类")
    private String productVariety;
    @ApiModelProperty("商品的封面图")
    private String productCover;


}
