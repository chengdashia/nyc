package git.bds.nyc.demand.model.domain;

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
 * 需求收藏表
 * </p>
 *
 * @author 成大事
 * @since 2022-10-31 15:59:15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("demand_collection")
@ApiModel(value = "DemandCollection对象", description = "需求收藏表")
public class DemandCollection extends Model<DemandCollection> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("收藏id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("收藏人的id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("收藏的需求的id")
    @TableField("demand_id")
    private Long demandId;

    @ApiModelProperty("收藏时间")
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
