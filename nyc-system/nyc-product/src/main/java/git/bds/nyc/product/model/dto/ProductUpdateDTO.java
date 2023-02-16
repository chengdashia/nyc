package git.bds.nyc.product.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: chnnc
 * @Date: 2022/11/06/14:21
 * @Description: 更新审核状态
 */
@ApiModel(value = "更新初级产品审核状态(更新时使用)")
@Data
@Accessors(chain = true)
public class ProductUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Id can't be NULL")
    @ApiModelProperty("商品id")
    private Long id;

    @NotNull(message = "auditStatus can't be NULL")
    @Length(min = 0, max = 1, message = "max length: 36")
    @ApiModelProperty("审核状态，0：不通过；1：审核通过")
    private Long auditStatus;

}


