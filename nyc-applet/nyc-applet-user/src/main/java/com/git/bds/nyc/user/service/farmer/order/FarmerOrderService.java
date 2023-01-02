package com.git.bds.nyc.user.service.farmer.order;

import com.git.bds.nyc.communal.model.dto.OrderDTO;

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
}
