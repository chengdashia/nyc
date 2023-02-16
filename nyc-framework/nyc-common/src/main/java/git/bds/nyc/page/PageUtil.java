package git.bds.nyc.page;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author 成大事
 * @since 2022/10/31 16:22
 */
@Slf4j
public class PageUtil {
    private PageUtil(){

    }
    public static <T> PageResponse<T> toPage(List<T> list, PageParam pageParam){
        int allSize = list.size();
        PageResponse<T> pageResponse = new PageResponse<>();
        pageResponse.setPageSize(pageParam.getPageSize());
        pageResponse.setPageNo(pageParam.getPageNo());
        pageResponse.setTotal(allSize);
        pageResponse.setDataList(list);
        return pageResponse;
    }

}
