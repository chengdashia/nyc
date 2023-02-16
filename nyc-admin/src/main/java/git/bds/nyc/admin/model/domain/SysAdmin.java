package git.bds.nyc.admin.model.domain;

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
 * 管理员表，用来登录web端。后台管理
 * </p>
 *
 * @author 成大事
 * @since 2022-11-14 16:53:41
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_admin")
@ApiModel(value = "SysAdmin对象", description = "管理员表，用来登录web端。后台管理")
public class SysAdmin extends Model<SysAdmin> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("管理员id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("账号")
    @TableField("account")
    private String account;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("注册时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("登录时间")
    @TableField("login_time")
    private LocalDateTime loginTime;


    public static final String ID = "id";

    public static final String ACCOUNT = "account";

    public static final String PASSWORD = "password";

    public static final String CREATE_TIME = "create_time";

    public static final String LOGIN_TIME = "login_time";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
