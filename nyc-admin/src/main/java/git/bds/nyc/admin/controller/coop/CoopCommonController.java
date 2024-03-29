package git.bds.nyc.admin.controller.coop;

import git.bds.nyc.admin.enums.AdminType;
import git.bds.nyc.admin.service.admin.SysAdminService;
import git.bds.nyc.util.validate.phone.Phone;
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
 * @since 2023/1/6 16:20
 */
@Api(tags = "合作社 ")
@Validated
@RestController
@RequestMapping("/coop")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CoopCommonController {

    private final SysAdminService adminService;

    /**
     * 普通登录,使用手机号+密码
     * @param account  手机号
     * @param password    密码
     * @return  R
     */
    @ApiOperation(value = "使用手机号+密码进行登录",notes = "13885052724")
    @PostMapping("/loginByPassword")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="account",dataTypeClass = String.class,required=true,value="手机号",example = "13885052724"),
            @ApiImplicitParam(paramType="query",name="password",dataTypeClass = String.class,required=true,value="密码",example = "zjysb")
    })
    public String loginByPassword(
            @RequestParam("account") @Phone String account,
            @RequestParam("password") @NotBlank(message = "密码不能为空") String password
    ){
        return adminService.loginByPwd(account,password, AdminType.COOP.getValue());
    }

}
