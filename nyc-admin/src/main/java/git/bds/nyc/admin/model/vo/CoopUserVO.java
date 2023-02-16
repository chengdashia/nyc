package git.bds.nyc.admin.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 成大事
 * @since 2023/1/11 9:52
 */
@Data
@ApiModel("合作社 下属的用户列表")
public class CoopUserVO implements Serializable {
    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("登录时间")
    private LocalDateTime loginTime;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("身份证")
    private String idCard;
}
