package com.git.bds.nyc.product.service.history;


import com.git.bds.nyc.product.mapper.ProductHistoryMapper;
import com.git.bds.nyc.product.model.domain.ProductHistory;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品浏览记录表 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-10-26 10:35:30
 */
@Service
public class ProductHistoryServiceImpl extends MPJBaseServiceImpl<ProductHistoryMapper, ProductHistory> implements ProductHistoryService {

}
