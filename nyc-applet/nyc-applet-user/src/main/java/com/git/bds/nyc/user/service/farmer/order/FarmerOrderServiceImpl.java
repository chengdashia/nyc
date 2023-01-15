package com.git.bds.nyc.user.service.farmer.order;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.communal.mapper.mp.ContractOrderMapper;
import com.git.bds.nyc.communal.model.domain.ContractOrder;
import com.git.bds.nyc.communal.model.dto.OrderDTO;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.mapper.mp.CorpProcessingProductMapper;
import com.git.bds.nyc.product.mapper.mp.primary.corp.CorpPrimaryProductMapper;
import com.git.bds.nyc.product.mapper.mp.primary.farmer.FarmerPrimaryProductMapper;
import com.git.bds.nyc.user.model.vo.OrderShowVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 成大事
 * @since 2023/1/1 14:47
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FarmerOrderServiceImpl implements FarmerOrderService{

    private final FarmerPrimaryProductMapper farmerPrimaryProductMapper;

    private final CorpPrimaryProductMapper corpPrimaryProductMapper;

    private final CorpProcessingProductMapper corpProcessingProductMapper;

    private final ContractOrderMapper contractOrderMapper;

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

    /**
     * 获取订单列表
     *
     * @param type      类型
     * @param pageParam 页面参数
     * @return {@link List}<{@link OrderShowVO}>
     */
    @Override
    public PageResult<ContractOrder> getOrderList(PageParam pageParam, int type) {
        Page<ContractOrder> page = contractOrderMapper.selectPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                new LambdaQueryWrapper<ContractOrder>()
                        .select(
                                ContractOrder::getId,
                                ContractOrder::getProductId,
                                ContractOrder::getProductSpecies,
                                ContractOrder::getProductVarieties,
                                ContractOrder::getOrderWeight,
                                ContractOrder::getCreateTime
                        )
                        .eq(ContractOrder::getBuyerId, StpUtil.getLoginIdAsLong())
                        .eq(ContractOrder::getType, type)
                        .orderByAsc(ContractOrder::getCreateTime));
        return new PageResult<>(page.getRecords(),page.getTotal());
    }
}
