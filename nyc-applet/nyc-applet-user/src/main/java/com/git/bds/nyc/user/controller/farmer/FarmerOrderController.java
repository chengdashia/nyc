package com.git.bds.nyc.user.controller.farmer;

import com.git.bds.nyc.communal.model.domain.ContractOrder;
import com.git.bds.nyc.communal.model.dto.OrderDTO;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.user.convert.FarmerOrderConvert;
import com.git.bds.nyc.user.model.vo.OrderShowVO;
import com.git.bds.nyc.user.service.farmer.order.FarmerOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

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


    /**
     * 查看别人 下单自己产品的订单list
     *
     * @return {@link R}<{@link Boolean}>
     */
    @PostMapping("/getOrderList/{type}")
    @ApiOperation("查看别人 下单自己产品的订单list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "id主键", dataTypeClass = Integer.class, paramType = "path", example = "1", required = true)
    })
    public R<PageResult<OrderShowVO>> getOrderList(
            @RequestBody @Validated PageParam pageParam,
            @PathVariable("type")@Min(1)@Max(4) int type
    ){
        PageResult<ContractOrder> page = farmerOrderService.getOrderList(pageParam, type);
        List<OrderShowVO> list = FarmerOrderConvert.INSTANCE.toOrderShowVO(page.getList());
        return R.ok(new PageResult<>(list,page.getTotal()));
    }
}
