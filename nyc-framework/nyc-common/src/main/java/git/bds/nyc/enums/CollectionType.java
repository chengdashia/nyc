package git.bds.nyc.enums;

/**
 * @author 成大事
 * @since 2022/11/14 16:13
 */
public enum CollectionType {
    /** 未收藏 */
    IS_COLLECTION(0,"未收藏！"),
    /** 收藏 */
    NOT_COLLECTION(1,"已收藏！");

    private final Integer value;
    private final String msg;

    CollectionType(Integer value, String msg) {
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
