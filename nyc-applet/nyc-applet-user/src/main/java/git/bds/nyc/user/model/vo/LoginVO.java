package git.bds.nyc.user.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 成大事
 * @since 2023/1/4 16:29
 */
@Data
@ApiModel("登录成功后的vo")
public class LoginVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("昵称")
    private String screenName;

    @ApiModelProperty("头像地址")
    private String avatar;
}
