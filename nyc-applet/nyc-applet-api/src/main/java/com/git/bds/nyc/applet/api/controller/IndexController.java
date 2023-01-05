package com.git.bds.nyc.applet.api.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.easyes.core.biz.PageInfo;
import cn.easyes.core.conditions.LambdaEsQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.git.bds.nyc.applet.api.convert.demand.DemandConvert;
import com.git.bds.nyc.applet.api.model.vo.*;
import com.git.bds.nyc.applet.api.convert.index.IndexConvert;
import com.git.bds.nyc.applet.api.convert.product.ProductConvert;
import com.git.bds.nyc.applet.api.model.vo.demand.DemandInfoVO;
import com.git.bds.nyc.applet.api.model.vo.demand.DemandVO;
import com.git.bds.nyc.applet.api.model.vo.product.ProductInfoVO;
import com.git.bds.nyc.applet.api.model.vo.product.ProductVO;
import com.git.bds.nyc.communal.model.domain.Advertisement;
import com.git.bds.nyc.communal.service.advertisement.AdvertisementService;
import com.git.bds.nyc.demand.model.dto.DemandDTO;
import com.git.bds.nyc.demand.model.dto.DemandInfoDTO;
import com.git.bds.nyc.demand.service.CorpDemandService;
import com.git.bds.nyc.enums.CollectionType;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResponse;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.page.PageUtil;
import com.git.bds.nyc.product.mapper.ee.ProductEsMapper;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.domain.ProductCollection;
import com.git.bds.nyc.product.model.dto.ProductInfoDTO;
import com.git.bds.nyc.product.model.es.ProductEs;
import com.git.bds.nyc.product.service.collection.ProductCollectionService;
import com.git.bds.nyc.product.service.history.ProductHistoryService;
import com.git.bds.nyc.product.service.primary.farmer.FarmerPrimaryProductService;
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

import javax.validation.Valid;
import java.util.List;


/**
 * @author 成大事
 * @since 2023/1/4 20:34
 */
@Api(tags = "首页接口 ")
@Slf4j
@Validated
@RestController
@RequestMapping("/index")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class IndexController {

    private final AdvertisementService advertisementService;

    private final FarmerPrimaryProductService productService;

    private final ProductCollectionService productCollectionService;

    private final ProductHistoryService productHistoryService;

    private final ProductEsMapper productEsMapper;

    private final CorpDemandService demandService;

    /**
     * 获取广告
     *
     * @return {@link R}<{@link List}<{@link IndexAdvertisementVO}>>
     */
    @ApiOperation("获取广告列表")
    @GetMapping("/getAdvertisements")
    public R<List<IndexAdvertisementVO>> getAdvertisements(){
        List<Advertisement> list = advertisementService.getAdvertisements();
        List<IndexAdvertisementVO> voList = IndexConvert.INSTANCE.toAdvertisementVOList(list);
        return R.ok(voList);
    }


    /**
     * 主页产品（按页面）
     *
     * @param pageParam 页面参数
     * @return {@link R}<{@link List}<{@link ProductVO}>>
     */
    @ApiOperation("首页商品数据")
    @GetMapping("/getProductData")
    public R<List<ProductVO>> homePageProductsByPage(
            @Valid PageParam pageParam
    ){
        List<FarmerPrimaryProduct> productList = productService.homePageProductsByPage(pageParam);
        return R.ok(ProductConvert.INSTANCE.toPrimaryProductVO(productList));
    }

    /**
     * 主页产品按页面按es
     *
     * @param pageParam 页面参数
     * @return {@link R}<{@link PageResult}<{@link ProductEs}>>
     */
    @ApiOperation("首页商品数据 通过Es获取")
    @GetMapping("/getProductByEs")
    public R<PageResult<ProductEs>> homePageProductsByPageByEs(
            @Valid PageParam pageParam
    ){
        log.info(""+pageParam);
        LambdaEsQueryWrapper<ProductEs> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.matchAllQuery();
        wrapper.orderByDesc(ProductEs::getCreateTime);
        PageInfo<ProductEs> productEsPageInfo = productEsMapper.pageQuery(wrapper, pageParam.getPageNo().intValue(), pageParam.getPageSize().intValue());
        return R.ok(new PageResult<>(productEsPageInfo.getList(),(long) productEsPageInfo.getPageSize()));
    }

    /**
     * 搜索
     *
     * @param pageParam 页面参数
     * @param key       钥匙
     * @return {@link R}<{@link PageInfo}<{@link ProductEs}>>
     */
    @GetMapping("/search/{key}")
    public R<PageInfo<ProductEs>> search(
            @Valid PageParam pageParam,
            @PathVariable("key") String key
    ){
        LambdaEsQueryWrapper<ProductEs> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.matchPhrasePrefixQuery(ProductEs::getProductVariety,key);
        return R.ok(productEsMapper.pageQuery(wrapper, pageParam.getPageNo().intValue(), pageParam.getPageSize().intValue()));
    }

    /**
     * 获取产品信息
     *
     * @param id   产品id
     * @param type 类型
     * @return {@link R}<{@link ProductInfoVO}>
     */
    @ApiOperation("商品的详细数据集")
    @PostMapping("/getProductInfo/{id}/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品id", dataTypeClass = Long.class, paramType = "path", example = "112345646545", required = true),
            @ApiImplicitParam(name = "type", value = "类型", dataTypeClass = Integer.class, paramType = "path", example = "0", required = true)
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


    /**
     * 主页需求（按页面）
     *
     * @param pageParam 页面参数
     * @return {@link R}<{@link PageResponse}<{@link DemandVO}>>
     */
    @ApiOperation("首页 需求数据")
    @GetMapping("/getDemandData")
    public R<PageResponse<DemandVO>> homePageDemandsByPage(
            @Valid PageParam pageParam
    ){
        List<DemandDTO> demandDTOList = demandService.homePageDemandsByPage(pageParam);
        List<DemandVO> demandVOList = DemandConvert.INSTANCE.toDemandVO(demandDTOList);
        PageResponse<DemandVO> result = PageUtil.toPage(demandVOList, pageParam);
        return R.ok(result);
    }

    /**
     * 获取需求信息
     *
     * @param id 身份证件
     * @return {@link R}<{@link DemandInfoVO}>
     */
    @ApiOperation("需求的详细数据集")
    @PostMapping("/getDemandInfo/{id}")
    @ApiImplicitParam(name = "id", value = "需求id", required = true, dataTypeClass = Long.class)
    public R<DemandInfoVO> getDemandInfo(
            @PathVariable("id") Long id
    ){
        DemandInfoDTO product = demandService.getDemandInfo(id);
        return R.ok(DemandConvert.INSTANCE.toDemandInfoVO(product));
    }


}
