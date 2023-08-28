package fun.fengwk.upms.core.service;

import fun.fengwk.convention4j.api.page.Page;
import fun.fengwk.convention4j.api.page.PageQuery;
import fun.fengwk.convention4j.common.NullSafe;
import fun.fengwk.upms.core.model.AppBO;
import fun.fengwk.upms.core.model.UserBO;
import fun.fengwk.upms.core.model.UserCreateBO;
import fun.fengwk.upms.core.model.UserUpdateBO;
import fun.fengwk.upms.core.facade.UserSystemFacade;
import fun.fengwk.upms.core.manager.UserSystemFacadeManager;
import fun.fengwk.upms.core.repo.AppRepository;
import fun.fengwk.upms.share.constant.UpmsErrorCodes;
import fun.fengwk.upms.share.model.UserDTO;
import fun.fengwk.upms.share.model.UserCreateDTO;
import fun.fengwk.upms.share.model.UserUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author fengwk
 */
@Slf4j
@AllArgsConstructor
@Validated
@Service
public class UserService {

    private final UserSystemFacadeManager userSystemFacadeManager;
    private final AppRepository appRepository;

    public UserDTO create(@NotNull UserCreateDTO createDTO) {
        UserSystemFacade userSystemFacade = userSystemFacadeManager
            .getUserSystemFacade(createDTO.getAppId());
        UserCreateBO createBO = UserCreateBO.fromDTO(createDTO);
        UserBO userBO = userSystemFacade.createUser(createBO);
        return NullSafe.map(userBO, UserBO::toDTO);
    }

    public boolean updateSelective(@NotNull UserUpdateDTO updateDTO) {
        UserSystemFacade userSystemFacade = userSystemFacadeManager
            .getUserSystemFacade(updateDTO.getAppId());
        UserUpdateBO updateBO = UserUpdateBO.fromDTO(updateDTO);
        return userSystemFacade.updateUserSelective(updateBO);
    }

    public boolean removeUser(@NotEmpty String appId, @NotEmpty String userId) {
        UserSystemFacade userSystemFacade = getUserSystemFacade(appId);
        return userSystemFacade.removeUser(userId);
    }

    public boolean getUser(@NotEmpty String appId, @NotEmpty String userId) {
        UserSystemFacade userSystemFacade = getUserSystemFacade(appId);
        return userSystemFacade.removeUser(userId);
    }

    public Page<UserDTO> pageUser(@NotEmpty String appId, @NotNull PageQuery pageQuery, String q) {
        UserSystemFacade userSystemFacade = getUserSystemFacade(appId);
        return userSystemFacade.pageUser(pageQuery, q).map(UserBO::toDTO);
    }

    private UserSystemFacade getUserSystemFacade(String appId) {
        AppBO appBO = appRepository.getById(appId);
        if (appBO == null) {
            log.warn("App not found, appId: {}", appId);
            throw UpmsErrorCodes.APP_NOT_FOUND.asThrowable();
        }

        UserSystemFacade userSystemFacade = userSystemFacadeManager
            .getUserSystemFacade(appBO.getUserSystemCode());
        if (userSystemFacade == null) {
            log.warn("UserSystemFacade not found, userSystemCode: {}",
                appBO.getUserSystemCode());
            throw UpmsErrorCodes.USER_SYSTEM_TYPE_NOT_FOUND.asThrowable();
        }
        return userSystemFacade;
    }

}
