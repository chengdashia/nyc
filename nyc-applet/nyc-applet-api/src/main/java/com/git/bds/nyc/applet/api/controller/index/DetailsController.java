package com.git.bds.nyc.applet.api.controller.index;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.git.bds.nyc.applet.api.convert.DemandConvert;
import com.git.bds.nyc.applet.api.convert.ProductConvert;
import com.git.bds.nyc.applet.api.model.vo.demand.DemandInfoVO;
import com.git.bds.nyc.applet.api.model.vo.product.ProductInfoVO;
import com.git.bds.nyc.applet.api.service.demand.DemandService;
import com.git.bds.nyc.applet.api.service.order.OrderService;
import com.git.bds.nyc.communal.model.dto.OrderDTO;
import com.git.bds.nyc.demand.model.dto.DemandInfoDTO;
import com.git.bds.nyc.demand.model.dto.DemandModifyDTO;
import com.git.bds.nyc.enums.CollectionType;
import com.git.bds.nyc.product.model.domain.ProductCollection;
import com.git.bds.nyc.product.model.dto.ProductInfoDTO;
import com.git.bds.nyc.product.service.ProductService;
import com.git.bds.nyc.product.service.collection.ProductCollectionService;
import com.git.bds.nyc.product.service.history.ProductHistoryService;
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

/**
 * @author 成大事
 * @since 2023/2/15 18:35
 */
@Api(tags = "小程序首页详情页接口管理")
@Slf4j
@Validated
@RestController
@RequestMapping("/applet/index/details")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DetailsController {

    private final OrderService orderService;

    private final ProductCollectionService productCollectionService;

    private final ProductHistoryService productHistoryService;

    private final ProductService productService;

    private final DemandService demandService;


    /**
     * 获取产品信息
     *
     * @param id   产品id
     * @param type 类型
     * @return {@link R}<{@link ProductInfoVO}>
     */
    @ApiOperation("商品的详细数据集")
    @PostMapping("/getProductInfo/{type}/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品id", dataTypeClass = Long.class, paramType = "path", example = "112345646545", required = true),
            @ApiImplicitParam(name = "type", value = "类型(0：农户初级；1：公司初级；2：公司加工)", dataTypeClass = Integer.class, paramType = "path", example = "0", required = true)
    })
    public R<ProductInfoVO> getProductInfo(
            @PathVariable("id") Long id,
            @PathVariable("type") int type
    ){
        ProductInfoDTO product = productService.getProductInfo(id,type);
        ProductCollection one;
        if(StpUtil.isLogin()){
            one = productCollectionService.getOne(new QueryWrapper<ProductCollection>()
                    .select(ProductCollection.PRODUCT_ID)
                    .eq(ProductCollection.PRODUCT_ID, id)
                    .eq(ProductCollection.PRODUCT_TYPE, type));
            if (one != null){
                product.setIsCollection(CollectionType.NOT_COLLECTION.getValue());
            }else {
                product.setIsCollection(CollectionType.IS_COLLECTION.getValue());
            }
            productHistoryService.addBrowsingHistory(StpUtil.getLoginIdAsLong(),id,type);
        }else {
            product.setIsCollection(CollectionType.IS_COLLECTION.getValue());
        }
        return R.ok(ProductConvert.INSTANCE.toPrimaryProductInfoVO(product));
    }


    @ApiOperation("获取商家电话")
    @GetMapping("/getSellerTel/{type}/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品id", dataTypeClass = Long.class, paramType = "path", example = "112345646545", required = true),
            @ApiImplicitParam(name = "type", value = "类型", dataTypeClass = Integer.class, paramType = "path", example = "0", required = true)
    })
    public String getSellerTel(
            @PathVariable("id") Long id,
            @PathVariable("type") int type
    ){
        return productService.getSellerTel(id,type);
    }


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
     * 根据需求id获取需求的详细数据集
     *
     * @param id 需求id
     * @return {@link R}<{@link DemandInfoVO}>
     */
    @ApiOperation("根据需求id获取需求的详细数据集")
    @GetMapping("/getDemandInfoById/{id}")
    @ApiImplicitParam(name = "id", value = "需求id", required = true, dataTypeClass = Long.class)
    public R<DemandInfoVO> getDemandInfoById(
            @PathVariable("id") Long id
    ){
        DemandInfoDTO product = demandService.getDemandInfoById(id);
        return R.ok(DemandConvert.INSTANCE.toDemandInfoVO(product));
    }

    /**
     * 修改发布的需求信息
     *
     * @param demandModifyDTO 需求修改dto
     * @return {@link R}<{@link Boolean}>
     */
    @PostMapping("/modifyDemandInfo")
    @ApiOperation("修改发布的需求信息")
    public R<Boolean> modifyDemandInfo(
            @Validated @RequestBody DemandModifyDTO demandModifyDTO
    ){
        return R.decide(demandService.modifyDemandInfo(demandModifyDTO));
    }

}
