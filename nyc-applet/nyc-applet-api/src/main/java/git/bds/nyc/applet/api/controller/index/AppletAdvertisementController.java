package git.bds.nyc.applet.api.controller.index;

import git.bds.nyc.applet.api.convert.IndexConvert;
import git.bds.nyc.applet.api.model.vo.IndexAdvertisementVO;
import git.bds.nyc.communal.model.domain.Advertisement;
import git.bds.nyc.communal.service.advertisement.AdvertisementService;
import git.bds.nyc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 成大事
 * @since 2023/2/15 18:24
 */
@Api(tags = "小程序首页广告模块接口管理")
@Slf4j
@Validated
@RestController
@RequestMapping("/applet/index/advertisement")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AppletAdvertisementController {

    private final AdvertisementService advertisementService;

    /**
     * 小程序首页获取广告
     *
     * @return {@link R}<{@link List}<{@link IndexAdvertisementVO}>>
     */
    @ApiOperation("小程序首页获取广告")
    @GetMapping("/getAdvertisements")
    public R<List<IndexAdvertisementVO>> getAdvertisements(){
        List<Advertisement> list = advertisementService.getAdvertisements();
        List<IndexAdvertisementVO> voList = IndexConvert.INSTANCE.toAdvertisementVOList(list);
        return R.ok(voList);
    }

}
