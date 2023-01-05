package com.git.bds.nyc.admin.service.audit;

import com.git.bds.nyc.demand.mapper.mp.DemandMapper;
import com.git.bds.nyc.demand.model.domain.Demand;
import com.git.bds.nyc.demand.model.dto.DemandInfoDTO;
import com.git.bds.nyc.enums.ProductType;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.product.mapper.mp.CorpProcessingProductMapper;
import com.git.bds.nyc.product.mapper.mp.primary.corp.CorpPrimaryProductMapper;
import com.git.bds.nyc.product.mapper.mp.primary.farmer.FarmerPrimaryProductMapper;
import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.domain.CorpProcessingProduct;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.product.model.domain.ProductPicture;
import com.git.bds.nyc.product.model.dto.AuditProductInfoDTO;
import com.git.bds.nyc.result.ResultCode;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 成大事
 * @since 2023/1/5 16:34
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuditCommonServiceImpl implements AuditCommonService{

    private final FarmerPrimaryProductMapper farmerPrimaryProductMapper;

    private final CorpPrimaryProductMapper corpPrimaryProductMapper;

    private final CorpProcessingProductMapper corpProcessingProductMapper;

    private final DemandMapper demandMapper;

    /**
     * 获取审核产品信息
     *
     * @param id   产品id
     * @param type 类型
     * @return {@link AuditProductInfoDTO}
     */
    @Override
    public AuditProductInfoDTO getAuditProductInfo(Long id, Integer type) {
        List<AuditProductInfoDTO> infoDTOList = null;
        if(ProductType.FARMER_PRIMARY.getValue().equals(type)){
            infoDTOList = farmerPrimaryProductMapper.selectJoinList(AuditProductInfoDTO.class,
                    new MPJLambdaWrapper<AuditProductInfoDTO>()
                            .select(FarmerPrimaryProduct.class, i -> !i.getColumn().equals(FarmerPrimaryProduct.CONTACT_INFO_ID)
                                    && !i.getColumn().equals(FarmerPrimaryProduct.COOP_AUDIT_STATUS)
                                    && !i.getColumn().equals(FarmerPrimaryProduct.AUDIT_STATUS)
                                    && !i.getColumn().equals(FarmerPrimaryProduct.DELETE_FLAG)
                            )
                            .select(ProductPicture::getPictureUrl)
                            .leftJoin(ProductPicture.class, ProductPicture::getProductId, FarmerPrimaryProduct::getId)
                            .eq(FarmerPrimaryProduct::getId, id));
        }else if(ProductType.CORP_PRIMARY.getValue().equals(type)){
            infoDTOList = corpPrimaryProductMapper.selectJoinList(AuditProductInfoDTO.class,
                    new MPJLambdaWrapper<AuditProductInfoDTO>()
                            .select(CorpPrimaryProduct.class, i -> !i.getColumn().equals(FarmerPrimaryProduct.CONTACT_INFO_ID)
                                    && !i.getColumn().equals(CorpPrimaryProduct.AUDIT_STATUS)
                                    && !i.getColumn().equals(CorpPrimaryProduct.DELETE_FLAG)
                            )
                            .select(ProductPicture::getPictureUrl)
                            .leftJoin(ProductPicture.class, ProductPicture::getProductId, CorpPrimaryProduct::getId)
                            .eq(CorpPrimaryProduct::getId, id));
        }else if(ProductType.CORP_PROCESSING.getValue().equals(type)){
            infoDTOList = corpProcessingProductMapper.selectJoinList(AuditProductInfoDTO.class,
                    new MPJLambdaWrapper<AuditProductInfoDTO>()
                            .select(CorpProcessingProduct.class, i -> !i.getColumn().equals(FarmerPrimaryProduct.CONTACT_INFO_ID)
                                    && !i.getColumn().equals(CorpProcessingProduct.AUDIT_STATUS)
                                    && !i.getColumn().equals(CorpProcessingProduct.DELETE_FLAG)
                            )
                            .select(ProductPicture::getPictureUrl)
                            .leftJoin(ProductPicture.class, ProductPicture::getProductId, CorpProcessingProduct::getId)
                            .eq(CorpProcessingProduct::getId, id));
        }
        if(infoDTOList == null){
            throw new BusinessException(ResultCode.NOT_EXIST.getCode(), ResultCode.NOT_EXIST.getMessage());
        }
        List<String> collect = infoDTOList.stream().map(AuditProductInfoDTO::getPictureUrl).collect(Collectors.toList());
        AuditProductInfoDTO auditProductInfoDTO = infoDTOList.get(0);
        auditProductInfoDTO.setImgList(collect);
        return auditProductInfoDTO;
    }

    /**
     * 获取审核需求信息
     *
     * @param id 需求id
     * @return {@link DemandInfoDTO}
     */
    @Override
    public DemandInfoDTO getAuditDemandInfo(Long id) {
        return demandMapper.selectJoinOne(DemandInfoDTO.class,
                new MPJLambdaWrapper<DemandInfoDTO>()
                        .select(Demand.class,i->!i.getColumn().equals(Demand.USER_ID))
                        .eq(Demand::getId,id)
        );
    }
}
