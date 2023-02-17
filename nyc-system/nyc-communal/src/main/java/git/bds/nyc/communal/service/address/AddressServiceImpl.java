package git.bds.nyc.communal.service.address;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import git.bds.nyc.communal.convert.AddressConvert;
import git.bds.nyc.communal.mapper.mp.AddressMapper;
import git.bds.nyc.communal.model.domain.Address;
import git.bds.nyc.communal.model.dto.AddressAddDTO;
import git.bds.nyc.communal.model.dto.AddressDTO;
import git.bds.nyc.enums.DefaultType;
import git.bds.nyc.exception.BusinessException;
import git.bds.nyc.result.ResultCode;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 收货地址表（也可用于发布商品和需求的时候用作发货地址表） 服务实现类
 * </p>
 *
 * @author 成大事
 * @since 2023-02-15 13:50:16
 */
@Service
public class AddressServiceImpl extends MPJBaseServiceImpl<AddressMapper, Address> implements AddressService {

    /**
     * 按id获取地址信息
     *
     * @param id 地址id
     * @return {@link Address}
     */
    @Override
    @Transactional
    public Address getAddressInfoById(Long id) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<Address>()
                .select(Address::getConsignee,
                        Address::getPhone,
                        Address::getLocation,
                        Address::getDetailedAddress)
                .eq(Address::getId,id));
    }

    /**
     * 添加地址
     *
     * @param addressAddDTO 地址添加数据
     * @return {@link Boolean}
     */
    @Override
    @Transactional
    public Boolean addAddress(AddressAddDTO addressAddDTO) {
        long userId = StpUtil.getLoginIdAsLong();
        List<Address> list = this.baseMapper.selectList(new LambdaQueryWrapper<Address>()
                .eq(Address::getUserId, userId));
        Address address;
        if(list.isEmpty()){
            address = AddressConvert.INSTANCE.toAddress(addressAddDTO, userId, DefaultType.IS_DEFAULT.getValue());
        }else {
            address = AddressConvert.INSTANCE.toAddress(addressAddDTO, userId, DefaultType.NOT_DEFAULT.getValue());
        }
        return this.baseMapper.insert(address) > 0;
    }

    /**
     * 修改默认地址
     *
     * @param id 身份证件
     * @return {@link Boolean}
     */
    @Override
    @Transactional
    public Boolean modifyDefaultAddress(Long id) {
        long userId = StpUtil.getLoginIdAsLong();
        Address one = this.baseMapper.selectOne(new LambdaQueryWrapper<Address>()
                .eq(Address::getId, id)
                .eq(Address::getUserId, userId));
        if(one == null){
            throw new BusinessException(ResultCode.NOT_EXIST.getCode(),ResultCode.NOT_EXIST.getMessage());
        }
        return this.baseMapper.modifyDefaultAddress(id,userId) > 0;
    }

    /**
     * 获取我的地址列表
     *
     * @return {@link List}<{@link AddressDTO}>
     */
    @Override
    @Transactional
    public List<AddressDTO> getMyAddress() {
        return this.baseMapper.selectJoinList(AddressDTO.class,
                new MPJLambdaWrapper<Address>()
                        .select(Address::getId,
                                Address::getConsignee,
                                Address::getPhone,
                                Address::getLocation,
                                Address::getDetailedAddress,
                                Address::getIsDefault)
                        .eq(Address::getUserId, StpUtil.getLoginIdAsLong()));
    }

}
