package git.bds.nyc.user.service.user;

import git.bds.nyc.user.model.domain.User;
import git.bds.nyc.user.model.dto.UserViewDTO;
import git.bds.nyc.user.model.dto.WxUserInfoDTO;
import git.bds.nyc.user.model.vo.LoginVO;
import com.github.yulichang.base.MPJBaseService;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 成大事
 * @since 2022-08-14 19:28:00
 */
public interface UserService extends MPJBaseService<User> {

    /**
     * 登录
     *
     * @param wxUserInfoDTO WX用户信息DTO
     * @return {@link LoginVO}
     * @throws WxErrorException wx错误异常
     */
    LoginVO login(WxUserInfoDTO wxUserInfoDTO) throws WxErrorException;


    /**
     * 修改用户视图信息
     *
     * @param userViewDTO 用户视图dto
     * @return {@link Boolean}
     */
    Boolean modifyUserViewInfo(UserViewDTO userViewDTO);
}
