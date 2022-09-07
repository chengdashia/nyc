package com.git.bds.nyc.product.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 成大事
 * @since 2022-09-07 13:55:14
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("product_picture")
@ApiModel(value = "ProductPicture对象", description = "")
public class ProductPicture extends Model<ProductPicture> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("图片id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("产品id")
    @TableField("product_id")
    private Long productId;

    @ApiModelProperty("图片的url")
    @TableField("picture_url")
    private String pictureUrl;


    public static final String ID = "id";

    public static final String PRODUCT_ID = "product_id";

    public static final String PICTURE_URL = "picture_url";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
