package git.bds.nyc.corp.service;

/**
 * @author 成大事
 * @since 2022/12/29 20:41
 */
public interface CorpService {

    /**
     * 按id删除产品
     *
     * @param id   身份证件
     * @param type 类型
     * @return {@link Boolean}
     */
    Boolean deleteProductById(Long id, int type);
}
