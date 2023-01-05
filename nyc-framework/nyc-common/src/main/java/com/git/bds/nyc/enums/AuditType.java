package com.git.bds.nyc.enums;

/**
 * @author 成大事
 * @since 2023/1/5 17:43
 */
public enum AuditType {
    /** 未审核 */
    UNAPPROVED(-1,"未审核！"),
    /** 不通过 */
    NO_PASS(0,"不通过！"),
    /** 通过 */
    PASS(1,"审核通过！");

    private final Integer value;
    private final String msg;

    AuditType(Integer value, String msg) {
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
