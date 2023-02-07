package com.git.bds.nyc.enums;

/**
 * @author 成大事
 * @since 2022/10/26 10:55
 */
public enum ProductType {

    /** 初级 */
    PRIMARY(0,"初级"),
    /** 加工 */
    PROCESSING(1,"加工"),
    /** 农户初级 */
    FARMER_PRIMARY(0,"农户初级"),
    /** 预售 */
    CORP_PRIMARY(1,"公司初级"),
    /** 公司加工 */
    CORP_PROCESSING(2,"公司加工"),
    /** 农民需求 */
    FARMER_DEMAND(3,"农户需求"),
    /** 企业需求 */
    CORP_DEMAND(4,"公司需求");

    private final Integer value;
    private final String msg;

    ProductType(Integer value, String msg) {
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
