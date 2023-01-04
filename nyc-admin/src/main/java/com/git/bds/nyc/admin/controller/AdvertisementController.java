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

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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
     * @param file  文件
     * @param title 标题
     * @return {@link R}<{@link Boolean}>
     */
    @SneakyThrows
    @ApiOperation("发布广告")
    @PostMapping("/releaseAdvertisement")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "标题", dataTypeClass = String.class, paramType = "query", example = "贵理工大数据学院工作室", required = true),
            @ApiImplicitParam(name = "file", value = "图片文件", required = true,dataTypeClass = MultipartFile.class,paramType = "query")
    })
    public R<Boolean> releaseAdvertisement(
            @RequestParam("title") @NotNull @NotBlank(message = "title不能为空") String title,
            @RequestParam("file") MultipartFile file
    ){
        return R.decide(advertisementService.releaseAdvertisement(title,file));
    }

    /**
     * 根据id删除广告
     *
     * @param id 广告id
     * @return {@link R}<{@link Boolean}>
     */
    @ApiOperation("根据id删除广告")
    @PostMapping("/delAdvertisementById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "广告id", dataTypeClass = Long.class, paramType = "query", example = "112345646545", required = true)
    })
    public R<Boolean> delAdvertisementById(
            @RequestParam("id") @NotNull Long id
    ){
        return R.decide(advertisementService.delAdvertisementById(id));
    }


    /**
     * 根据id更新广告
     *
     * @param id    广告id
     * @param file  文件
     * @param title 标题
     * @return {@link R}<{@link Boolean}>
     */
    @SneakyThrows
    @ApiOperation("根据id更新广告")
    @PostMapping("/modifyAdvertisementById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "广告id", dataTypeClass = Long.class, paramType = "query", example = "112345646545", required = true),
            @ApiImplicitParam(name = "title", value = "标题", dataTypeClass = String.class, paramType = "query", example = "成果", required = true),
            @ApiImplicitParam(name = "file", value = "图片文件", required = true, dataTypeClass = MultipartFile.class, paramType = "query")
    })
    public R<Boolean> modifyAdvertisementById(
            @RequestParam("id") @NotNull Long id,
            @RequestParam(value = "title",defaultValue = "农营C") String title,
            @RequestParam("file") MultipartFile file
    ){
        return R.decide(advertisementService.modifyAdvertisementById(id,title,file));
    }


    /**
     * 按id修改广告状态
     *
     * @param id     身份证件
     * @param status 状态
     * @return {@link R}<{@link Boolean}>
     */
    @SneakyThrows
    @ApiOperation("根据id更新广告 状态")
    @PostMapping("/modifyAdvertisementStatusById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "广告id", dataTypeClass = Long.class, paramType = "query", example = "112345646545", required = true),
            @ApiImplicitParam(name = "status", value = "status", dataTypeClass = Integer.class, paramType = "query", example = "1", required = true)
    })
    public R<Boolean> modifyAdvertisementStatusById(
            @RequestParam("id") @NotNull Long id,
            @RequestParam("status") @NotNull @Min(0) @Max(1) Integer status
    ){
        return R.decide(advertisementService.modifyAdvertisementStatusById(id,status));
    }


}
