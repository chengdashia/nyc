package com.git.bds.nyc.applet.api.controller.mine;

import com.git.bds.nyc.applet.api.convert.OrderConvert;
import com.git.bds.nyc.applet.api.model.vo.order.OrderDataVO;
import com.git.bds.nyc.applet.api.model.vo.order.OrderVO;
import com.git.bds.nyc.applet.api.service.order.OrderService;
import com.git.bds.nyc.communal.model.domain.ContractOrder;
import com.git.bds.nyc.communal.model.dto.OrderDTO;
import com.git.bds.nyc.communal.model.dto.OrderDataDTO;
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
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 成大事
 * @since 2023/2/3 16:21
 */
@Api(tags = "订单处理模块")
@Slf4j
@Validated
@RestController
@RequestMapping("/applet/mine/orderHandler")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OrderHandlerController {

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
            @PathVariable("type") @Min(value = 1,message = "类型错误！！！") @Max(value = 4,message = "类型错误") int type
    ){
        PageResult<ContractOrder> page = orderService.getOrderPage(pageParam, type);
        List<OrderVO> list = OrderConvert.INSTANCE.toOrderVO(page.getList());
        return R.ok(new PageResult<>(list,page.getTotal()));
    }


    /**
     * 根据订单id查看订单详情
     *
     * @return {@link R}<{@link Boolean}>
     */
    @PostMapping("/getOrderInfoById/{id}")
    @ApiOperation("根据订单id查看订单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单id", dataTypeClass = Long.class, paramType = "path", example = "1", required = true)
    })
    public R<ContractOrder> getOrderInfoById(
            @PathVariable("id") @NotNull Long id
    ){
        ContractOrder contractOrder = orderService.getOrderInfoById(id);
        return R.ok(contractOrder);
    }


    /**
     * 根据订单id删除订单
     *
     * @return {@link R}<{@link Boolean}>
     */
    @PostMapping("/delOrderById/{type}/{id}")
    @ApiOperation(value = "根据订单id删除订单",notes = "类型只能有这两种(3:拒绝签字、4:交易成功)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型(3:拒绝签字、4:交易成功", dataTypeClass = Integer.class, paramType = "path", example = "1", required = true),
            @ApiImplicitParam(name = "id", value = "订单id", dataTypeClass = Long.class, paramType = "path", example = "1", required = true)

    })
    public R<Boolean> delOrderById(
            @PathVariable("type") @Min(value = 3,message = "类型错误！！！") @Max(value = 4,message = "类型错误！！！") int type,
            @PathVariable("id") Long id
    ){
       return R.decide( orderService.delOrderById(type,id) );
    }


    /**
     * 获取各种订单数量
     *
     * @return {@link R}<{@link OrderDataDTO}>
     */
    @PostMapping("/getQuantitiesOfVariousOrders")
    @ApiOperation("查看自己订单模块的数据 成功或失败的个数")
    public R<OrderDataVO> getQuantitiesOfVariousOrders(){
        OrderDataDTO data = orderService.getQuantitiesOfVariousOrders();
        OrderDataVO orderDataVO = OrderConvert.INSTANCE.toOrderDataVO(data);
        return R.ok(orderDataVO);
    }


}
