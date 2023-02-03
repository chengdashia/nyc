package com.git.bds.nyc.applet.api.controller;

import com.git.bds.nyc.applet.api.convert.OrderConvert;
import com.git.bds.nyc.communal.model.dto.OrderDataDTO;
import com.git.bds.nyc.applet.api.model.vo.order.OrderDataVO;
import com.git.bds.nyc.applet.api.model.vo.order.OrderVO;
import com.git.bds.nyc.applet.api.service.order.OrderService;
import com.git.bds.nyc.communal.model.domain.ContractOrder;
import com.git.bds.nyc.communal.model.dto.OrderDTO;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.result.R;
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
 * @since 2023/2/3 16:21
 */
@Api(tags = "订单模块")
@Slf4j
@Validated
@RestController
@RequestMapping("/applet/order")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OrderController {

    private final OrderService orderService;

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
        return R.decide(orderService.placeOrder(orderDTO));
    }

    /**
     * 查看别人 下单自己产品的订单list
     *
     * @return {@link R}<{@link Boolean}>
     */
    @PostMapping("/getOrderPage/{type}")
    @ApiOperation("查看别人 下单自己产品的订单list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型(1:待签字、2:已签字、3:拒绝签字、4:交易成功", dataTypeClass = Integer.class, paramType = "path", example = "1", required = true)
    })
    public R<PageResult<OrderVO>> getOrderPage(
            @RequestBody @Validated PageParam pageParam,
            @PathVariable("type")@Min(1)@Max(4) int type
    ){
        PageResult<ContractOrder> page = orderService.getOrderPage(pageParam, type);
        List<OrderVO> list = OrderConvert.INSTANCE.toOrderVO(page.getList());
        return R.ok(new PageResult<>(list,page.getTotal()));
    }


    @PostMapping("/getQuantitiesOfVariousOrders")
    @ApiOperation("查看自己订单模块的数据 成功或失败的个数")
    public R<OrderDataVO> getQuantitiesOfVariousOrders(){
        OrderDataDTO dataDTO = orderService.getQuantitiesOfVariousOrders();
        return R.ok();
    }


}
