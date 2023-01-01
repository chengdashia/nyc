package com.git.bds.nyc.applet.api.controller.demand;

import com.git.bds.nyc.applet.api.controller.demand.vo.DemandInfoVO;
import com.git.bds.nyc.applet.api.controller.demand.vo.DemandVO;
import com.git.bds.nyc.applet.api.convert.demand.DemandConvert;


import com.git.bds.nyc.demand.model.dto.DemandDTO;
import com.git.bds.nyc.demand.model.dto.DemandInfoDTO;
import com.git.bds.nyc.demand.service.DemandService;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResponse;
import com.git.bds.nyc.page.PageUtil;
import com.git.bds.nyc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 需求表 前端控制器
 * </p>
 *
 * @author 成大事
 * @since 2022-10-31 15:59:15
 */
@Api(tags = "需求")
@Slf4j
@Validated
@RestController
@RequestMapping("/demand")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DemandController {

    private final DemandService demandService;

    @ApiOperation("首页 需求数据")
    @GetMapping("/indexDemand")
    public R<PageResponse<DemandVO>> homePageDemandsByPage(
            @Valid PageParam pageParam
    ){
        List<DemandDTO> demandDTOList = demandService.homePageDemandsByPage(pageParam);
        List<DemandVO> demandVOList = DemandConvert.INSTANCE.toDemandVO(demandDTOList);
        PageResponse<DemandVO> result = PageUtil.toPage(demandVOList, pageParam);
        return R.ok(result);
    }

    @ApiOperation("需求的详细数据集")
    @PostMapping("/getDemandInfo/{id}")
    @ApiImplicitParam(name = "id", value = "需求id", required = true, dataTypeClass = Long.class)
    public R<DemandInfoVO> getDemandInfo(
            @PathVariable("id") Long id
    ){
        DemandInfoDTO product = demandService.getDemandInfo(id);
        return R.ok(DemandConvert.INSTANCE.toDemandInfoVO(product));
    }

}
