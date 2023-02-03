package com.git.bds.nyc.admin.enums;

/**
 * @author 成大事
 * @since 2023/2/3 16:02
 */
public enum AdminType {
    /** 合作社 */
    COOP(4,"合作社！"),
    /** 供销社 */
    ADMIN(5,"供销社！");

    private final Integer value;
    private final String msg;

    AdminType(Integer value, String msg) {
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
