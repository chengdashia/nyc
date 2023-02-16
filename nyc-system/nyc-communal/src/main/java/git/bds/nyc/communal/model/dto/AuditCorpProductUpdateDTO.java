package git.bds.nyc.communal.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: chnnc
 * @Date: 2022/11/06/13:25
 * @Description:  用于公司产品审核更新
 */
@ApiModel(value = "用于公司产品审核更新(更新时使用)")
@Setter
@Getter
@Accessors(chain = true)
public class AuditCorpProductUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "productId can't be NULL")
    @ApiModelProperty("商品id")
    private Long productId;

    @NotNull(message = "userId can't be NULL")
    @ApiModelProperty("更新人id")
    private Long userId;

    @NotNull(message = "auditStatus can't be NULL")
    @Length(min = 0, max = 1, message = "max length: 36")
    @ApiModelProperty("审核状态，0：不通过；1：审核通过")
    private Long auditStatus;

}
