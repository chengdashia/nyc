package com.git.bds.nyc.applet.api.controller.index;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 成大事
 * @since 2023/2/15 19:00
 */
@Api(tags = "首页接口管理")
@Slf4j
@Validated
@RestController
@RequestMapping("/applet/index")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReleaseController {


}
