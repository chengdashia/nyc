package com.git.bds.nyc.enums;

/**
 * @author 成大事
 * @since 2023/1/4 18:09
 */
public enum AdvertisementType {
    /** 禁用 */
    DISABLE(0,"禁用！"),
    /** 在用 */
    ABLE(1,"在用！");

    private final Integer value;
    private final String msg;

    AdvertisementType(Integer value, String msg) {
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
