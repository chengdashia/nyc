package com.git.bds.nyc.enums;

/**
 * @author 成大事
 * @since 2022/8/18 9:29
 */
public enum SysRole {


    /** 游客 */
    VISITOR(1, "游客"),
    /** 农民 */
    FARMER(2, "农户"),
    /** 企业 */
    ENTERPRISE(3, "企业"),
    /** 合作社 */
    COOP(4, "合作社"),
    /** 超级管理员 */
    SUPER_ADMIN(5, "超管");

    private final Integer value;
    private final String msg;

    SysRole(Integer value, String msg) {
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
