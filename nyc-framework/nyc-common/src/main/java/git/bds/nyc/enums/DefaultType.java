package git.bds.nyc.enums;

/**
 * @author 成大事
 * @since 2022/11/28 8:31
 */
public enum DefaultType {
    /** 默认 */
    IS_DEFAULT(1,"默认！"),
    /** 非默认 */
    NOT_DEFAULT(0,"非默认！");

    private final Integer value;
    private final String msg;

    DefaultType(Integer value, String msg) {
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
