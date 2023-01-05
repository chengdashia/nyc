package com.git.bds.nyc.demand.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 需求表
 * </p>
 *
 * @author 成大事
 * @since 2022-10-31 15:59:15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("corp_demand")
@ApiModel(value = "Demand对象", description = "需求表")
public class CorpDemand extends Model<CorpDemand> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("需求的id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("发布用户的id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("需求的种类")
    @TableField("demand_species")
    private String demandSpecies;

    @ApiModelProperty("需求的品种")
    @TableField("demand_variety")
    private String demandVariety;

    @ApiModelProperty("需求的重量")
    @TableField("demand_weight")
    private BigDecimal demandWeight;

    @ApiModelProperty("价格")
    @TableField("demand_price")
    private BigDecimal demandPrice;

    @ApiModelProperty("详细地址")
    @TableField("detailed_address")
    private String detailedAddress;

    @ApiModelProperty("需求封面")
    @TableField("demand_cover")
    private String demandCover;

    @ApiModelProperty("备注")
    @TableField("demand_remark")
    private String demandRemark;

    @ApiModelProperty("审核状态(-1：未审核；0：不通过；1：审核通过)")
    @TableField("audit_status")
    private Integer auditStatus;

    @ApiModelProperty("发布时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("截止日期")
    @TableField("expiration_date")
    private LocalDateTime expirationDate;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String DEMAND_SPECIES = "demand_species";

    public static final String DEMAND_VARIETY = "demand_variety";

    public static final String DEMAND_WEIGHT = "demand_weight";

    public static final String DEMAND_PRICE = "demand_price";

    public static final String DETAILED_ADDRESS = "detailed_address";

    public static final String DEMAND_COVER = "demand_cover";

    public static final String DEMAND_REMARK = "demand_remark";

    public static final String AUDIT_STATUS = "audit_status";

    public static final String CREATE_TIME = "create_time";

    public static final String EXPIRATION_DATE = "expiration_date";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
