package git.bds.nyc.product.mapper.mp;


import git.bds.nyc.product.model.domain.ProductHistory;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 产品浏览记录表 Mapper 接口
 * </p>
 *
 * @author 成大事
 * @since 2022-10-26 10:35:30
 */
@Mapper
public interface ProductHistoryMapper extends MPJBaseMapper<ProductHistory> {


}
