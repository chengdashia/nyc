package com.git.bds.nyc.user.controller;

import com.git.bds.nyc.demand.model.dto.DemandAddDTO;
import com.git.bds.nyc.demand.model.dto.DemandModifyDTO;
import com.git.bds.nyc.demand.service.DemandService;
import com.git.bds.nyc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 成大事
 * @since 2022/12/7 13:08
 */
@Api(tags = "用户需求")
@Slf4j
@Validated
@RestController
@RequestMapping("/farmerDemand")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FarmerDemandController {

    private final DemandService demandService;

    /**
     * 发布需求
     *
     * @param demandAddDTO 按需添加数据
     * @return {@link R}<{@link Boolean}>
     */
    @PostMapping("/releaseDemand")
    @ApiOperation("发布需求")
    public R<Boolean> releaseDemand(
            @Validated @RequestBody DemandAddDTO demandAddDTO
    ){
        return R.decide(demandService.releaseDemand(demandAddDTO));
    }


    /**
     * 修改需求信息
     *
     * @param demandModifyDTO 需求修改dto
     * @return {@link R}<{@link Boolean}>
     */
    @PostMapping("/modifyDemandInfo")
    @ApiOperation("修改需求信息")
    public R<Boolean> modifyDemandInfo(
            @Validated @RequestBody DemandModifyDTO demandModifyDTO
    ){
        return R.decide(demandService.modifyDemandInfo(demandModifyDTO));
    }

    /**
     * 删除需求
     *
     * @param id 需求id
     * @return {@link = R<Boolean>}
     */
    @DeleteMapping("/delDemand/{id}")
    @ApiOperation("根据id删除需求数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id主键", dataTypeClass = Long.class, paramType = "path", example = "123456", required = true)
    })
    public R<Boolean> delete(@PathVariable(value = "id") Long id) {
        return R.decide(demandService.delDemand(id));
    }
}
