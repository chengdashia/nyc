package com.git.bds.nyc.page;

import lombok.Data;

import java.util.List;

/**
 * @author 成大事
 * @since 2022/10/31 16:24
 */
@Data
public class PageResponse<T> {
    /**
     * 每页的大小
     */
    private long pageSize;

    /**
     * 当前是第几页
     */
    private long pageNo;

    /**
     * 总的数据个数
     */
    private long total;

    /**
     * 数据列表
     */
    private List<T> dataList;

}
