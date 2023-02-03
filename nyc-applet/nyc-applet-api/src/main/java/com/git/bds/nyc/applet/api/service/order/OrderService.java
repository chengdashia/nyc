package com.git.bds.nyc.applet.api.service.order;

import com.git.bds.nyc.communal.model.dto.OrderDataDTO;
import com.git.bds.nyc.communal.model.domain.ContractOrder;
import com.git.bds.nyc.communal.model.dto.OrderDTO;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;

/**
 * @author 成大事
 * @since 2023/2/3 16:22
 */
public interface OrderService {

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
    PageResult<ContractOrder> getOrderPage(PageParam pageParam, int type);

    /**
     * 获取各种订单数量
     *
     * @return {@link OrderDataDTO}
     */
    OrderDataDTO getQuantitiesOfVariousOrders();
}
