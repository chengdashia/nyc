package git.bds.nyc.corp.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import git.bds.nyc.enums.AuditType;
import git.bds.nyc.enums.ProductType;
import git.bds.nyc.exception.BusinessException;
import git.bds.nyc.product.mapper.ee.ProductEsMapper;
import git.bds.nyc.product.mapper.mp.CorpProcessingProductMapper;
import git.bds.nyc.product.mapper.mp.primary.corp.CorpPrimaryProductMapper;
import git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import git.bds.nyc.product.model.domain.CorpProcessingProduct;
import git.bds.nyc.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 成大事
 * @since 2022/12/29 20:42
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CropServiceImpl implements CorpService{

    private final CorpPrimaryProductMapper corpPrimaryProductMapper;

    private final CorpProcessingProductMapper corpProcessingProductMapper;

    private final ProductEsMapper productEsMapper;


    /**
     * 按id删除产品
     *
     * @param id   身份证件
     * @param type 类型
     * @return {@link Boolean}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteProductById(Long id, int type) {
        if (ProductType.CORP_PRIMARY.getValue().equals(type)){
            CorpPrimaryProduct corpPrimaryProduct = corpPrimaryProductMapper.selectOne(new LambdaQueryWrapper<CorpPrimaryProduct>()
                    .select(CorpPrimaryProduct::getAuditStatus));
            if(corpPrimaryProduct == null){
                throw new BusinessException(ResultCode.NOT_EXIST.getCode(),ResultCode.NOT_EXIST.getMessage());
            }else {
                if(AuditType.PASS.getValue().equals(corpPrimaryProduct.getAuditStatus())){
                    return productEsMapper.deleteById(id) > 0 && corpPrimaryProductMapper.deleteById(id) > 0;
                }else {
                    return corpPrimaryProductMapper.deleteById(id) > 0;
                }
            }
        }else {
            CorpProcessingProduct corpProcessingProduct = corpProcessingProductMapper.selectOne(new LambdaQueryWrapper<CorpProcessingProduct>()
                    .select(CorpProcessingProduct::getAuditStatus));
            if(corpProcessingProduct == null){
                throw new BusinessException(ResultCode.NOT_EXIST.getCode(),ResultCode.NOT_EXIST.getMessage());
            }else {
                if(AuditType.PASS.getValue().equals(corpProcessingProduct.getAuditStatus())){
                    return productEsMapper.deleteById(id) > 0 && corpProcessingProductMapper.deleteById(id) > 0;
                }else {
                    return corpProcessingProductMapper.deleteById(id) > 0;
                }
            }
        }
    }
}
