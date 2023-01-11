package com.git.bds.nyc.communal.model.domain.authentication;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 个人认证表
 * </p>
 *
 * @author 成大事
 * @since 2023-01-11 10:06:59
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("farmer_authentication")
@ApiModel(value = "FarmerAuthentication对象", description = "个人认证表")
public class FarmerAuthentication extends Model<FarmerAuthentication> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("认证表的id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("用户的真实姓名")
    @TableField("real_name")
    private String realName;

    @ApiModelProperty("身份证前面照片(带照片名字的一面)")
    @TableField("id_card_front")
    private String idCardFront;

    @ApiModelProperty("身份证后面照片(带国徽名字的一面)")
    @TableField("id_cart_back")
    private String idCartBack;

    @ApiModelProperty("用户的身份证号码")
    @TableField("id_cart_num")
    private String idCartNum;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String REAL_NAME = "real_name";

    public static final String ID_CARD_FRONT = "id_card_front";

    public static final String ID_CART_BACK = "id_cart_back";

    public static final String ID_CART_NUM = "id_cart_num";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
