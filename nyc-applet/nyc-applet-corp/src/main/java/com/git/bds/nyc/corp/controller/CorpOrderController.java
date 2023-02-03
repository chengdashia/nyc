package com.git.bds.nyc.corp.controller;

import com.git.bds.nyc.communal.model.dto.OrderDTO;
import com.git.bds.nyc.corp.service.CorpService;
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

/**
 * @author 成大事
 * @since 2023/1/15 15:36
 */
@Api(tags = "公司合同订单 的接口")
@Slf4j
@Validated
@RestController
@RequestMapping("/corp/order")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CorpOrderController {

    private final CorpService corpService;

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
        return R.decide(corpService.placeOrder(orderDTO));
    }


}
