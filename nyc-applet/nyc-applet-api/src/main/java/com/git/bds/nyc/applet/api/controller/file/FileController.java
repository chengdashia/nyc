package com.git.bds.nyc.applet.api.controller.file;


import com.git.bds.nyc.applet.api.service.PrimaryProductFileService;
import com.git.bds.nyc.result.R;
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
@Api(tags = "产品图片")
@Slf4j
@Validated
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FileController {

    private final PrimaryProductFileService productFileService;


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
     * 上传身份证img
     *
     * @param uploadFiles 上载文件
     * @return {@link R}<{@link List}<{@link String}>>
     */
    @SneakyThrows
    @PostMapping("/uploadIdCardImg")
    @ApiOperation(value = "上传身份证",notes = "游客通过上传身份证 进行认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "图片文件", required = true,dataTypeClass = MultipartFile.class,allowMultiple = true,paramType = "query")
    })
    public R<List<String>> uploadIdCardImg(
            @RequestPart("files") MultipartFile[] uploadFiles
    ){

        return R.ok(productFileService.uploadIdCardImg(uploadFiles));
    }


    /**
     * 上载企业许可证img
     *
     * @param uploadFiles 上载文件
     * @return {@link R}<{@link List}<{@link String}>>
     */
    @SneakyThrows
    @PostMapping("/uploadEnterpriseLicenseImg")
    @ApiOperation(value = "uploadEnterpriseLicenseImg",notes = "游客通过上传身份证 进行认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "图片文件", required = true,dataTypeClass = MultipartFile.class,allowMultiple = true,paramType = "query")
    })
    public R<List<String>> uploadEnterpriseLicenseImg(
            @RequestPart("files") MultipartFile[] uploadFiles
    ){

        return R.ok(productFileService.uploadEnterpriseLicenseImg(uploadFiles));
    }
}
