package fun.fengwk.upms.core.facade;

import fun.fengwk.convention4j.api.page.Page;
import fun.fengwk.convention4j.api.page.PageQuery;
import fun.fengwk.upms.core.model.UserBO;
import fun.fengwk.upms.core.model.UserCreateBO;
import fun.fengwk.upms.core.model.UserUpdateBO;
import fun.fengwk.upms.share.constant.UpmsErrorCodes;
import fun.fengwk.upms.core.repo.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author fengwk
 */
@Slf4j
@AllArgsConstructor
public class DefaultUserSystemFacade implements UserSystemFacade {

    private final String userSystemCode;
    private final UserRepository userRepository;

    @Override
    public void createUserSystem() {
        userRepository.createUserSystem(userSystemCode);
    }

    @Override
    public UserBO createUser(UserCreateBO createBO) {
        if (createBO.getUsername() != null && userRepository.existsByUsername(
            userSystemCode, createBO.getUsername())) {
            log.warn("Duplicate username, userSystemCode: {}, username: {}",
                userSystemCode, createBO.getUsername());
            throw UpmsErrorCodes.DUPLICATE_USERNAME.asThrowable();
        }
        if (createBO.getEmail() != null && userRepository.existsByEmail(
            userSystemCode, createBO.getEmail())) {
            log.warn("Duplicate email, userSystemCode: {}, email: {}",
                userSystemCode, createBO.getEmail());
            throw UpmsErrorCodes.DUPLICATE_EMAIL.asThrowable();
        }
        if (createBO.getMobile() != null && userRepository.existsByMobile(
            userSystemCode, createBO.getMobile())) {
            log.warn("Duplicate mobile, userSystemCode: {}, mobile: {}",
                userSystemCode, createBO.getMobile());
            throw UpmsErrorCodes.DUPLICATE_MOBILE.asThrowable();
        }

        String id = userRepository.generateId();
        UserBO userBO = UserBO.create(userSystemCode, id, createBO);
        if (!userRepository.add(userSystemCode, userBO)) {
            log.error("Create user failed, userSystemCode: {}, userBO: {}",
                userSystemCode, userBO);
            throw UpmsErrorCodes.CREATE_USER_FAILED.asThrowable();
        }
        return userBO;
    }

    @Override
    public boolean updateUserSelective(UserUpdateBO updateBO) {
        if (userRepository.existsByUserId(userSystemCode, updateBO.getId())) {
            return false;
        }

        if (updateBO.getUsername() != null && userRepository.existsByUsername(
            userSystemCode, updateBO.getUsername())) {
            log.warn("Duplicate username, userSystemCode: {}, username: {}",
                userSystemCode, updateBO.getUsername());
            throw UpmsErrorCodes.DUPLICATE_USERNAME.asThrowable();
        }
        if (updateBO.getEmail() != null && userRepository.existsByEmail(
            userSystemCode, updateBO.getEmail())) {
            log.warn("Duplicate email, userSystemCode: {}, email: {}",
                userSystemCode, updateBO.getEmail());
            throw UpmsErrorCodes.DUPLICATE_EMAIL.asThrowable();
        }
        if (updateBO.getMobile() != null && userRepository.existsByMobile(
            userSystemCode, updateBO.getMobile())) {
            log.warn("Duplicate mobile, userSystemCode: {}, mobile: {}",
                userSystemCode, updateBO.getMobile());
            throw UpmsErrorCodes.DUPLICATE_MOBILE.asThrowable();
        }

        UserBO selectiveBO = UserBO.updateSelective(userSystemCode, updateBO);
        return userRepository.updateSelective(userSystemCode, selectiveBO);
    }

    @Override
    public boolean removeUser(String userId) {
        if (userRepository.existsByUserId(userSystemCode, userId)) {
            return false;
        }
        return userRepository.removeById(userSystemCode, userId);
    }

    @Override
    public UserBO getUser(String userId) {
        return userRepository.getById(userSystemCode, userId);
    }

    @Override
    public Page<UserBO> pageUser(PageQuery pageQuery, String q) {
        return userRepository.page(userSystemCode, pageQuery, q);
    }

}
