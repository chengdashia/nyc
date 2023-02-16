package git.bds.nyc.applet.api.service.order;

import git.bds.nyc.communal.model.domain.ContractOrder;
import git.bds.nyc.communal.model.dto.OrderDTO;
import git.bds.nyc.communal.model.dto.OrderDataDTO;
import git.bds.nyc.page.PageParam;
import git.bds.nyc.page.PageResult;

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

    /**
     * 按页面获取我订单
     *
     * @param pageParam 页面参数
     * @return {@link PageResult}<{@link ContractOrder}>
     */
    PageResult<ContractOrder> getMyOrderPage(PageParam pageParam);

    /**
     * 按id获取订单信息
     *
     * @param id 订单id
     * @return {@link ContractOrder}
     */
    ContractOrder getOrderInfoById(Long id);

    /**
     * 按id删除订单
     *
     * @param type 类型
     * @param id   订单
     * @return {@link Boolean}
     */
    Boolean delOrderById(int type, Long id);
}
