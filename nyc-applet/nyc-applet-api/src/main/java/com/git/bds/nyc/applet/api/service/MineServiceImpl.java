package com.git.bds.nyc.applet.api.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.git.bds.nyc.enums.RoleType;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.product.mapper.mp.CorpProcessingProductMapper;
import com.git.bds.nyc.product.mapper.mp.primary.corp.CorpPrimaryProductMapper;
import com.git.bds.nyc.product.mapper.mp.primary.farmer.FarmerPrimaryProductMapper;
import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.domain.CorpProcessingProduct;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 成大事
 * @since 2023/2/7 20:43
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MineServiceImpl implements MineService{

    private final FarmerPrimaryProductMapper farmerPrimaryProductMapper;

    private final CorpPrimaryProductMapper corpPrimaryProductMapper;

    private final CorpProcessingProductMapper corpProcessingProductMapper;


    /**
     * 获取发布数量
     *
     * @return {@link Long}
     */
    @Override
    public Long getNumberOfReleases() {
        List<String> roleList = StpUtil.getRoleList();
        long userId = StpUtil.getLoginIdAsLong();
        if(roleList.contains(RoleType.FARMER.getMsg())){
            return farmerPrimaryProductMapper.selectCount(new LambdaQueryWrapper<FarmerPrimaryProduct>()
                    .eq(FarmerPrimaryProduct::getUserId, userId));
        }else if(roleList.contains(RoleType.COOP.getMsg())){
            Long primaryNum = corpPrimaryProductMapper.selectCount(new LambdaQueryWrapper<CorpPrimaryProduct>()
                    .eq(CorpPrimaryProduct::getUserId, userId));
            Long processingNum = corpProcessingProductMapper.selectCount(new LambdaQueryWrapper<CorpProcessingProduct>()
                    .eq(CorpProcessingProduct::getUserId, userId));
            return primaryNum + processingNum;
        }else {
            throw new BusinessException(ResultCode.NOT_ROLE.getCode(),ResultCode.NOT_EXIST.getMessage());
        }
    }
}
