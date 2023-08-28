package fun.fengwk.upms.core.service;

import fun.fengwk.convention4j.api.page.Page;
import fun.fengwk.convention4j.api.page.PageQuery;
import fun.fengwk.convention4j.common.NullSafe;
import fun.fengwk.upms.core.model.UserSystemBO;
import fun.fengwk.upms.core.model.UserSystemTypeBO;
import fun.fengwk.upms.core.facade.UserSystemFacade;
import fun.fengwk.upms.core.manager.UserSystemFacadeManager;
import fun.fengwk.upms.core.repo.UserSystemRepository;
import fun.fengwk.upms.share.constant.UpmsErrorCodes;
import fun.fengwk.upms.share.model.UserSystemCreateDTO;
import fun.fengwk.upms.share.model.UserSystemDTO;
import fun.fengwk.upms.share.model.UserSystemTypeDTO;
import fun.fengwk.upms.share.model.UserSystemUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author fengwk
 */
@Slf4j
@AllArgsConstructor
@Validated
@Service
public class UserSystemService {

    private final UserSystemFacadeManager userSystemFacadeManager;
    private final UserSystemRepository userSystemRepository;

    public List<UserSystemTypeDTO> listAllTypes() {
        List<UserSystemTypeBO> userSystemTypeBOs = userSystemFacadeManager.listAllTypes();
        return NullSafe.map(userSystemTypeBOs, UserSystemTypeBO::toDTO);
    }

    @Transactional
    public void create(@NotNull UserSystemCreateDTO createDTO) {
        if (userSystemRepository.existsByCode(createDTO.getCode())) {
            log.warn("Duplicate user system code, userSystemCode: {}", createDTO.getCode());
            throw UpmsErrorCodes.DUPLICATE_USER_SYSTEM_CODE.asThrowable();
        }

        UserSystemBO userSystemBO = UserSystemBO.create(createDTO);
        if (!userSystemRepository.add(userSystemBO)) {
            log.error("Create user system failed, userSystemBO: {}", userSystemBO);
            throw UpmsErrorCodes.CREATE_USER_SYSTEM_FAILED.asThrowable();
        }

        UserSystemFacade userSystemFacade = userSystemFacadeManager
            .getUserSystemFacade(userSystemBO.getCode());
        userSystemFacade.createUserSystem();
    }

    public void updateSelective(@NotNull UserSystemUpdateDTO updateDTO) {
        if (!userSystemRepository.existsByCode(updateDTO.getCode())) {
            log.warn("User system not found, userSystemCode: {}", updateDTO.getCode());
            throw UpmsErrorCodes.USER_SYSTEM_NOT_FOUND.asThrowable();
        }
        if (!userSystemFacadeManager.containsType(updateDTO.getType())) {
            log.warn("User system type not found, userSystemType: {}", updateDTO.getType());
            throw UpmsErrorCodes.USER_SYSTEM_TYPE_NOT_FOUND.asThrowable();
        }

        UserSystemBO selectiveBO = UserSystemBO.updateSelective(updateDTO);
        if (!userSystemRepository.updateSelective(selectiveBO)) {
            log.warn("Update user system failed, updateDTO: {}", updateDTO);
            throw UpmsErrorCodes.UPDATE_USER_SYSTEM_FAILED.asThrowable();
        }
    }

    public void remove(String userSystemCode) {
        if (!userSystemRepository.existsByCode(userSystemCode)) {
            log.warn("User system not found, userSystemCode: {}", userSystemCode);
            throw UpmsErrorCodes.USER_SYSTEM_NOT_FOUND.asThrowable();
        }

        if (!userSystemRepository.removeByCode(userSystemCode)) {
            log.warn("Remove user system failed, userSystemCode: {}", userSystemCode);
            throw UpmsErrorCodes.REMOVE_USER_SYSTEM_FAILED.asThrowable();
        }
    }

    public Page<UserSystemDTO> page(PageQuery pageQuery, String q) {
        Page<UserSystemBO> page = userSystemRepository.page(pageQuery, q);
        return page.map(UserSystemBO::toDTO);
    }

}
