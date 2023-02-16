package git.bds.nyc.applet.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 成大事
 * @since 2023/2/8 14:31
 */
@Getter
@Setter
public class NumberOfReleaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 总数  */
    private Integer totalNum;

    /** 在售数量 */
    private Integer onSellNum;

    /** 预售数量 */
    private Integer preSellNum;

    /** 审核数量 */
    private Integer auditNum;
}
