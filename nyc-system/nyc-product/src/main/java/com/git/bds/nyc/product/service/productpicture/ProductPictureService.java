package com.git.bds.nyc.product.service.productpicture;


import com.git.bds.nyc.product.model.domain.ProductPicture;
import com.github.yulichang.base.MPJBaseService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 成大事
 * @since 2022-09-07 13:55:14
 */
public interface ProductPictureService extends MPJBaseService<ProductPicture> {


    Boolean updateProductPicture(Long productId,List<String> productNewImgList);

}
