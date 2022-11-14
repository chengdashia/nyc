package com.git.bds.nyc.admin.controller;

import com.git.bds.nyc.admin.service.SysAdminService;
import com.git.bds.nyc.util.validate.phone.Phone;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author 成大事
 * @since 2022/11/14 16:39
 */
@Api(tags = "农户的 浏览记录和收藏记录")
@Validated
@RestController
@RequestMapping("/farmerHistoryCollection")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SysAdminController {

    private final SysAdminService adminService;

    /**
     * 普通登录,使用手机号+密码
     * @param account  手机号
     * @param pwd    密码
     * @return  R
     */
    @ApiOperation(value = "使用手机号+密码进行登录",notes = "13885052724")
    @PostMapping("/loginByPassword")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="account",dataTypeClass = String.class,required=true,value="手机号",example = "13885052724"),
            @ApiImplicitParam(paramType="query",name="pwd",dataTypeClass = String.class,required=true,value="密码",example = "zjysb")
    })
    public String loginByPassword(
            @RequestParam("account") @Phone String account,
            @RequestParam("userPwd") @NotBlank(message = "密码不能为空") String pwd
    ){
        return adminService.loginByPwd(account,pwd);
    }


}
