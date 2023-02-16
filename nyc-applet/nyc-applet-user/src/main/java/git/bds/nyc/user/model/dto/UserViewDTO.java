package git.bds.nyc.user.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 成大事
 * @since 2022/12/15 18:35
 */
@Data
public class UserViewDTO implements Serializable {

    @ApiModelProperty("用户微信头像地址")
    @NotNull
    @NotBlank(message = "头像地址不能为空")
    private String avatarUrl;
    @ApiModelProperty("用户微信昵称")
    @NotNull
    @NotBlank(message = "头像地址不能为空")
    private String nickName;
}
