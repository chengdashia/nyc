package com.git.bds.nyc.demand.service;

import com.git.bds.nyc.demand.model.domain.Demand;
import com.git.bds.nyc.demand.model.dto.DemandDTO;
import com.git.bds.nyc.demand.model.dto.DemandInfoDTO;
import com.git.bds.nyc.demand.model.dto.DemandAddDTO;
import com.git.bds.nyc.demand.model.dto.DemandModifyDTO;
import com.git.bds.nyc.page.PageParam;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * <p>
 * 需求表 服务类
 * </p>
 *
 * @author 成大事
 * @since 2022-10-31 15:59:15
 */
public interface DemandService extends MPJBaseService<Demand> {

    /**
     * 主页需求（按页面）
     *
     * @param pageParam 页面参数
     * @return {@link List}<{@link DemandDTO}>
     */
    List<DemandDTO> homePageDemandsByPage(PageParam pageParam);

    /**
     * 获取需求信息
     *
     * @param id 身份证件
     * @return {@link DemandInfoDTO}
     */
    DemandInfoDTO getDemandInfo(Long id);

    /**
     * 发布需求
     *
     * @param demandAddDTO 需求操作数据
     * @return {@link Boolean}
     */
    Boolean releaseDemand(DemandAddDTO demandAddDTO);

    /**
     * 修改需求信息
     *
     * @param demandModifyDTO 需求修改数据
     * @return {@link Boolean}
     */
    Boolean modifyDemandInfo(DemandModifyDTO demandModifyDTO);
}
