package com.git.bds.nyc.user.convert;

import com.git.bds.nyc.communal.model.domain.ContractOrder;
import com.git.bds.nyc.communal.model.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 成大事
 * @since 2023/1/2 14:45
 */
@Mapper
public interface FarmerOrderConvert {

    FarmerOrderConvert INSTANCE = Mappers.getMapper(FarmerOrderConvert.class);

    /**
     * 合同订单
     *
     * @param orderDTO 订单dto
     * @return {@link ContractOrder}
     */
    ContractOrder toContractOrder(OrderDTO orderDTO);
}
