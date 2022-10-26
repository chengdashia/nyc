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
 * 产品收藏表
 * </p>
 *
 * @author 成大事
 * @since 2022-10-26 10:35:29
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("product_collection")
@ApiModel(value = "ProductCollection对象", description = "产品收藏表")
public class ProductCollection extends Model<ProductCollection> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("收藏id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("收藏人的id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("收藏的商品的id")
    @TableField("product_id")
    private Long productId;

    @ApiModelProperty("产品的种类(0：农户初级；1：公司初级；2：公司加工)")
    @TableField("product_status")
    private Integer productStatus;

    @ApiModelProperty("收藏时间")
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
