package com.git.bds.nyc.communal.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author 成大事
 * @since 2023-01-02 14:13:45
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("contract_order")
@ApiModel(value = "ContractOrder对象", description = "订单表")
public class ContractOrder extends Model<ContractOrder> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("卖方id")
    @TableField("seller_id")
    private Long sellerId;

    @ApiModelProperty("买家id")
    @TableField("buyer_id")
    private Long buyerId;

    @ApiModelProperty("产品id")
    @TableField("product_id")
    private Long productId;

    @ApiModelProperty("产品品种")
    @TableField("product_species")
    private String productSpecies;
    @ApiModelProperty("产品种类")
    @TableField("product_variety")
    private String productVariety;

    @ApiModelProperty("产品类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("卖家的联系信息(即发布人的联系方式，发货地址等)")
    @TableField("seller_contact_info_id")
    private Long sellerContactInfoId;

    @ApiModelProperty("卖家签名")
    @TableField("seller_signature")
    private String sellerSignature;

    @ApiModelProperty("买家联系信息(即发布人的联系方式，发货地址等)")
    @TableField("buyer_contact_info_id")
    private Long buyerContactInfoId;

    @ApiModelProperty("买家签名")
    @TableField("buyer_signature")
    private String buyerSignature;

    @ApiModelProperty("单价")
    @TableField("unit_price")
    private BigDecimal unitPrice;

    @ApiModelProperty("订单状态（1，待签字 2 ；已签字，3，拒绝签字，4交易成功）")
    @TableField("order_status")
    private Integer orderStatus;

    @ApiModelProperty("下单重量")
    @TableField("order_weight")
    private BigDecimal orderWeight;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("合同地址")
    @TableField("contract_url")
    private String contractUrl;

    @ApiModelProperty("下单时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("完成时间")
    @TableField("complete_time")
    private LocalDateTime completeTime;

    @ApiModelProperty("交易失败的反馈")
    @TableField("fail_feedback")
    private String failFeedback;


    public static final String ID = "id";

    public static final String SELLER_ID = "seller_id";

    public static final String BUYER_ID = "buyer_id";

    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_SPECIES = "product_species";
    public static final String PRODUCT_VARIETY = "product_variety";

    public static final String SELLER_CONTACT_INFO_ID = "seller_contact_info_id";

    public static final String SELLER_SIGNATURE = "seller_signature";

    public static final String BUYER_CONTACT_INFO_ID = "buyer_contact_info_id";

    public static final String BUYER_SIGNATURE = "buyer_signature";

    public static final String UNIT_PRICE = "unit_price";

    public static final String ORDER_STATUS = "order_status";

    public static final String ORDER_WEIGHT = "order_weight";

    public static final String REMARK = "remark";

    public static final String CONTRACT_URL = "contract_url";

    public static final String CREATE_TIME = "create_time";

    public static final String COMPLETE_TIME = "complete_time";

    public static final String FAIL_FEEDBACK = "fail_feedback";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
