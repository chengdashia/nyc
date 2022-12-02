package com.git.bds.nyc.file.controller;

import com.git.bds.nyc.file.service.PrimaryProductFileService;
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


    @SneakyThrows
    @PostMapping("/upload/{type}")
    @ApiOperation(value = "图片上传",notes = "产品分 农户初级农产品(0) 公司初级农产品(1) 公司加工农产品(2) 需求(3)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "图片文件", required = true,dataTypeClass = MultipartFile.class,allowMultiple = true,paramType = "query"),
            @ApiImplicitParam(name = "type", value = "类型", required = true,dataTypeClass = Integer.class,paramType = "path")
    })

    public R<List<String>> uploadPictures(
        @RequestPart("files") MultipartFile[] uploadFiles,
        @PathVariable("type") int type
    ){
        return R.ok( productFileService.uploadPictures(uploadFiles,type));
    }
}
