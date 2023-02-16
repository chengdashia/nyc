package git.bds.nyc.admin.convert;

import git.bds.nyc.demand.model.domain.CorpDemand;
import git.bds.nyc.demand.model.ee.DemandEs;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 成大事
 * @since 2023/1/6 12:56
 */
@Mapper
public interface DemandConvert {

    DemandConvert INSTANCE = Mappers.getMapper(DemandConvert.class);

    /**
     * 映射到es
     *
     * @param corpDemand 企业需求
     * @param type       类型
     * @return {@link DemandEs}
     */
    DemandEs toDemandEs(CorpDemand corpDemand,int type);

}
