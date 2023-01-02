package com.git.bds.nyc.user.service.farmer.order;

import com.git.bds.nyc.communal.mapper.mp.ContractOrderMapper;
import com.git.bds.nyc.communal.model.dto.OrderDTO;
import com.git.bds.nyc.product.mapper.mp.CorpProcessingProductMapper;
import com.git.bds.nyc.product.mapper.mp.primary.corp.CorpPrimaryProductMapper;
import com.git.bds.nyc.product.mapper.mp.primary.farmer.FarmerPrimaryProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 成大事
 * @since 2023/1/1 14:47
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FarmerOrderServiceImpl implements FarmerOrderService{

    private FarmerPrimaryProductMapper farmerPrimaryProductMapper;

    private CorpPrimaryProductMapper corpPrimaryProductMapper;

    private CorpProcessingProductMapper corpProcessingProductMapper;

    private ContractOrderMapper contractOrderMapper;

    /**
     * 下单
     *
     * @param orderDTO 订单dto
     * @return {@link Boolean}
     */
    @Override
    public Boolean placeOrder(OrderDTO orderDTO) {
        Integer type = orderDTO.getType();

        return null;
    }
}
