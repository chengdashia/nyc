package com.git.bds.nyc.result;

import java.util.HashMap;

/**
 * @author 成大事
 * @since 2022/7/7 19:59
 */
public class ResultMap extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public static ResultMap result(int status, String msg) {
        ResultMap r = new ResultMap();
        r.put("status", status);
        r.put("msg", msg);
        return r;
    }

    public static ResultMap result() {
        return new ResultMap();
    }

    public ResultMap put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
