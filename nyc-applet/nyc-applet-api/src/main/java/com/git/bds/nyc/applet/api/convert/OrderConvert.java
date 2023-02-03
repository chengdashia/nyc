package com.git.bds.nyc.applet.api.convert;

import com.git.bds.nyc.applet.api.model.vo.order.OrderVO;
import com.git.bds.nyc.communal.model.domain.ContractOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 成大事
 * @since 2023/2/3 16:31
 */
@Mapper
public interface OrderConvert {

    OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);


    /**
     * 订单转VO
     *
     * @param list 列表
     * @return {@link List}<{@link OrderVO}>
     */
    List<OrderVO> toOrderVO(List<ContractOrder> list);
}
