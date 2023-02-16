package git.bds.nyc.corp.model.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: chnnc
 * @Date: 2022/11/06/14:47
 * @Description: 用于公司产品返回数据
 */
@ApiModel(value = "用于公司产品返回数据")
@Data
@Accessors(chain = true)
public class AuditCorpProductVO implements Serializable {

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

    @ApiModelProperty("商品的种类(0：初级；1：加工)")
    @TableField("product_status")
    private Integer productStatus;

    @ApiModelProperty("审核状态(-1：未审核；0：不通过；1：审核通过)")
    @TableField("audit_status")
    private Integer auditStatus;

    @ApiModelProperty("审核不通过的备注")
    @TableField("audit_remark")
    private String auditRemark;

    @ApiModelProperty("提交审核时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("审核时间")
    @TableField("audit_time")
    private LocalDateTime auditTime;

    @ApiModelProperty("提交次数")
    @TableField("apply_times")
    private Integer applyTimes;

}
