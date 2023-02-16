package git.bds.nyc.admin.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 成大事
 * @since 2023/1/11 9:52
 */
@Data
public class CoopUserDTO {

    /** 用户id */
    private Long userId;

    /** 头像 */
    private String avatar;

    /** 登录时间 */
    private LocalDateTime loginTime;

    /** 真实姓名 */
    private String realName;

    /** 身份证 */
    private String idCard;
}
