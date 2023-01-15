package com.git.bds.nyc.user.service.farmer.order;

import com.git.bds.nyc.communal.model.domain.ContractOrder;
import com.git.bds.nyc.communal.model.dto.OrderDTO;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;

/**
 * @author 成大事
 * @since 2023/1/1 14:47
 */
public interface FarmerOrderService {
    /**
     * 下单
     *
     * @param orderDTO 订单dto
     * @return {@link Boolean}
     */
    Boolean placeOrder(OrderDTO orderDTO);

    /**
     * 获取订单列表
     *
     * @param type      类型
     * @param pageParam 页面参数
     * @return {@link PageResult}<{@link ContractOrder}>
     */
    PageResult<ContractOrder> getOrderList(PageParam pageParam, int type);
}
