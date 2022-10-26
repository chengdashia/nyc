package com.git.bds.nyc.product.model.domain;

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
 * 产品浏览记录表
 * </p>
 *
 * @author 成大事
 * @since 2022-10-26 10:35:30
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("product_history")
@ApiModel(value = "ProductHistory对象", description = "产品浏览记录表")
public class ProductHistory extends Model<ProductHistory> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("浏览的id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("浏览人的id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("浏览的商品的id")
    @TableField("product_id")
    private Long productId;

    @ApiModelProperty("商品的种类(0：农户初级；1：公司初级；2：公司加工)")
    @TableField("product_status")
    private Integer productStatus;

    @ApiModelProperty("浏览时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String PRODUCT_ID = "product_id";

    public static final String PRODUCT_STATUS = "product_status";

    public static final String CREATE_TIME = "create_time";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
