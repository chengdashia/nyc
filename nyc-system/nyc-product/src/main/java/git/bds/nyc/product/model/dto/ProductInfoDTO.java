package git.bds.nyc.product.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 成大事
 * @since 2022/9/7 13:47
 */
@Data
public class ProductInfoDTO {

    /** 商品id */
    private Long id;

    /** 联系信息(即发布人的联系方式，发货地址等) */
    private Long contactInfoId;

    /** 产品种类 */
    private String productSpecies;

    /** 产品种类 */
    private String productVariety;

    /** 产品重量 */
    private BigDecimal productWeight;

    /** 产品价格 */
    private BigDecimal productPrice;

    /** 产品生产区 */
    private String productProductionArea;

    /** 产品封面 */
    private String productCover;

    /** 产品备注 */
    private String productRemark;

    /** 产品状态 */
    private Integer productStatus;

    /** 创造时间 */
    private LocalDateTime createTime;

    /** 上市时间 */
    private LocalDateTime marketTime;

    /** 图片url */
    private String pictureUrl;

    /** img列表 */
    private List<String> imgList;

    /** 是否收藏  0:未收藏 1:已收藏 */
    private Integer isCollection;


}
