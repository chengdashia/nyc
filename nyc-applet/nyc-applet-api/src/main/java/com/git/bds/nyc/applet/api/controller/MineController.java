package com.git.bds.nyc.applet.api.controller;

import com.git.bds.nyc.applet.api.service.MineService;
import com.git.bds.nyc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 成大事
 * @since 2023/2/7 20:41
 */
@Api(tags = "个人中心接口")
@Slf4j
@Validated
@RestController
@RequestMapping("/applet/mine")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MineController {

    private final MineService mineService;

    @ApiOperation("获取发布的数量")
    @GetMapping("/getNumberOfReleases")
    public R<Long> getNumberOfReleases(){
        return R.ok(mineService.getNumberOfReleases());
    }
}
