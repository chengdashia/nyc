package com.git.bds.nyc.applet.api.controller.index;

import cn.easyes.core.biz.PageInfo;
import cn.easyes.core.conditions.LambdaEsQueryWrapper;
import com.git.bds.nyc.applet.api.convert.DemandConvert;
import com.git.bds.nyc.applet.api.model.vo.demand.DemandVO;
import com.git.bds.nyc.applet.api.service.demand.DemandService;
import com.git.bds.nyc.demand.model.dto.DemandDTO;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResponse;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.page.PageUtil;
import com.git.bds.nyc.product.mapper.ee.ProductEsMapper;
import com.git.bds.nyc.product.model.es.ProductEs;
import com.git.bds.nyc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


/**
 * @author 成大事
 * @since 2023/1/4 20:34
 */
@Api(tags = "首页接口管理")
@Slf4j
@Validated
@RestController
@RequestMapping("/applet/index")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class IndexController {

    private final ProductEsMapper productEsMapper;

    private final DemandService demandService;


    /**
     * 主页产品按页面按es
     *
     * @param pageParam 页面参数
     * @return {@link R}<{@link PageResult}<{@link ProductEs}>>
     */
    @ApiOperation("首页商品数据 通过Es获取")
    @GetMapping("/getProductPageByEs")
    public R<PageResult<ProductEs>> getProductPageByEs(
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
     * 搜索框搜索
     *
     * @param pageParam 页面参数
     * @param key       搜索词
     * @return {@link R}<{@link PageInfo}<{@link ProductEs}>>
     */
    @ApiOperation("搜索框搜索")
    @GetMapping("/search/{key}")
    public R<PageInfo<ProductEs>> search(
            @Valid PageParam pageParam,
            @PathVariable("key") String key
    ){
        LambdaEsQueryWrapper<ProductEs> wrapper = new LambdaEsQueryWrapper<>();
        wrapper.match(ProductEs::getProductName,key);
        return R.ok(productEsMapper.pageQuery(wrapper, pageParam.getPageNo().intValue(), pageParam.getPageSize().intValue()));
    }



    /**
     * 主页需求（按页面）
     *
     * @param pageParam 页面参数
     * @return {@link R}<{@link PageResponse}<{@link DemandVO}>>
     */
    @ApiOperation("首页 需求数据")
    @GetMapping("/getDemandPageByEs")
    public R<PageResponse<DemandVO>> getDemandPageByEs(
            @Valid PageParam pageParam
    ){
        List<DemandDTO> demandDTOList = demandService.homePageDemandsByPage(pageParam);
        List<DemandVO> demandVOList = DemandConvert.INSTANCE.toDemandVO(demandDTOList);
        PageResponse<DemandVO> result = PageUtil.toPage(demandVOList, pageParam);
        return R.ok(result);
    }




}
