package com.git.bds.nyc.corp.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: chnnc
 * @Date: 2022/11/06/13:25
 * @Description:  用于公司产品审核更新
 */
@ApiModel(value = "用于公司产品审核更新(更新时使用)")
@Data
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
