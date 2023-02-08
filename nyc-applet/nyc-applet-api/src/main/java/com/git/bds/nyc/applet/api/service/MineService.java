package com.git.bds.nyc.applet.api.service;

import com.git.bds.nyc.applet.api.model.dto.NumberOfReleaseDTO;

/**
 * @author 成大事
 * @since 2023/2/7 20:43
 */
public interface MineService {

    /**
     * 获取发布次数
     *
     * @param type 类型
     * @return {@link NumberOfReleaseDTO}
     */
    NumberOfReleaseDTO getNumberOfReleases(int type);

    /**
     * 根据id删除发布的产品
     *
     * @param id     商品的id
     * @param type   类型(0：初级、1:加工)
     * @param status 状态(-1:审核,0:在售,1:预售)
     * @return {@link Boolean}
     */
    Boolean delReleaseProductById(Long id, int type, int status);

    /**
     * 按id立即发布产品
     *
     * @param id   身份证件
     * @param type 类型
     * @return {@link Boolean}
     */
    Boolean releaseProductNowById(Long id, int type);
}
