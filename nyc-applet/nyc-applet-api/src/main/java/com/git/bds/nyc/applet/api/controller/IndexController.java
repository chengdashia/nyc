package com.git.bds.nyc.applet.api.controller;

import com.git.bds.nyc.applet.api.convert.index.IndexConvert;
import com.git.bds.nyc.applet.api.model.vo.IndexAdvertisementVO;
import com.git.bds.nyc.communal.model.domain.Advertisement;
import com.git.bds.nyc.communal.service.advertisement.AdvertisementService;
import com.git.bds.nyc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 分页获取广告
     *
     * @return {@link R}<{@link List}<{@link IndexAdvertisementVO}>>
     */
    @ApiOperation("获取广告列表 分页")
    @PostMapping("/getAdvertisements")
    public R<List<IndexAdvertisementVO>> getAdvertisementsByPage(){
        List<Advertisement> list = advertisementService.getAdvertisements();
        List<IndexAdvertisementVO> voList = IndexConvert.INSTANCE.toAdvertisementVOList(list);
        return R.ok(voList);
    }
}
