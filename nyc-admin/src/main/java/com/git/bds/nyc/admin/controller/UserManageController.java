package com.git.bds.nyc.admin.controller;

import com.git.bds.nyc.admin.convert.AdminConvert;
import com.git.bds.nyc.admin.model.vo.UserVO;
import com.git.bds.nyc.admin.service.SysAdminService;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.result.R;
import com.git.bds.nyc.user.domain.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 成大事
 * @since 2022/11/14 17:46
 */
@Api(tags = "用户管理")
@Validated
@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserManageController {

    private final SysAdminService adminService;


    @ApiOperation("获取用户列表 分页")
    @PostMapping("/getUserByPage")
    public R<PageResult<UserVO>> getUserByPage(
            @Validated @RequestBody PageParam pageParam
    ){
        PageResult<UserDTO> page = adminService.getUserByPage(pageParam);
        List<UserVO> userVOList = AdminConvert.INSTANCE.toUserVOList(page.getList());
        return R.ok(new PageResult<>(userVOList,page.getTotal()));
    }
}
