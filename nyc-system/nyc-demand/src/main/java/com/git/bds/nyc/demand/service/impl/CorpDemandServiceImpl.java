package com.git.bds.nyc.demand.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.demand.convert.DemandCovert;
import com.git.bds.nyc.demand.mapper.mp.CorpDemandMapper;
import com.git.bds.nyc.demand.model.domain.CorpDemand;
import com.git.bds.nyc.demand.model.dto.DemandAddDTO;
import com.git.bds.nyc.demand.model.dto.DemandDTO;
import com.git.bds.nyc.demand.model.dto.DemandInfoDTO;
import com.git.bds.nyc.demand.model.dto.DemandModifyDTO;
import com.git.bds.nyc.demand.service.CorpDemandService;
import com.git.bds.nyc.framework.file.minio.MinioConfig;
import com.git.bds.nyc.framework.file.minio.MinioUtil;
import com.git.bds.nyc.page.PageParam;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 需求表 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2022-10-31 15:59:15
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CorpDemandServiceImpl extends MPJBaseServiceImpl<CorpDemandMapper, CorpDemand> implements CorpDemandService {

    private final MinioUtil minioUtil;

    private final MinioConfig minioConfig;

    /**
     * 主页需求（按页面）
     *
     * @param pageParam 页面参数
     * @return {@link List}<{@link DemandDTO}>
     */
    @Override
    public List<DemandDTO> homePageDemandsByPage(PageParam pageParam) {

        return this.baseMapper.selectJoinPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),DemandDTO.class,
                new MPJLambdaWrapper<>()
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
     * 获取需求信息
     *
     * @param id 身份证件
     * @return {@link DemandInfoDTO}
     */
    @Override
    public DemandInfoDTO getDemandInfo(Long id) {
        return this.baseMapper.selectJoinOne(DemandInfoDTO.class,
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
    public Boolean releaseDemand(DemandAddDTO demandAddDTO) {
        CorpDemand demand = DemandCovert.INSTANCE.toDemandForAdd(demandAddDTO, StpUtil.getLoginIdAsLong(), IdUtil.getSnowflakeNextId());
        return this.baseMapper.insert(demand) > 0;
    }

    /**
     * 修改需求信息
     *
     * @param demandModifyDTO 需求修改数据
     * @return {@link Boolean}
     */
    @Override
    public Boolean modifyDemandInfo(DemandModifyDTO demandModifyDTO) {
        CorpDemand demand = DemandCovert.INSTANCE.toDemandForModify(demandModifyDTO, StpUtil.getLoginIdAsLong());
        return this.baseMapper.updateById(demand) > 0;
    }

    /**
     * del需求
     *
     * @param id 身份证件
     * @return {@link Boolean}
     */
    @Override
    public Boolean delDemand(Long id) {
        String demandCover = this.baseMapper.selectOne(new LambdaQueryWrapper<CorpDemand>()
                .select(CorpDemand::getDemandCover)
                .eq(CorpDemand::getId, id)).getDemandCover();
        minioUtil.removeFile(minioConfig.getBucketName(),demandCover);
        return this.baseMapper.deleteById(id) > 0;
    }
}
