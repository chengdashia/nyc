package git.bds.nyc.admin.mapper.mp;


import git.bds.nyc.admin.model.domain.CoopUser;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 合作社和农户关系表 Mapper 接口
 * </p>
 *
 * @author 成大事
 * @since 2023-01-06 15:02:50
 */
@Mapper
public interface CoopUserMapper extends MPJBaseMapper<CoopUser> {

}
