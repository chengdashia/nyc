package git.bds.nyc.applet.api.convert;

import git.bds.nyc.applet.api.model.vo.order.OrderDataVO;
import git.bds.nyc.applet.api.model.vo.order.OrderVO;
import git.bds.nyc.communal.model.domain.ContractOrder;
import git.bds.nyc.communal.model.dto.OrderDataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 成大事
 * @since 2023/2/3 16:31
 */
@Mapper
public interface OrderConvert {

    OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);


    /**
     * 订单转VO
     *
     * @param list 列表
     * @return {@link List}<{@link OrderVO}>
     */
    List<OrderVO> toOrderVO(List<ContractOrder> list);


    /**
     * 订购数据vo
     *
     * @param orderDataDTO 订单数据dto
     * @return {@link OrderDataVO}
     */
    @Mapping(source = "unSigned",target = "unSigned",defaultValue = "0")
    @Mapping(source = "signed",target = "signed",defaultValue = "0")
    @Mapping(source = "refuseToSign",target = "refuseToSign",defaultValue = "0")
    @Mapping(source = "successfulTrade",target = "successfulTrade",defaultValue = "0")
    OrderDataVO toOrderDataVO(OrderDataDTO orderDataDTO);
}
