package git.bds.nyc.admin.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 成大事
 * @since 2022/11/14 17:50
 */
@Data
public class UserVO implements Serializable {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("昵称")
    private String screenName;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("登录时间")
    private LocalDateTime loginTime;
}
