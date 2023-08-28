package fun.fengwk.upms.core.service;

import fun.fengwk.upms.core.model.AppBO;
import fun.fengwk.upms.core.repo.AppRepository;
import fun.fengwk.upms.share.constant.UpmsErrorCodes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;

/**
 * 登陆服务
 * @author fengwk
 */
@Slf4j
@AllArgsConstructor
@Service
public class SignInService {

    private final AppRepository appRepository;

    public void sendEmailValidCode(@NotEmpty String appId, @NotEmpty String email) {
        AppBO appBO = getApp(appId);
        // TODO
    }

    public void sendMobileValidCode(@NotEmpty String appId, @NotEmpty String mobile) {
        AppBO appBO = getApp(appId);
        // TODO
    }

    public void signUpByUsernamePassword(@NotEmpty String appId, @NotEmpty String username, @NotEmpty String password) {
        AppBO appBO = getApp(appId);
        // TODO
    }

    public void signUpByEmailValidCode(@NotEmpty String appId, @NotEmpty String email, @NotEmpty String validCode) {
        AppBO appBO = getApp(appId);
        // TODO
    }

    public void signUpByMobileValidCode(@NotEmpty String appId, @NotEmpty String mobile, @NotEmpty String validCode) {
        AppBO appBO = getApp(appId);
        // TODO
    }

    private AppBO getApp(String appId) {
        AppBO appBO = appRepository.getById(appId);
        if (appBO == null) {
            log.warn("App not found, appId: {}", appId);
            throw UpmsErrorCodes.APP_NOT_FOUND.asThrowable();
        }
        return appBO;
    }

}
