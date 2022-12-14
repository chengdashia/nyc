package com.git.bds.nyc.enums;

/**
 * @author 成大事
 * @since 2022/10/26 10:55
 */
public enum ProductType {
    /** 农户初级 */
    FARMER_PRIMARY(0,"农户初级"),
    /** 预售 */
    CORP_PRIMARY(1,"公司初级"),
    /** 公司加工 */
    CORP_PROCESSING(2,"公司加工");

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
