package git.bds.nyc.communal.convert;

import git.bds.nyc.communal.model.domain.Address;
import git.bds.nyc.communal.model.dto.AddressAddDTO;
import git.bds.nyc.communal.model.dto.AddressModifyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author 成大事
 * @since 2022/11/26 19:29
 */
@Mapper
public interface AddressConvert {

    AddressConvert INSTANCE = Mappers.getMapper(AddressConvert.class);

    /**
     * 至购物地址
     *
     * @param addressAddDTO 地址添加数据
     * @param userId        用户id
     * @param type          类型
     * @return {@link Address}
     */
    @Mapping(source = "userId",target = "userId")
    @Mapping(source = "type",target = "isDefault")
    Address toAddress(AddressAddDTO addressAddDTO, long userId, int type);


    /**
     * 至购物地址
     *
     * @param userId           用户id
     * @param addressModifyDTO 地址修改dto
     * @return {@link Address}
     */
    @Mapping(source = "userId",target = "userId")
    Address toAddress(AddressModifyDTO addressModifyDTO, long userId);

}
