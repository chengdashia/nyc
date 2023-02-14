package com.git.bds.nyc.applet.api.controller;

import com.git.bds.nyc.applet.api.convert.OrderConvert;
import com.git.bds.nyc.applet.api.model.vo.order.OrderVO;
import com.git.bds.nyc.applet.api.service.order.OrderService;
import com.git.bds.nyc.communal.model.domain.ContractOrder;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.result.R;
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

import java.util.List;

/**
 * @author 成大事
 * @since 2023/2/14 21:28
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
     * 查看自己下单别人产品的订单list 分页
     *
     * @return {@link R}<{@link Boolean}>
     */
    @PostMapping("/getMyOrderByPage")
    @ApiOperation("查看自己下单别人产品的订单list 分页")
    public R<PageResult<OrderVO>> getMyOrderByPage(
            @RequestBody @Validated PageParam pageParam
    ){
        PageResult<ContractOrder> page = orderService.getMyOrderByPage(pageParam);
        List<OrderVO> list = OrderConvert.INSTANCE.toOrderVO(page.getList());
        return R.ok(new PageResult<>(list,page.getTotal()));
    }
}
