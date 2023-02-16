package git.bds.nyc.demand.model.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 需求的浏览记录表
 * </p>
 *
 * @author 成大事
 * @since 2022-10-31 15:59:15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("demand_history")
@ApiModel(value = "DemandHistory对象", description = "需求的浏览记录表")
public class DemandHistory extends Model<DemandHistory> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("浏览记录id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("浏览用户的id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("浏览的需求的id")
    @TableField("demand_id")
    private Long demandId;

    @ApiModelProperty("浏览时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String DEMAND_ID = "demand_id";

    public static final String CREATE_TIME = "create_time";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
