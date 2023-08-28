package fun.fengwk.upms.core.model;

import fun.fengwk.auth.share.constant.OAuth2Mode;
import fun.fengwk.convention4j.common.NullSafe;
import fun.fengwk.upms.core.constant.ValidCodeScene;
import fun.fengwk.upms.core.constant.ValidCodeTargetType;
import fun.fengwk.upms.share.constant.SignInMode;
import fun.fengwk.upms.share.constant.SignUpMode;
import fun.fengwk.upms.share.constant.UpmsErrorCodes;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;

/**
 * @author fengwk
 */
@Slf4j
@Data
public class AppBO {

    private String id;
    private String secret;
    private String name;
    private String description;
    /* user system config */
    private String userSystemCode;
    /* support sign in or sign up modes */
    private Set<SignInMode> signInModes;
    private Set<SignUpMode> signUpModes;
    /* sign in valid code config */
    private Integer signInEmailDigits;
    private Integer signInEmailExpireSeconds;
    private List<Character> signInEmailValidCodeChars;
    private Integer signInMobileDigits;
    private Integer signInMobileExpireSeconds;
    private List<Character> signInMobileValidCodeChars;
    /* sign up valid code config */
    private Integer signUpEmailDigits;
    private Integer signUpEmailExpireSeconds;
    private List<Character> signUpEmailValidCodeChars;
    private Integer signUpMobileDigits;
    private Integer signUpMobileExpireSeconds;
    private List<Character> signUpMobileValidCodeChars;
    /* oauth2 config */
    private List<OAuth2Mode> oauth2Modes;
    private List<String> redirectUris;
    private Integer authorizationCodeExpireSeconds;
    private Integer accessTokenExpireSeconds;
    private Integer refreshTokenExpireSeconds;
    private Integer authorizationExpireSeconds;

    public ValidCodeConfigBO getValidCodeConfig(ValidCodeScene scene, ValidCodeTargetType targetType) {
        switch (scene) {
            case SIGN_UP:
                switch (targetType) {
                    case EMAIL:
                        if (!NullSafe.of(signUpModes).contains(SignUpMode.EMAIL_VALID_CODE)) {
                            log.warn("App does not support sign up by email, appId: {}", id);
                            throw UpmsErrorCodes.APP_UNSUPPORTED_VALID_CODE_MODE.asThrowable();
                        }
                        return new ValidCodeConfigBO(id, scene, targetType,
                            signUpEmailDigits, signUpEmailExpireSeconds, signUpEmailValidCodeChars);
                    case MOBILE:
                        if (!NullSafe.of(signUpModes).contains(SignUpMode.MOBILE_VALID_CODE)) {
                            log.warn("App does not support sign up by mobile, appId: {}", id);
                            throw UpmsErrorCodes.APP_UNSUPPORTED_VALID_CODE_MODE.asThrowable();
                        }
                        return new ValidCodeConfigBO(id, scene, targetType,
                            signUpMobileDigits, signUpMobileExpireSeconds, signUpMobileValidCodeChars);
                    default:
                        throw new UnsupportedOperationException("Unsupported target type: " + targetType);
                }
            case SIGN_IN:
                switch (targetType) {
                    case EMAIL:
                        if (!NullSafe.of(signInModes).contains(SignInMode.EMAIL_VALID_CODE)) {
                            log.warn("App does not support sign in by email, appId: {}", id);
                            throw UpmsErrorCodes.APP_UNSUPPORTED_VALID_CODE_MODE.asThrowable();
                        }
                        return new ValidCodeConfigBO(id, scene, targetType,
                            signInEmailDigits, signInEmailExpireSeconds, signInEmailValidCodeChars);
                    case MOBILE:
                        if (!NullSafe.of(signInModes).contains(SignInMode.EMAIL_VALID_CODE)) {
                            log.warn("App does not support sign in by mobile, appId: {}", id);
                            throw UpmsErrorCodes.APP_UNSUPPORTED_VALID_CODE_MODE.asThrowable();
                        }
                        return new ValidCodeConfigBO(id, scene, targetType,
                            signInMobileDigits, signInMobileExpireSeconds, signInMobileValidCodeChars);
                    default:
                        throw new UnsupportedOperationException("Unsupported target type: " + targetType);
                }
            default:
                throw new UnsupportedOperationException("Unsupported scene: " + scene);
        }
    }

}
