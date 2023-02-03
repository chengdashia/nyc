package com.git.bds.nyc.applet.api.service.order;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.git.bds.nyc.communal.model.dto.OrderDataDTO;
import com.git.bds.nyc.communal.mapper.mp.ContractOrderMapper;
import com.git.bds.nyc.communal.model.domain.ContractOrder;
import com.git.bds.nyc.communal.model.dto.OrderDTO;
import com.git.bds.nyc.communal.util.Base64Util;
import com.git.bds.nyc.enums.ProductType;
import com.git.bds.nyc.exception.BusinessException;
import com.git.bds.nyc.framework.file.minio.MinioConfig;
import com.git.bds.nyc.framework.file.minio.MinioUtil;
import com.git.bds.nyc.page.PageParam;
import com.git.bds.nyc.page.PageResult;
import com.git.bds.nyc.product.mapper.mp.CorpProcessingProductMapper;
import com.git.bds.nyc.product.mapper.mp.primary.corp.CorpPrimaryProductMapper;
import com.git.bds.nyc.product.mapper.mp.primary.farmer.FarmerPrimaryProductMapper;
import com.git.bds.nyc.product.model.domain.CorpPrimaryProduct;
import com.git.bds.nyc.product.model.domain.CorpProcessingProduct;
import com.git.bds.nyc.product.model.domain.FarmerPrimaryProduct;
import com.git.bds.nyc.result.ResultCode;
import com.git.bds.nyc.util.DecimalUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.math.BigDecimal;

