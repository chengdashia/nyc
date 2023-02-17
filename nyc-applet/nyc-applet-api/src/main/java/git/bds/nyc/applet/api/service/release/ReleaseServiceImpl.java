package git.bds.nyc.applet.api.service.release;

import cn.dev33.satoken.stp.StpUtil;
import git.bds.nyc.communal.mapper.mp.audit.AuditCorpProductMapper;
import git.bds.nyc.communal.service.audit.AuditCorpProductService;
import git.bds.nyc.communal.service.audit.CoopAuditProductService;
import git.bds.nyc.enums.RoleType;
import git.bds.nyc.exception.BusinessException;
import git.bds.nyc.product.convert.ProductConvert;
import git.bds.nyc.product.mapper.mp.primary.corp.CorpPrimaryProductMapper;
import git.bds.nyc.product.mapper.mp.primary.farmer.FarmerPrimaryProductMapper;
import git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import git.bds.nyc.product.model.domain.ProductPicture;
import git.bds.nyc.product.model.dto.ProductDTO;
import git.bds.nyc.product.service.productpicture.ProductPictureService;
import git.bds.nyc.result.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 成大事
 * @since 2023/2/15 19:06
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReleaseServiceImpl implements ReleaseService {

    private final FarmerPrimaryProductMapper farmerPrimaryProductMapper;

    private final CorpPrimaryProductMapper corpPrimaryProductMapper;

    private final ProductPictureService productPictureService;

    private final CoopAuditProductService coopAuditProductService;

    private final AuditCorpProductService auditCorpProductService;

    /**
     * 发布初级农产品
     *
     * @param productDTO 产品dto
     * @return {@link Boolean}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean releaseProduct(ProductDTO productDTO) {
        long userId = StpUtil.getLoginIdAsLong();
        //获取角色列表
        List<String> roleList = StpUtil.getRoleList();
        List<String> productImgList = productDTO.getProductImgList();
        String coverImg = productImgList.get(0);
        Long productId;
        if(roleList.contains(RoleType.FARMER.getMsg())){
            FarmerPrimaryProduct product = ProductConvert.INSTANCE.toFarmerPrimaryProduct(userId, coverImg,productDTO);
            // 插入
            farmerPrimaryProductMapper.insert(product);
            productId = product.getId();
            // 添加合作社审核
            coopAuditProductService.addAudit(userId,productId);
        }else if(roleList.contains(RoleType.COOP.getMsg())){
            CorpPrimaryProduct product = ProductConvert.INSTANCE.toCorpPrimaryProduct(userId, coverImg,productDTO);
            //插入
            corpPrimaryProductMapper.insert(product);
            productId = product.getId();
            // 添加审核
            auditCorpProductService.addAudit(userId,productId);
        }else {
            throw new BusinessException(ResultCode.NOT_ROLE.getCode(),ResultCode.NOT_ROLE.getMessage());
        }
        //循环将图片插入
        List<ProductPicture> productPictureList = new ArrayList<>(productImgList.size());
        for (String img : productImgList) {
            ProductPicture productPicture = new ProductPicture().setProductId(productId).setPictureUrl(img);
            productPictureList.add(productPicture);
        }
        productPictureService.saveBatch(productPictureList);
        return true;
    }

}
