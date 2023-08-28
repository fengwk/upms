package fun.fengwk.upms.share.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 注册方式
 *
 * @author fengwk
 */
@Getter
@AllArgsConstructor
public enum SignInMode {

    USERNAME_PASSWORD("username+password"),
    EMAIL_VALID_CODE("email+validCode"),
    MOBILE_VALID_CODE("mobile+validCode");

    private final String code;

    public static SignInMode of(String code) {
        for (SignInMode mode : values()) {
            if (Objects.equals(mode.getCode(), code)) {
                return mode;
            }
        }
        return null;
    }

}
