package git.bds.nyc.itext;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author 成大事
 * @since 2023/1/1 15:40
 */
@Getter
@Setter
public class PdfData {

    /** 甲方 */
    private String partyA;
    /** 乙方 */
    private String partyB;

    /** 种 */
    private String species;

    /** 品种 */
    private String varieties;

    /** 量 */
    private BigDecimal quantity;

    /** 单价 */
    private BigDecimal unitPrice;

    /** 总价 */
    private BigDecimal totalPrice;

    /** 总额 */
    private String totalAmount;

    /** 甲方联系地址 */
    private String partyAContactAddress;
    /** 乙方联系地址 */
    private String partyBContactAddress;
    /** 甲方联系电话 */
    private String partyAContactNumber;
    /** 乙方联系电话 */
    private String partyBContactNumber;

    /** 年 */
    private Integer year;

    /** 月 */
    private Integer month;

    /** 白天 */
    private Integer day;




}
