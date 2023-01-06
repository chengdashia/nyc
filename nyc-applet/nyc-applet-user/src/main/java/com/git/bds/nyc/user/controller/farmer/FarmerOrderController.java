package com.git.bds.nyc.user.controller.farmer;

import com.git.bds.nyc.communal.model.dto.OrderDTO;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.user.service.farmer.order.FarmerOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 成大事
 * @since 2023/1/1 14:40
 */
@Api(tags = "农户订单 的接口")
@Slf4j
@Validated
@RestController
@RequestMapping("/farmer/order")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FarmerOrderController {

    private final FarmerOrderService farmerOrderService;

    /**
     * 下单
     *
     * @param orderDTO 订单dto
     * @return {@link R}<{@link Boolean}>
     */
    @PostMapping("/placeOrder")
    @ApiOperation("下订单")
    public R<Boolean> placeOrder(
            @RequestBody @Validated OrderDTO orderDTO
    ){
        return R.decide(farmerOrderService.placeOrder(orderDTO));
    }
}
