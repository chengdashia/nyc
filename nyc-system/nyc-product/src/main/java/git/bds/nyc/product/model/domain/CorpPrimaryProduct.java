package git.bds.nyc.product.model.domain;

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
 * 初级农产品表
 * </p>
 *
 * @author chnnc
 * @since 2022-10-15 18:50:50
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("corp_primary_product")
@ApiModel(value = "CorpPrimaryProduct对象", description = "初级农产品表")
public class CorpPrimaryProduct extends Model<CorpPrimaryProduct> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("发布者id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("联系信息(即发布人的联系方式，发货地址等)")
    @TableField("contact_info_id")
    private Long contactInfoId;

    @ApiModelProperty("种类")
    @TableField("product_species")
    private String productSpecies;

    @ApiModelProperty("品种")
    @TableField("product_variety")
    private String productVariety;

    @ApiModelProperty("商品重量")
    @TableField("product_weight")
    private BigDecimal productWeight;

    @ApiModelProperty("商品单价")
    @TableField("product_price")
    private BigDecimal productPrice;

    @ApiModelProperty("商品的生产地区")
    @TableField("product_production_area")
    private String productProductionArea;

    @ApiModelProperty("商品的封面图")
    @TableField("product_cover")
    private String productCover;

    @ApiModelProperty("商品的备注")
    @TableField("product_remark")
    private String productRemark;

    @ApiModelProperty("状态（0：在售  1：预售）")
    @TableField("product_status")
    private Integer productStatus;

    @ApiModelProperty("审核状态(-1：未审核；0：不通过；1：审核通过)")
    @TableField("audit_status")
    private Integer auditStatus;

    @ApiModelProperty("商品的发布时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("上市时间，用于预售")
    @TableField("market_time")
    private LocalDateTime marketTime;

    @ApiModelProperty("逻辑删除(0：删除；1：存在)")
    @TableField("delete_flag")
    private Integer deleteFlag;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String CONTACT_INFO_ID = "contact_info_id";

    public static final String PRODUCT_SPECIES = "product_species";

    public static final String PRODUCT_VARIETY = "product_variety";

    public static final String PRODUCT_WEIGHT = "product_weight";

    public static final String PRODUCT_PRICE = "product_price";

    public static final String PRODUCT_PRODUCTION_AREA = "product_production_area";

    public static final String PRODUCT_COVER = "product_cover";

    public static final String PRODUCT_REMARK = "product_remark";

    public static final String PRODUCT_STATUS = "product_status";

    public static final String AUDIT_STATUS = "audit_status";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String MARKET_TIME = "market_time";

    public static final String DELETE_FLAG = "delete_flag";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
