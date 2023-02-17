package git.bds.nyc.applet.api.service.demand;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import git.bds.nyc.communal.service.audit.AuditCorpDemandService;
import git.bds.nyc.demand.convert.DemandCovert;
import git.bds.nyc.demand.mapper.mp.CorpDemandMapper;
import git.bds.nyc.demand.model.domain.CorpDemand;
import git.bds.nyc.demand.model.dto.DemandAddDTO;
import git.bds.nyc.demand.model.dto.DemandDTO;
import git.bds.nyc.demand.model.dto.DemandInfoDTO;
import git.bds.nyc.demand.model.dto.DemandModifyDTO;
import git.bds.nyc.enums.RoleType;
import git.bds.nyc.framework.file.minio.MinioConfig;
import git.bds.nyc.framework.file.minio.MinioUtil;
import git.bds.nyc.page.PageParam;
import com.github.yulichang.query.MPJQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 成大事
 * @since 2023/2/16 21:37
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DemandServiceImpl implements DemandService {

    private final CorpDemandMapper corpDemandMapper;

    private final AuditCorpDemandService auditCorpDemandService;

    private final MinioUtil minioUtil;

    private final MinioConfig minioConfig;

    /**
     * 主页需求（按页面）
     *
     * @param pageParam 页面参数
     * @return {@link List}<{@link DemandDTO}>
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<DemandDTO> homePageDemandsByPage(PageParam pageParam) {
        return corpDemandMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),DemandDTO.class,
                new MPJLambdaWrapper<CorpDemand>()
                        .select(CorpDemand::getId
                                , CorpDemand::getDemandSpecies
                                , CorpDemand::getDemandVariety
                                , CorpDemand::getDemandWeight
                                , CorpDemand::getDemandPrice
                                , CorpDemand::getDetailedAddress
                                , CorpDemand::getDemandCover)
                        .orderByDesc(CorpDemand::getCreateTime)).getRecords();
    }

    /**
     * 根据需求id获取需求的详细数据集
     *
     * @param id 需求id
     * @return {@link DemandInfoDTO}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public DemandInfoDTO getDemandInfoById(Long id) {
        return corpDemandMapper.selectJoinOne(DemandInfoDTO.class,
                new MPJQueryWrapper<CorpDemand>()
                        .select(CorpDemand.class,i->!i.getColumn().equals(CorpDemand.USER_ID))
                        .eq(CorpDemand.ID,id)
        );
    }

    /**
     * 发布需求
     *
     * @param demandAddDTO 需求操作数据
     * @return {@link Boolean}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean releaseDemand(DemandAddDTO demandAddDTO) {
        long userId = StpUtil.getLoginIdAsLong();
        List<String> roleList = StpUtil.getRoleList();
        CorpDemand demand;
        if(roleList.contains(RoleType.COOP.getMsg())){
            demand = DemandCovert.INSTANCE.toDemandForAdd(demandAddDTO, userId);
            return auditCorpDemandService.addAudit(userId,demand.getId()) && corpDemandMapper.insert(demand) > 0;
        }
        return false;
    }

    /**
     * 修改需求信息
     *
     * @param demandModifyDTO 需求更改dto
     * @return {@link Boolean}
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean modifyDemandInfo(DemandModifyDTO demandModifyDTO) {
        CorpDemand demand = DemandCovert.INSTANCE.toDemandForModify(demandModifyDTO, StpUtil.getLoginIdAsLong());
        return corpDemandMapper.updateById(demand) > 0;
    }

    /**
     * 根据id删除需求数据
     *
     * @param id 需求id
     * @return {@link Boolean}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delDemandById(Long id) {
        String demandCover = corpDemandMapper.selectOne(new LambdaQueryWrapper<CorpDemand>()
                .select(CorpDemand::getDemandCover)
                .eq(CorpDemand::getId, id)).getDemandCover();
        minioUtil.removeFile(minioConfig.getBucketName(),demandCover);
        return corpDemandMapper.deleteById(id) > 0;
    }
}
