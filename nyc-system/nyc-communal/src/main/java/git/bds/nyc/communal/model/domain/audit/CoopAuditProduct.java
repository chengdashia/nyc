package git.bds.nyc.communal.model.domain.audit;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 合作社审核个人发布的农产品(只有初级农产品)
 * </p>
 *
 * @author 成大事
 * @since 2022-11-23 10:43:48
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("coop_audit_product")
@ApiModel(value = "CoopAuditProduct对象", description = "合作社审核个人发布的农产品(只有初级农产品)")
public class CoopAuditProduct extends Model<CoopAuditProduct> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("审核的id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("用户的id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("商品的id")
    @TableField("product_id")
    private Long productId;

    @ApiModelProperty("审核状态(-1：未审核；0：不通过；1：审核通过；)")
    @TableField("audit_status")
    private Integer auditStatus;

    @ApiModelProperty("提交次数")
    @TableField("apply_times")
    private Integer applyTimes;

    @ApiModelProperty("审核不通过的备注")
    @TableField("audit_remark")
    private String auditRemark;

    @ApiModelProperty("提交审核时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("审核时间")
    @TableField(value = "audit_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime auditTime;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String PRODUCT_ID = "product_id";

    public static final String AUDIT_STATUS = "audit_status";

    public static final String APPLY_TIMES = "apply_times";

    public static final String AUDIT_REMARK = "audit_remark";

    public static final String CREATE_TIME = "create_time";

    public static final String AUDIT_TIME = "audit_time";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
