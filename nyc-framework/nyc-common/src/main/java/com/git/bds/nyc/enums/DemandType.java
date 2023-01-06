package com.git.bds.nyc.enums;

/**
 * @author 成大事
 * @since 2023/1/6 13:16
 */
public enum DemandType {
    /** 农户需求 */
    FARMER_DEMAND(0,"农户需求"),
    /** 公司需求 */
    CORP_DEMAND(1,"公司需求");

    private final Integer value;
    private final String msg;

    DemandType(Integer value, String msg) {
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
