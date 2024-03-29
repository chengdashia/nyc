package git.bds.nyc.enums;

/**
 * @author 成大事
 * @since 2022/10/19 11:13
 */
public enum ProductStatusType {

    /**审核**/
    AUDIT(-1,"审核"),

    /** 在售 */
    ON_SELL(0,"在售"),
    /** 预售 */
    PRE_SELL(1,"预售");

    private final Integer value;
    private final String msg;

    ProductStatusType(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public Integer getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }
}
