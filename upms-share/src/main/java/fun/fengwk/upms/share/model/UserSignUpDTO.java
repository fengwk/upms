package fun.fengwk.upms.share.model;

import fun.fengwk.upms.share.constant.SignUpMode;
import lombok.Data;

/**
 * @author fengwk
 */
@Data
public class UserSignUpDTO {

    /**
     * {@link SignUpMode}
     */
    private String signUpMode;
    private String username;
    private String email;
    private String mobile;
    private String password;
    private String validCode;

}
