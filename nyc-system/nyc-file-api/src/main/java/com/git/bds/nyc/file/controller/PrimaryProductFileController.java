package com.git.bds.nyc.file.controller;

import com.git.bds.nyc.file.service.PrimaryProductFileService;
import com.git.bds.nyc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 成大事
 * @since 2022/9/15 19:50
 */
@Api(tags = "初级农产品 文件")
@Slf4j
@Validated
@RestController
@RequestMapping("/primaryProductFile")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PrimaryProductFileController {

    private final PrimaryProductFileService productFileService;


    @SneakyThrows
    @PostMapping("/upload")
    @ApiOperation("初级农产品 图片上传")
    @ApiImplicitParam(name = "files", value = "多文件", required = true,dataTypeClass = MultipartFile.class,allowMultiple = true,paramType = "query")
    public R<List<String>> upload(
        @RequestPart("files") MultipartFile[] uploadFiles
    ){

        return R.ok( productFileService.uploadFiles(uploadFiles));
    }
}
