package git.bds.nyc.applet.api.service.demand;

import git.bds.nyc.demand.model.dto.DemandAddDTO;
import git.bds.nyc.demand.model.dto.DemandDTO;
import git.bds.nyc.demand.model.dto.DemandInfoDTO;
import git.bds.nyc.demand.model.dto.DemandModifyDTO;
import git.bds.nyc.page.PageParam;

import java.util.List;

/**
 * @author 成大事
 * @since 2023/2/16 21:37
 */
public interface DemandService {


    /**
     * 主页需求（按页面）
     *
     * @param pageParam 页面参数
     * @return {@link List}<{@link DemandDTO}>
     */
    List<DemandDTO> homePageDemandsByPage(PageParam pageParam);

    /**
     * 根据需求id获取需求的详细数据集
     *
     * @param id 需求id
     * @return {@link DemandInfoDTO}
     */
    DemandInfoDTO getDemandInfoById(Long id);

    /**
     * 发布需求
     *
     * @param demandAddDTO 需求添加dto
     * @return {@link Boolean}
     */
    Boolean releaseDemand(DemandAddDTO demandAddDTO);

    /**
     * 修改需求信息
     *
     * @param demandModifyDTO 需求更改dto
     * @return {@link Boolean}
     */
    Boolean modifyDemandInfo(DemandModifyDTO demandModifyDTO);

    /**
     * 根据id删除需求数据
     *
     * @param id 需求id
     * @return {@link Boolean}
     */
    Boolean delDemandById(Long id);
}
