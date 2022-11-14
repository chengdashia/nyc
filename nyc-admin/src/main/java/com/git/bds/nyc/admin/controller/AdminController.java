package com.git.bds.nyc.admin.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 成大事
 * @since 2022/11/14 16:39
 */
@Api(tags = "农户的 浏览记录和收藏记录")
@Validated
@RestController
@RequestMapping("/farmerHistoryCollection")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AdminController {
}
