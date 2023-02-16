package git.bds.nyc.enums;

/**
 * @author 成大事
 * @since 2022/8/18 9:29
 */
public enum RoleType {


    /** 游客 */
    VISITOR(1, "visitor"),
    /** 农民 */
    FARMER(2, "farmer"),
    /** 企业 */
    ENTERPRISE(3, "corp"),
    /** 合作社 */
    COOP(4, "coop"),
    /** 超级管理员 */
    SUPER_ADMIN(5, "admin");

    private final Integer value;
    private final String msg;

    RoleType(Integer value, String msg) {
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
