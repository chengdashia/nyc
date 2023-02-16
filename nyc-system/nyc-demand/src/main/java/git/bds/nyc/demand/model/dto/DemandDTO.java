package git.bds.nyc.demand.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 成大事
 * @since 2022/10/31 16:09
 */
@Data
public class DemandDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 需求的id */
    private Long id;

    /** 需求物种 */
    private String demandSpecies;

    /** 需求品种 */
    private String demandVariety;

    /** 需求重量 */
    private BigDecimal demandWeight;

    /** 需求价格 */
    private BigDecimal demandPrice;

    /** 详细地址 */
    private String detailedAddress;

    /** 需求封面 */
    private String demandCover;

}
