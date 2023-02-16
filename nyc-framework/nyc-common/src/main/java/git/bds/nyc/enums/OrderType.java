package git.bds.nyc.enums;

/**
 * @author 成大事
 * @since 2023/1/2 14:47
 */
public enum OrderType {
    /** 待签字 */
    UNSIGNED(1,"待签字"),
    /** 已签字 */
    SIGNED(2,"已签字"),
    /** 拒绝签字 */
    REFUSE_TO_SIGN(3,"拒绝签字"),
    /** 交易成功 */
    SUCCESSFUL_TRADE(4,"交易成功");

    private final Integer value;
    private final String msg;

    OrderType(Integer value, String msg) {
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
