package git.bds.nyc.applet.api.controller.file;


import git.bds.nyc.applet.api.service.AppletFileService;
import git.bds.nyc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 成大事
 * @since 2022/9/15 19:50
 */
@Api(tags = "文件管理接口")
@Slf4j
@Validated
@RestController
@RequestMapping("/applet/file")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FileController {

    private final AppletFileService productFileService;


    /**
     * 上载产品img
     *
     * @param uploadFiles 上载文件
     * @return {@link R}<{@link List}<{@link String}>>
     */
    @SneakyThrows
    @PostMapping("/uploadProductImg")
    @ApiOperation(value = "商品图片上传",notes = "产品分 农户初级农产品(0) 公司初级农产品(1) 公司加工农产品(2) 需求(3)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "图片文件", required = true,dataTypeClass = MultipartFile.class,allowMultiple = true,paramType = "query")
    })
    public R<List<String>> uploadProductImg(
        @RequestPart("files") MultipartFile[] uploadFiles
    ){
        return R.ok( productFileService.uploadProductImg(uploadFiles));
    }


    /**
     * 上传需求img
     *
     * @param uploadFiles 上载文件
     * @return {@link R}<{@link List}<{@link String}>>
     */
    @SneakyThrows
    @PostMapping("/uploadDemandImg")
    @ApiOperation(value = "需求图片上传",notes = "产品分 农户初级农产品(0) 公司初级农产品(1) 公司加工农产品(2) 需求(3)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "图片文件", required = true,dataTypeClass = MultipartFile.class,allowMultiple = true,paramType = "query")
    })
    public R<List<String>> uploadDemandImg(
            @RequestPart("files") MultipartFile[] uploadFiles
    ){
        return R.ok( productFileService.uploadDemandImg(uploadFiles));
    }

    /**
     * 上传用户头像
     *
     * @param file 上载文件
     * @return {@link R}<{@link String}>
     */
    @SneakyThrows
    @PostMapping("/uploadAvatar")
    @ApiOperation(value = "上传用户头像",notes = "微信用户可以选择使用自己的微信头像或者选择其他的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "图片文件", required = true,dataTypeClass = MultipartFile.class,allowMultiple = true,paramType = "query")
    })
    public String uploadAvatar(
            @RequestPart("file") MultipartFile file
    ){
        return productFileService.uploadAvatar(file);
    }

    /**
     * 上传身份证正面img
     *
     * @param frontImg 正面img
     * @return {@link R}<{@link List}<{@link String}>>
     */
    @SneakyThrows
    @PostMapping("/uploadIdCardFrontImg")
    @ApiOperation(value = "上传正面的身份证图片",notes = "游客通过上传身份证 进行认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "frontImg", value = "身份证正面的图片文件", required = true,dataTypeClass = MultipartFile.class,allowMultiple = true,paramType = "query")
    })
    public String uploadIdCardFrontImg(
            @RequestPart("frontImg") MultipartFile frontImg
    ){
        return productFileService.uploadIdCardFrontImg(frontImg);
    }

    /**
     * 上传身份证背面img
     *
     * @param backImg 后退img
     * @return {@link R}<{@link List}<{@link String}>>
     */
    @SneakyThrows
    @PostMapping("/uploadIdCardBackImg")
    @ApiOperation(value = "上传背面的身份证图片",notes = "游客通过上传身份证 进行认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "backImg", value = "身份证背面的图片文件", required = true,dataTypeClass = MultipartFile.class,allowMultiple = true,paramType = "query")
    })
    public String uploadIdCardBackImg(
            @RequestPart("backImg") MultipartFile backImg
    ){
        return productFileService.uploadIdCardBackImg(backImg);
    }


    /**
     * 上载企业许可证img
     *
     * @param uploadFiles 上载文件
     * @return {@link R}<{@link List}<{@link String}>>
     */
    @SneakyThrows
    @PostMapping("/uploadEnterpriseLicenseImg")
    @ApiOperation(value = "上载企业许可证img",notes = "游客通过上传身份证 进行认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "图片文件", required = true,dataTypeClass = MultipartFile.class,allowMultiple = true,paramType = "query")
    })
    public R<List<String>> uploadEnterpriseLicenseImg(
            @RequestPart("files") MultipartFile[] uploadFiles
    ){

        return R.ok(productFileService.uploadEnterpriseLicenseImg(uploadFiles));
    }
}
