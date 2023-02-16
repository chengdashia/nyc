package git.bds.nyc.product.service.primary.farmer;

import git.bds.nyc.product.mapper.mp.primary.farmer.FarmerPrimaryProductMapper;
import git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 初级农产品表 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-09-02 22:02:03
 */
@Service
public class FarmerPrimaryProductServiceImpl extends MPJBaseServiceImpl<FarmerPrimaryProductMapper, FarmerPrimaryProduct> implements FarmerPrimaryProductService {

}