/**
 * @author 成大事
 * @since 2023/2/3 16:22
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OrderServiceImpl implements OrderService{

    private final FarmerPrimaryProductMapper farmerPrimaryProductMapper;

    private final CorpProcessingProductMapper corpProcessingProductMapper;

    private final CorpPrimaryProductMapper corpPrimaryProductMapper;

    private final ContractOrderMapper contractOrderMapper;

    private final MinioConfig minioConfig;

    private final MinioUtil minioUtil;

    /**
     * 下单
     *
     * @param orderDTO 订单dto
     * @return {@link Boolean}
     */
    @SneakyThrows
    @Override
    public Boolean placeOrder(OrderDTO orderDTO) {
        //买家id
        long buyerId = StpUtil.getLoginIdAsLong();
        Long productId = orderDTO.getProductId();
        Integer type = orderDTO.getType();
        BigDecimal orderWeight = orderDTO.getOrderWeight();
        BigDecimal unitPrice = null;
        String productSpecies = null;
        String productVariety = null;
        Long sellerContactInfoId = null;
        Long sellerId = null;
        if(ProductType.FARMER_PRIMARY.getValue().equals(type)){
            FarmerPrimaryProduct farmerPrimaryProduct = farmerPrimaryProductMapper.selectOne(new LambdaQueryWrapper<FarmerPrimaryProduct>()
                    .select(
                            FarmerPrimaryProduct::getProductSpecies,
                            FarmerPrimaryProduct::getUserId,
                            FarmerPrimaryProduct::getProductVariety,
                            FarmerPrimaryProduct::getProductPrice,
                            FarmerPrimaryProduct::getProductWeight,
                            FarmerPrimaryProduct::getContactInfoId
                    )
                    .eq(FarmerPrimaryProduct::getId,productId)
            );
            //有数据
            if(farmerPrimaryProduct == null){
                throw new BusinessException(ResultCode.NOT_EXIST.getCode(),ResultCode.NOT_EXIST.getMessage());
            }
            //如果库存量不够
            if(Boolean.TRUE.equals(DecimalUtils.lessThan(farmerPrimaryProduct.getProductWeight(),orderWeight))){
                throw new BusinessException(ResultCode.INSUFFICIENT_STOCK.getCode(),ResultCode.INSUFFICIENT_STOCK.getMessage());
            }
            unitPrice = farmerPrimaryProduct.getProductPrice();
            productSpecies = farmerPrimaryProduct.getProductSpecies();
            productVariety = farmerPrimaryProduct.getProductVariety();
            sellerContactInfoId = farmerPrimaryProduct.getContactInfoId();
            sellerId = farmerPrimaryProduct.getUserId();
        }else if(ProductType.CORP_PRIMARY.getValue().equals(type)){
            CorpPrimaryProduct corpPrimaryProduct = corpPrimaryProductMapper.selectOne(new LambdaQueryWrapper<CorpPrimaryProduct>()
                    .select(
                            CorpPrimaryProduct::getProductSpecies,
                            CorpPrimaryProduct::getUserId,
                            CorpPrimaryProduct::getProductVariety,
                            CorpPrimaryProduct::getProductPrice,
                            CorpPrimaryProduct::getProductWeight
                    )
                    .eq(CorpPrimaryProduct::getId,productId)
            );
            if(ObjectUtil.isNull(corpPrimaryProduct)){
                throw new BusinessException(ResultCode.NOT_EXIST.getCode(),ResultCode.NOT_EXIST.getMessage());
            }
            //如果库存量不够
            if(Boolean.TRUE.equals(DecimalUtils.lessThan(corpPrimaryProduct.getProductWeight(),orderWeight))){
                throw new BusinessException(ResultCode.INSUFFICIENT_STOCK.getCode(),ResultCode.INSUFFICIENT_STOCK.getMessage());
            }
            unitPrice = corpPrimaryProduct.getProductPrice();
            productSpecies = corpPrimaryProduct.getProductSpecies();
            productVariety = corpPrimaryProduct.getProductVariety();
            sellerContactInfoId = corpPrimaryProduct.getContactInfoId();
            sellerId = corpPrimaryProduct.getUserId();
        }else if(ProductType.CORP_PROCESSING.getValue().equals(type)){
            CorpProcessingProduct corpProcessingProduct = corpProcessingProductMapper.selectOne(new LambdaQueryWrapper<CorpProcessingProduct>()
                    .select(
                            CorpProcessingProduct::getProductSpecies,
                            CorpProcessingProduct::getUserId,
                            CorpProcessingProduct::getProductVariety,
                            CorpProcessingProduct::getProductPrice,
                            CorpProcessingProduct::getProductWeight
                    )
                    .eq(CorpProcessingProduct::getId,productId)
            );
            if(corpProcessingProduct == null){
                throw new BusinessException(ResultCode.NOT_EXIST.getCode(),ResultCode.NOT_EXIST.getMessage());
            }
            //如果库存量不够
            if(Boolean.TRUE.equals(DecimalUtils.lessThan(corpProcessingProduct.getProductWeight(),orderWeight))){
                throw new BusinessException(ResultCode.INSUFFICIENT_STOCK.getCode(),ResultCode.INSUFFICIENT_STOCK.getMessage());
            }
            unitPrice = corpProcessingProduct.getProductPrice();
            productSpecies = corpProcessingProduct.getProductSpecies();
            productVariety = corpProcessingProduct.getProductVariety();
            sellerContactInfoId = corpProcessingProduct.getContactInfoId();
            sellerId = corpProcessingProduct.getUserId();
        }
        //获取签名的字节流
        InputStream inputStream = Base64Util.generateImageStream(orderDTO.getBuyerSignature());
        String signPath = minioUtil.uploadSign(minioConfig.getBucketName(), inputStream, buyerId);
        ContractOrder contractOrder = new ContractOrder();
        contractOrder.setSellerId(sellerId);
        contractOrder.setBuyerId(buyerId);
        contractOrder.setProductId(productId);
        contractOrder.setProductSpecies(productSpecies);
        contractOrder.setProductVariety(productVariety);
        contractOrder.setType(type);
        contractOrder.setSellerContactInfoId(sellerContactInfoId);
        contractOrder.setBuyerContactInfoId(orderDTO.getBuyerContactInfoId());
        //买家签名地址
        contractOrder.setBuyerSignature(signPath);
        contractOrder.setUnitPrice(unitPrice);
        contractOrder.setOrderWeight(orderWeight);
        contractOrder.setRemark(orderDTO.getRemark());
        return contractOrderMapper.insert(contractOrder) > 0;
    }

    /**
     * 获取订单页面
     *
     * @param pageParam 页面参数
     * @param type      类型
     * @return {@link PageResult}<{@link ContractOrder}>
     */
    @Override
    public PageResult<ContractOrder> getOrderPage(PageParam pageParam, int type) {
        Page<ContractOrder> page = contractOrderMapper.selectPage(new Page<>(pageParam.getPageNo(), pageParam.getPageSize()),
                new LambdaQueryWrapper<ContractOrder>()
                        .select(
                                ContractOrder::getId,
                                ContractOrder::getProductId,
                                ContractOrder::getProductSpecies,
                                ContractOrder::getProductVariety,
                                ContractOrder::getOrderWeight,
                                ContractOrder::getCreateTime
                        )
                        .eq(ContractOrder::getBuyerId, StpUtil.getLoginIdAsLong())
                        .eq(ContractOrder::getType, type)
                        .orderByAsc(ContractOrder::getCreateTime));
        return new PageResult<>(page.getRecords(),page.getTotal());
    }

    /**
     * 获取各种订单数量
     *
     * @return {@link OrderDataDTO}
     */
    @Override
    public OrderDataDTO getQuantitiesOfVariousOrders() {
        long userId = StpUtil.getLoginIdAsLong();
        contractOrderMapper.getQuantitiesOfVariousOrders(userId);
        return null;
    }
}
