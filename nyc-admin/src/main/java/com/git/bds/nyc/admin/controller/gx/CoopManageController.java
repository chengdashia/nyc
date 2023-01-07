package com.git.bds.nyc.admin.controller.gx;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 成大事
 * @since 2023/1/7 14:03
 */
@Api(tags = "供销社管理合作社")
@Validated
@RestController
@RequestMapping("/admin/coop")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CoopManageController {

}
