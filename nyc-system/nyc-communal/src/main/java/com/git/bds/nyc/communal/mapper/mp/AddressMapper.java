package com.git.bds.nyc.communal.mapper.mp;


import com.git.bds.nyc.communal.model.domain.Address;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 收货地址表（也可用于发布商品和需求的时候用作发货地址表） Mapper 接口
 * </p>
 *
 * @author 成大事
 * @since 2023-02-15 13:50:16
 */
@Mapper
public interface AddressMapper extends MPJBaseMapper<Address> {


    /**
     * 修改默认地址
     *
     * @param id     身份证件
     * @param userId 用户id
     * @return int
     */
    int modifyDefaultAddress(@Param("id") Long id,@Param("userId") Long userId);

}
