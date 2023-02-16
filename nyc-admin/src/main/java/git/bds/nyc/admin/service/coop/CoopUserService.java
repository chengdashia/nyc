package git.bds.nyc.admin.service.coop;


import git.bds.nyc.admin.model.domain.CoopUser;
import git.bds.nyc.admin.model.dto.CoopUserDTO;
import git.bds.nyc.page.PageParam;
import git.bds.nyc.page.PageResult;
import com.github.yulichang.base.MPJBaseService;

/**
 * <p>
 * 合作社和农户关系表 服务类
 * </p>
 *
 * @author 成大事
 * @since 2023-01-06 15:02:50
 */
public interface CoopUserService extends MPJBaseService<CoopUser> {

    /**
     * 通过合作社id获取下级农民
     *
     * @param pageParam
     * @param id        合作社id
     * @return {@link PageResult}<{@link CoopUserDTO}>
     */
    PageResult<CoopUserDTO> getSubordinateFarmerByCoopId(PageParam pageParam, Long id);
}
