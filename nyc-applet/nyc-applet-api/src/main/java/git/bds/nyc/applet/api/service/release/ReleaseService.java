package git.bds.nyc.applet.api.service.release;

import git.bds.nyc.product.model.dto.ProductDTO;

/**
 * @author 成大事
 * @since 2023/2/15 19:06
 */
public interface ReleaseService {

    /**
     * 发布初级农产品
     *
     * @param productDTO 产品dto
     * @return {@link Boolean}
     */
    Boolean releaseProduct(ProductDTO productDTO);

}
