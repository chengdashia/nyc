package com.git.bds.nyc.admin.controller;

import com.git.bds.nyc.admin.convert.AdvertisementConvert;
import com.git.bds.nyc.admin.model.domain.Advertisement;
import com.git.bds.nyc.admin.model.vo.AdvertisementVO;
import com.git.bds.nyc.admin.model.vo.UserVO;
import com.git.bds.nyc.admin.service.advertisement.AdvertisementService;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 成大事
 * @since 2023/1/4 15:42
 */
@Api(tags = "广告管理")
@Validated
@RestController
@RequestMapping("/admin/advertisement")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    /**
     * 分页获取广告
     *
     * @param pageParam 页面参数
     * @return {@link R}<{@link PageResult}<{@link UserVO}>>
     */
    @ApiOperation("获取广告列表 分页")
    @PostMapping("/getAdvertisementsByPage")
    public R<PageResult<AdvertisementVO>> getAdvertisementsByPage(
            @Validated @RequestBody PageParam pageParam
    ){
        PageResult<Advertisement> page = advertisementService.getAdvertisementsByPage(pageParam);
        List<AdvertisementVO> userVOList = AdvertisementConvert.INSTANCE.toAdvertisementVOList(page.getList());
        return R.ok(new PageResult<>(userVOList,page.getTotal()));
    }


    /**
     * 发布广告
     *
     * @param file 文件
     * @return {@link R}<{@link Boolean}>
     */
    @SneakyThrows
    @ApiOperation("发布广告")
    @PostMapping("/releaseAdvertisement")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "图片文件", required = true,dataTypeClass = MultipartFile.class,paramType = "query")
    })
    public R<Boolean> releaseAdvertisement(
            @RequestParam("file") MultipartFile file
    ){
        return R.decide(advertisementService.releaseAdvertisement(file));
    }

    /**
     * 根据id删除广告
     *
     * @param advertisementId 广告id
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation("根据id删除广告")
    @PostMapping("/delAdvertisementById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "advertisementId", value = "advertisementId", dataTypeClass = Long.class, paramType = "query", example = "112345646545", required = true)
    })
    public R<Boolean> delAdvertisementById(
            @RequestParam("advertisementId") @NotNull Long advertisementId
    ){
        return R.decide(advertisementService.delAdvertisementById(advertisementId));
    }


    /**
     * 根据id更新广告
     *
     * @param advertisementId 广告id
     * @return {@link R}<{@link Boolean}>
     */
    @SneakyThrows
    @ApiOperation("根据id更新广告")
    @PostMapping("/modifyAdvertisementById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "advertisementId", value = "advertisementId", dataTypeClass = Long.class, paramType = "query", example = "112345646545", required = true),
            @ApiImplicitParam(name = "file", value = "图片文件", required = true, dataTypeClass = MultipartFile.class, paramType = "query")
    })
    public R<Boolean> modifyAdvertisementById(
            @RequestParam("advertisementId") @NotNull Long advertisementId,
            @RequestParam("file") MultipartFile file
    ){
        return R.decide(advertisementService.modifyAdvertisementById(advertisementId,file));
    }


}
