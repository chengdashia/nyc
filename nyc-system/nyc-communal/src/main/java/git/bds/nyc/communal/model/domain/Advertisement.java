package git.bds.nyc.communal.model.domain;

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
 * 
 * </p>
 *
 * @author 成大事
 * @since 2023-01-04 15:47:52
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("advertisement")
@ApiModel(value = "Advertisement对象", description = "")
public class Advertisement extends Model<Advertisement> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("广告title")
    @TableField("title")
    private String title;

    @ApiModelProperty("状态（0：禁用；1:在用）")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("图片地址")
    @TableField("picture_url")
    private String pictureUrl;

    @ApiModelProperty("添加时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    public static final String ID = "id";

    public static final String TITLE = "title";

    public static final String STATUS = "status";

    public static final String PICTURE_URL = "picture_url";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
